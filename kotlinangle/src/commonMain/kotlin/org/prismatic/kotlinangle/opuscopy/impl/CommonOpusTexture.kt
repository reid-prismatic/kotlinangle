package org.prismatic.kotlinangle.opuscopy.impl

import org.prismatic.kotlinangle.AngleWrapper.Companion.GL_FLOAT
import org.prismatic.kotlinangle.AngleWrapper.Companion.GL_INT
import org.prismatic.kotlinangle.opuscopy.renderer.Mesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusMesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusTexture


internal class CommonOpusTexture(
    var name: GLTextureName?,
    override val width: Int,
    override val height: Int
) : OpusTexture

internal data class CommonOpusMesh(
    val primitiveType: Int,
    val vertexCount: Int,
    val stride: Int,
    val attributes: Map<Mesh.Attr.Tag, Mesh.Attr>,
    val attributeBuffer: GLBufferName,
    val elementBuffer: GLBufferName?
) : OpusMesh

internal val Mesh.Attr.Type.glType get() = when(this) {
    Mesh.Attr.FLOAT -> GL_FLOAT
    Mesh.Attr.INT -> GL_INT
}
