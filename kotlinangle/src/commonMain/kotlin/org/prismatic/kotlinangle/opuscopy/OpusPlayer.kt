package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.prismatic.kotlinangle.opuscopy.composable.core.MediaPlaybackNode
import kotlin.time.TimeSource

@Stable
interface OpusPlayer {
    val playing: Boolean
    val playhead: MediaTime
    val displayedFrame: MediaTime
    val duration: MediaTime
    fun play()
    fun stop()
    fun seek(target: Time)
}

val OpusPlayer.atEnd get() = displayedFrame >= duration
fun OpusPlayer.playFrom(time: Time) {
    if( !playing ) {
        seek(time.toMediaTime())
        play()
    }
}

@Composable
fun rememberOpusPlayer(opus: Opus, coroutineScope: CoroutineScope = rememberCoroutineScope()) : OpusPlayer {

    val treeFlow = remember { MutableStateFlow(OpusTree()) }
    val player = remember { OpusPlayerImpl(treeFlow, coroutineScope) }

    DisposableEffect(opus) {
        val comp = (opus as? ComposedOpusFactory)?.beginComposition { comp, opusContent ->
            CompositionLocalProvider(
                LocalPlayheadTime provides player.displayedFrame
            ) {

                ExplicitRecomposeScope {
                    // We want to make sure we don't read `playheadTime` directly in the same
                    // recomposition scope as the call to `opusContent()` or the entire composition
                    // will be re-composed from the top down every time the current time changes.
                    val t = playheadTime
                    val config = (opus as? OpusConfigProvider)?.activeConfig
                    SideEffect {
                        val newTree = comp.treeState.value
                        val updatedTree = comp.treeState.value.copy(currentTime = t, config = config ?: newTree.config)
                        player.duration = updatedTree.root.globalTiming.endTime
                        player.frameRate = updatedTree.config.frameRate
                        treeFlow.value = updatedTree
                    }
                }

                opusContent()
            }
        }
        val treeStateJob = coroutineScope.launch {
            comp?.treeState?.collectLatest { newTree ->
                // We have to do this here as well as in the composition side-effect because
                // otherwise changes that don't involve the playhead time (such as user edits)
                // might not be noticed. However, this is only needed if we're not in playback
                // mode.
                if( !player.playing ) {
                    (player as OpusPlayerImpl).duration = newTree.root.globalTiming.endTime
                    treeFlow.update { oldTree ->
                        newTree.copy(currentTime = oldTree.currentTime)
                    }
                }
            }
        }
        onDispose {
            treeStateJob.cancel()
            comp?.dispose()
        }
    }

    return player
}

private data class AudioKey(
    val path: String,
    val startTime: MediaTime
)

internal class OpusPlayerImpl(
    val treeFlow: MutableStateFlow<OpusTree>,
    val coroutineScope: CoroutineScope
): OpusPlayer {

    private var playbackJob: Job? = null
//    val engine: OpusEngine = OpusEngine()

    internal var frameRate : FrameRate by mutableStateOf(30.fps)
    private var frameNumber : Long by mutableStateOf(0)
    private var frameCount : Long by mutableStateOf(0)
    private var playbackTree: OpusTree = OpusTree()

    override var playing: Boolean by mutableStateOf(false)
        private set

    override val displayedFrame: MediaTime get() = MediaTime(frameNumber,frameRate)
    override var playhead: MediaTime by mutableStateOf(MediaTime.ZERO)
        private set

    override var duration: MediaTime get() = MediaTime(frameCount,frameRate)
        internal set(value) {
            frameCount = value.toFrameRate(frameRate).frameNumber
        }

    private val playingAudio = mutableMapOf<AudioKey,Result<AudioClip>>()

    override fun play() {
        if( playing ) return
        playing = true
        playbackJob?.cancel()
        playbackJob = coroutineScope.launch {
            playbackTree = treeFlow.value
            val startMark = TimeSource.Monotonic.markNow()
            val startFrame = frameNumber
            try {
                while (frameNumber < frameCount) {
                    val elapsedMillis = startMark.elapsedNow().inWholeMilliseconds
                    val targetRelativeFrame = elapsedMillis.millis.toMediaTime(frameRate).frameNumber + 1
                    val targetElapsedMillis = targetRelativeFrame * 1000 / frameRate.toInt()
                    val waitTime = (targetElapsedMillis - elapsedMillis - 1).coerceAtLeast(1)
                    delay(waitTime)
                    frameNumber = startFrame + targetRelativeFrame
                    playhead = displayedFrame
                    val usedAudioClips = mutableSetOf<AudioKey>()
                    playbackTree.forEachNodeOfType<MediaPlaybackNode>(displayedFrame) { node ->
                        if( node.path.isNotEmpty() ) {
                            val key = AudioKey(node.path,node.globalTiming.startTime)
                            usedAudioClips += key
                            if( key !in playingAudio ) {
                                playingAudio[key] = audioClipFromFile(node.path).onSuccess {
                                    val playbackOffset = displayedFrame - node.globalTiming.startTime
                                    if( playbackOffset > MediaTime(1,frameRate) ) {
                                        it.seek(playbackOffset)
                                    }
                                    it.play()
                                }
                            }
                        }
                    }
                    val unusedAudio = playingAudio.keys - usedAudioClips
                    unusedAudio.forEach {
                        playingAudio[it]?.getOrNull()?.apply { stop(); dispose() }
                    }
                    playingAudio -= unusedAudio
                }
            } finally {
                playing = false
                playbackJob = null
                playingAudio.values.forEach { it.getOrNull()?.apply { stop(); dispose() } }
                playingAudio.clear()
            }
        }
    }

    override fun stop() {
        playbackJob?.cancel()
    }

    override fun seek(target: Time) {
        if( !playing ) {
            val adjustedTarget = target.toMediaTime().coerceIn(MediaTime.ZERO,duration)
            playhead = adjustedTarget
            frameNumber = adjustedTarget.toFrameRate(frameRate).frameNumber
        }
    }

}

/**
 * This is essentially a no-op, but wrapping a content lambda in it forces the Compose compiler to
 * create a new recomposition scope, so that changes to data accessed in the inner scope don't cause
 * recomposition of the outer scope.
 */
@Composable
private fun ExplicitRecomposeScope(content:@Composable ()->Unit) {
    content()
}

private val LocalPlayheadTime = compositionLocalOf<MediaTime> { throw IllegalStateException() }
val playheadTime @Composable get() = LocalPlayheadTime.current