package org.prismatic.kotlinangle.opuscopy.renderer

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.opus.math.Mat4x4

interface CoreVisualRenderContext {
    fun fillScreenRGB(red: Float, green: Float, blue: Float)
    fun test()
    fun textTest(text: AnnotatedString, style: TextStyle)
    val playheadTime: MediaTime
}

interface PlatformVisualRenderContext : CoreVisualRenderContext {
    fun loadMesh(mesh: Mesh) : OpusMesh
    fun deleteMesh(mesh: OpusMesh)
    fun createTexture(imageBitmap: ImageBitmap) : OpusTexture
    fun deleteTexture(texture: OpusTexture)
    fun drawTexturedRect(
        texture: OpusTexture,
        left: Float = -1f,
        top: Float = 1f,
        right: Float = 1f,
        bottom: Float = -1f,
    )
    fun drawTexturedMesh(
        mesh: OpusMesh,
        texture: OpusTexture,
        transform: Mat4x4
    )
}

