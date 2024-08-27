package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun rememberOpus(
    width: Int = 1920,
    height: Int = 1080,
    frameRate: FrameRate = 30.fps,
    config: OpusConfig = OpusConfig(width, height, frameRate),
    content: @Composable ContainerContext.() -> Unit
): Opus {
    val opus = remember { Opus(config=config,content=content) }
    SideEffect {
        opus.setConfig(config)
    }
    return opus
}
