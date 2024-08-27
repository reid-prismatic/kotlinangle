package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.IntSize
import kotlin.js.JsName

@Immutable
data class OpusConfig(
    val width: Int = 1920,
    val height: Int = 1080,
    val frameRate: FrameRate = 30.fps
)

inline val OpusConfig.size get() = IntSize(width,height)

@Stable
interface Opus {
    fun setContent(content: @Composable ContainerContext.() -> Unit)
    fun setConfig(config: OpusConfig)
}

@JsName("createOpus")
fun Opus() : Opus {
    return createOpusInstanceInternal()
}

fun Opus(
    width: Int = 1920,
    height: Int = 1080,
    frameRate: FrameRate = 30.fps,
    config: OpusConfig = OpusConfig(width, height, frameRate),
    content: @Composable ContainerContext.() -> Unit,
) : Opus = Opus().apply {
    setContent(content)
    setConfig(config)
}

//@DslMarker
annotation class MediaTimelineDsl

@MediaTimelineDsl
interface EntityContext

@MediaTimelineDsl
interface ContainerContext : EntityContext

@MediaTimelineDsl
interface TrackContext : ContainerContext

@MediaTimelineDsl
interface GroupContext : ContainerContext
