package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface MediaSource {
    companion object {
        fun audio(path: String) : MediaSource { TODO() }
        fun video(path: String) : MediaSource { TODO() }
        fun image(path: String) : MediaSource { TODO() }
    }
}

fun MediaSource.trimStart(time: TimeType<*>) : MediaSource {
    TODO()
}

object NullMediaSource : MediaSource

@Composable
fun rememberMediaSource(path: String) : MediaSource {
    return remember { NullMediaSource }
}