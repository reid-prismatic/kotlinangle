package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.prismatic.kotlinangle.opuscopy.composable.core.RootGroup
import org.prismatic.kotlinangle.opuscopy.renderer.CompositionWrapper

internal fun createOpusInstanceInternal() : Opus = OpusImpl()

@Stable
internal class OpusImpl() : Opus, ComposedOpusFactory, OpusConfigProvider {

    private var content: (@Composable ContainerContext.() -> Unit)? = null
    override var activeConfig: OpusConfig by mutableStateOf(OpusConfig())

    private val frameClock = ImmediateMonotonicFrameClock() //BroadcastFrameClock()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val recomposeDispatcher = Dispatchers.Default.limitedParallelism(1) + frameClock

    private val recomposeScope = CoroutineScope(recomposeDispatcher)

    override fun setConfig(config: OpusConfig) {
        if( this.activeConfig != config ) {
            this.activeConfig = config
        }
    }

    private val recomposer by lazy {
        Recomposer(recomposeDispatcher).also {
            recomposeScope.launch {
                it.runRecomposeAndApplyChanges()
            }
        }
    }

    override fun setContent(content: @Composable ContainerContext.() -> Unit) {
        recomposeScope.launch {
            if (this@OpusImpl.content !== content) {
                this@OpusImpl.content = content
                composers.forEach { it.setContent(wrapContent(content)) }
            }
        }
    }

    private fun wrapContent(
        content: (@Composable ContainerContext.() -> Unit)? = null
    ) : @Composable () -> Unit {
        return {
            RootGroup {
                CompositionWrapper(content ?: {})
            }
        }
    }

    private var composers = listOf<ComposedOpusImpl>()

    override fun beginComposition(wrapper: @Composable (comp: ComposedOpus, content: @Composable () -> Unit) -> Unit): ComposedOpus {
        opusDebugTrace("OpusImpl") { "beginComposition" }
        val composedOpus = ComposedOpusImpl(this, wrapper, recomposer)
        opusDebugTrace("OpusImpl") { "beginComposition/compImpl=${composedOpus.debugId}" }
        recomposeScope.launch {
            composedOpus.setContent(wrapContent(content))
            composers += composedOpus
        }
        return composedOpus
    }

    fun endComposition(comp: ComposedOpusImpl) {
        opusDebugTrace("OpusImpl") { "endComposition(${comp.debugId})" }
        recomposeScope.launch {
            composers -= comp
        }
    }
}

internal interface ComposedOpusFactory {
    fun beginComposition(wrapper: @Composable (comp: ComposedOpus, content:@Composable ()->Unit)->Unit) : ComposedOpus
}

internal interface OpusConfigProvider {
    val activeConfig: OpusConfig
}

private class ImmediateMonotonicFrameClock : MonotonicFrameClock {
    private var frameCounter = 0L
    override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
        return onFrame(frameCounter++)
    }
}









