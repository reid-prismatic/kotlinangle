package org.prismatic.kotlinangle.opuscopy.impl

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import org.prismatic.kotlinangle.AngleWrapper
//import com.jogamp.opengl.GL4
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.impl.GLBufferName
import org.prismatic.kotlinangle.opuscopy.impl.GLTextureName
import org.prismatic.kotlinangle.opuscopy.impl.glBindBuffer
import org.prismatic.kotlinangle.opuscopy.impl.glBindTexture
import org.prismatic.kotlinangle.opuscopy.renderer.PlatformVisualRenderContext
import org.prismatic.opus.math.Mat4x4
import org.prismatic.kotlinangle.opuscopy.renderer.Mesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusMesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusTexture
import org.prismatic.kotlinangle.opuscopy.renderer.check

internal interface DesktopVisualRenderContext : PlatformVisualRenderContext {
    fun dispose()
    override var playheadTime: MediaTime
}

internal fun makeVisualRenderContext(gl: GLVER) = with(gl){object : DesktopVisualRenderContext {

    private var vbo: GLBufferName = GLBufferName.NONE
    private var texVbo: GLBufferName = GLBufferName.NONE
    private var quadVbo: GLBufferName = GLBufferName.NONE
    private var tex: GLTextureName = GLTextureName.NONE

    private var testProgram : Int = 0
    private var testTexProgram : Int = 0
    private var textured3DShader : Int = 0
    private var textured3DShader_transform : Int = 0
    private var textured3DShader_texture : Int = 0
    private var scratchMatrix = FloatArray(16)

    override var playheadTime: MediaTime = MediaTime.ZERO

    fun setup () {
        vbo = makeArrayBuffer(
            -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f,  0.0f, 1.0f,
            0.0f,  0.5f,  0.0f, 1.0f
        )
        val left = -1f
        val bottom = -1f
        val top = 1f
        val right = 1f
        quadVbo = makeArrayBuffer(
            left, bottom, 0f,   0f, 0f,
            right, bottom, 0f,  1f, 0f,
            left, top, 0f,      0f, 1f,
            right, top, 0f,     1f, 1f,
        )
        texVbo = makeArrayBuffer(
            -1f, -1f, 0f, 1f,
            1f, -1f, 1f, 1f,
            -1f,  1f, 0f, 0f,
            1f,  1f, 1f, 0f,
        )
        val tex = makeTexture()
        testProgram = createProgramFromResources(
            vertexShaderResourceId = "vertex",
            fragmentShaderResourceId = "fragment"
        )
        testTexProgram = createProgramFromResources(
            vertexShaderResourceId = "texvertex",
            fragmentShaderResourceId = "texfragment"
        )
        textured3DShader = createProgramFromResources(
            vertexShaderResourceId = "mesh_vertex",
            fragmentShaderResourceId = "mesh_fragment"
        )
        textured3DShader_transform = glGetUniformLocation(textured3DShader,"transform")
        textured3DShader_texture = glGetUniformLocation(textured3DShader,"textureSampler")
    }

    override fun dispose() {
        glDeleteBuffer(vbo)
        vbo = GLBufferName.NONE
    }

    override fun fillScreenRGB(red: Float, green: Float, blue: Float) {
        glClearColor(red, green, blue, 1f)
        glClear(GL4.GL_COLOR_BUFFER_BIT)
    }

    override fun createTexture(imageBitmap: ImageBitmap): OpusTexture {
        val texName = makeTexture()

        val pixmap = imageBitmap.toPixelMap()

        glBindTexture(GL4.GL_TEXTURE_2D,texName)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_S, GL4.GL_CLAMP_TO_EDGE)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_T, GL4.GL_CLAMP_TO_EDGE)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_LINEAR)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_LINEAR)
        glTexImage2D(
            GL4.GL_TEXTURE_2D, 0,
            GL4.GL_RGBA, pixmap.width, pixmap.height, 0,
            GL4.GL_BGRA,
            GL4.GL_UNSIGNED_BYTE, IntBuffer.wrap(pixmap.buffer))
        glBindTexture(GL4.GL_TEXTURE_2D,0)

        return DesktopOpusTexture(
            name = texName,
            width = imageBitmap.width,
            height = imageBitmap.height
        )
    }

    override fun deleteTexture(texture: OpusTexture) {
        if (texture !is DesktopOpusTexture) return
        val name = texture.name ?: return
        glDeleteTexture(name)
        texture.name = null
    }

    override fun loadMesh(mesh: Mesh): OpusMesh = DesktopOpusMesh(
        primitiveType = when(mesh.primitiveType) {
            Mesh.PrimitiveType.Triangles -> GL4.GL_TRIANGLES
            Mesh.PrimitiveType.TriangleStrip -> GL4.GL_TRIANGLE_STRIP
        },
        vertexCount = mesh.vertexCount,
        stride = mesh.stride,
        attributes = mesh.attributes,
        attributeBuffer = makeBuffer(GL4.GL_ARRAY_BUFFER, GL4.GL_STATIC_DRAW, mesh.attributeData),
        elementBuffer = mesh.elementData?.let { makeBuffer(
            GL4.GL_ELEMENT_ARRAY_BUFFER,
            GL4.GL_STATIC_DRAW, it) })

    override fun deleteMesh(mesh: OpusMesh) {
        mesh as DesktopOpusMesh
        glDeleteBuffer(mesh.attributeBuffer)
        mesh.elementBuffer?.let { glDeleteBuffer(it) }
    }

    override fun drawTexturedMesh(mesh: OpusMesh, texture: OpusTexture, transform: Mat4x4) {
        mesh as DesktopOpusMesh
        texture as DesktopOpusTexture

        if( !mesh.attributes.check { POSITION.matches(FLOAT,3) && TEXCOORD.optionallyMatches(FLOAT,2) } )
            return

        glUseProgram(textured3DShader)

        glBlendFunc(GL4.GL_SRC_ALPHA, GL4.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL4.GL_BLEND);
        glBindBuffer(GL4.GL_ARRAY_BUFFER,mesh.attributeBuffer)
        mesh.vertexAttribPointer(0, Mesh.Attr.POSITION)
        mesh.vertexAttribPointer(1, Mesh.Attr.TEXCOORD)

        transform.copyTo(scratchMatrix)
        glUniformMatrix4fv(textured3DShader_transform, 1, false, scratchMatrix, 0)
        glActiveTexture(GL4.GL_TEXTURE0)
        glBindTexture(GL4.GL_TEXTURE_2D, texture.name!!) // TODO: Use a fallback texture
        glUniform1i(textured3DShader_texture, 0);
        if( mesh.elementBuffer!=null ) {
            glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER,mesh.elementBuffer)
            glDrawElements(mesh.primitiveType, mesh.vertexCount, GL4.GL_UNSIGNED_INT, 0L)
            glBindBuffer(GL4.GL_ELEMENT_ARRAY_BUFFER,0)
        } else {
            glDrawArrays(mesh.primitiveType, 0, mesh.vertexCount)
        }
        checkGLErrors()
        glBindBuffer(GL4.GL_ARRAY_BUFFER,0)
        glBindTexture(GL4.GL_TEXTURE_2D,0)

    }

    private fun DesktopOpusMesh.vertexAttribPointer(index: Int, tag: Mesh.Attr.Tag) {
        val attr = attributes[tag]
        if( attr == null ) {
            glDisableVertexAttribArray(index)
        } else {
            glEnableVertexAttribArray(index)
            glVertexAttribPointer(index,attr.size,attr.type.glType, false, stride, attr.offset.toLong())
        }
    }

    override fun drawTexturedRect(
        texture: OpusTexture,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float
    ) {
        val texName = (texture as? DesktopOpusTexture)?.name ?: return

        // TODO: It's very inefficient making a new buffer every time; we should make a full-screen one and adjust it with uniform parameters or something
        val buf = makeArrayBuffer(
            left, bottom, 0f, 1f,
            right, bottom, 1f, 1f,
            left, top, 0f, 0f,
            right, top, 1f, 0f,
        )

        glBlendFunc(GL4.GL_SRC_ALPHA, GL4.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL4.GL_BLEND);

        glBindBuffer(GL4.GL_ARRAY_BUFFER,buf)
        checkGLErrors()
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)
        checkGLErrors()
        glVertexAttribPointer(0,2, GL4.GL_FLOAT, false, 4*Float.SIZE_BYTES, 0)
        glVertexAttribPointer(1,2, GL4.GL_FLOAT, false, 4*Float.SIZE_BYTES, 2L*Float.SIZE_BYTES)
        checkGLErrors()
        glUseProgram(testTexProgram)
        glActiveTexture(GL4.GL_TEXTURE0)
        glBindTexture(GL4.GL_TEXTURE_2D, texName)
        checkGLErrors()
        glDrawArrays(GL4.GL_TRIANGLE_STRIP,0,4)
        checkGLErrors()
        glBindBuffer(GL4.GL_ARRAY_BUFFER,0)

        glBindTexture(GL4.GL_TEXTURE_2D,0)
        glDeleteBuffer(buf)
    }

    override fun textTest(text: AnnotatedString, style: TextStyle) {
        val measurer = TextMeasurer(
            defaultFontFamilyResolver = createFontFamilyResolver(),
            defaultDensity = Density(1f,1f),
            defaultLayoutDirection = LayoutDirection.Ltr,
        )
        val measureResult = measurer.measure(text, style)
        val ib = ImageBitmap(width = measureResult.size.width, height = measureResult.size.height)
        val canvas = Canvas(ib)
        val scope = CanvasDrawScope()
        scope.drawContext.canvas = canvas
        scope.drawContext.size = Size(ib.width.toFloat(), ib.height.toFloat())
        scope.drawText(measureResult,style.color)
        val pixmap = ib.toPixelMap()

        glBindTexture(GL4.GL_TEXTURE_2D,tex)
//        glUniform1i(glGetUniformLocation(testTexProgram, "texture1"), 0);
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_S, GL4.GL_CLAMP_TO_EDGE)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_WRAP_T, GL4.GL_CLAMP_TO_EDGE)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_LINEAR)
        glTexParameteri(GL4.GL_TEXTURE_2D, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_LINEAR)
        glTexImage2D(
            GL4.GL_TEXTURE_2D, 0,
            GL4.GL_RGBA, pixmap.width, pixmap.height, 0,
            GL4.GL_BGRA,
            GL4.GL_UNSIGNED_BYTE, IntBuffer.wrap(pixmap.buffer))

