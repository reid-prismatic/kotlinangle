package org.prismatic.kotlinangle.opuscopy.renderer

import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.composable.core.GraphicsMesh
import org.prismatic.kotlinangle.opuscopy.composable.core.GraphicsTexture
import org.prismatic.opus.math.Mat4x4

interface VisualRenderContext : CoreVisualRenderContext {
    val opusDuration: MediaTime
    val entityLocalTime: MediaTime
    val entityDuration: MediaTime
    val opusWidth: Int
    val opusHeight: Int
    val modelMatrix: Mat4x4
    val viewMatrix: Mat4x4
    val projectionMatrix: Mat4x4
    fun drawTexturedRect(
        texture: GraphicsTexture,
        left: Float = -1f,
        top: Float = 1f,
        right: Float = 1f,
        bottom: Float = -1f,
    )
    fun drawTexturedMesh(
        mesh: GraphicsMesh,
        texture: GraphicsTexture,
        transform: Mat4x4
    )
    val Quad: GraphicsMesh
}