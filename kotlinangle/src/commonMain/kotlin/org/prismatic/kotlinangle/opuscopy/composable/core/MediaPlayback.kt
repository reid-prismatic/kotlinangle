package org.prismatic.kotlinangle.opuscopy.composable.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import org.prismatic.kotlinangle.opuscopy.AudioClip
import org.prismatic.kotlinangle.opuscopy.EntityContext
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusNodeApplier
import org.prismatic.kotlinangle.opuscopy.PreferredNodeTiming
import org.prismatic.kotlinangle.opuscopy.audioClipFromFile
import org.prismatic.kotlinangle.opuscopy.getAudioFileDuration
import org.prismatic.kotlinangle.opuscopy.use

internal class MediaPlaybackNode : OpusNode() {
    override val isTimedNode: Boolean = true
    var path: String = ""
    override fun getPreferredTiming() = PreferredNodeTiming(
        duration = getAudioFileDuration(path)
    )
}

@Composable
fun EntityContext.MediaPlayback(path: String) {
    ComposeNode<MediaPlaybackNode, OpusNodeApplier>(
        factory = { MediaPlaybackNode() },
        update = {
            set(path) {
                this.path = path
            }
        },
        content = {}
    )
}