//        glGenerateMipmap(GL_TEXTURE_2D)

        glBlendFunc(GL4.GL_SRC_ALPHA, GL4.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL4.GL_BLEND);

        glBindBuffer(GL4.GL_ARRAY_BUFFER,texVbo)
        checkGLErrors()
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)
        checkGLErrors()
        glVertexAttribPointer(0,2, GL4.GL_FLOAT, false, 4*Float.SIZE_BYTES, 0)
        glVertexAttribPointer(1,2, GL4.GL_FLOAT, false, 4*Float.SIZE_BYTES, 2L*Float.SIZE_BYTES)
        checkGLErrors()
        glUseProgram(testTexProgram)
        glActiveTexture(GL4.GL_TEXTURE0)
        glBindTexture(GL4.GL_TEXTURE_2D,tex)
        checkGLErrors()
        glDrawArrays(GL4.GL_TRIANGLE_STRIP,0,4)
        checkGLErrors()

//        glDeleteTexture(tex)
//        TextLayoutResult
//        glTexImage2D()
    }

    override fun test() {
        glBindBuffer(GL4.GL_ARRAY_BUFFER,vbo)
        checkGLErrors()
        glEnableVertexAttribArray(0)
        checkGLErrors()
        glVertexAttribPointer(0,4, GL4.GL_FLOAT, false, 4*Float.SIZE_BYTES, 0)
        checkGLErrors()
        glUseProgram(testProgram)
        checkGLErrors()
        glDrawArrays(GL4.GL_TRIANGLE_STRIP,0,3)
        checkGLErrors()
    }

    init { setup() }

}}
