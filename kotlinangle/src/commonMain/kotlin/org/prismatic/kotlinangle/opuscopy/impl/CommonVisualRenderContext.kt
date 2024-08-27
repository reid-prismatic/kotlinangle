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
import org.prismatic.kotlinangle.OpusBuffer.OpusIntBuffer
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.renderer.PlatformVisualRenderContext
import org.prismatic.opus.math.Mat4x4
import org.prismatic.kotlinangle.opuscopy.renderer.Mesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusMesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusTexture
import org.prismatic.kotlinangle.opuscopy.renderer.check

typealias GLVER = AngleWrapper

internal interface CommonVisualRenderContext : PlatformVisualRenderContext {
    fun dispose()
    override var playheadTime: MediaTime
}

internal fun makeVisualRenderContext(gl: GLVER) = with(gl){ object : CommonVisualRenderContext {
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
        textured3DShader_transform = glGetUniformLocation(textured3DShader,stringToOpusByteBuffer("transform"))
        textured3DShader_texture = glGetUniformLocation(textured3DShader,stringToOpusByteBuffer("textureSampler"))
    }

    override fun dispose() {
        glDeleteBuffer(vbo)
        vbo = GLBufferName.NONE
    }

    override fun fillScreenRGB(red: Float, green: Float, blue: Float) {
        glClearColor(red, green, blue, 1f)
        glClear(AngleWrapper.GL_COLOR_BUFFER_BIT)
    }

    override fun createTexture(imageBitmap: ImageBitmap): OpusTexture {
        val texName = makeTexture()

        val pixmap = imageBitmap.toPixelMap()

        glBindTexture(AngleWrapper.GL_TEXTURE_2D,texName)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_WRAP_S, AngleWrapper.GL_CLAMP_TO_EDGE)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_WRAP_T, AngleWrapper.GL_CLAMP_TO_EDGE)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_MIN_FILTER, AngleWrapper.GL_LINEAR)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_MAG_FILTER, AngleWrapper.GL_LINEAR)
        glTexImage2D(
            AngleWrapper.GL_TEXTURE_2D, 0,
            AngleWrapper.GL_RGBA, pixmap.width, pixmap.height, 0,
            AngleWrapper.GL_BGRA,
            AngleWrapper.GL_UNSIGNED_BYTE, OpusIntBuffer.wrap(pixmap.buffer))
        glBindTexture(AngleWrapper.GL_TEXTURE_2D,0)

        return CommonOpusTexture(
            name = texName,
            width = imageBitmap.width,
            height = imageBitmap.height
        )
    }

    override fun deleteTexture(texture: OpusTexture) {
        if (texture !is CommonOpusTexture) return
        val name = texture.name ?: return
        glDeleteTexture(name)
        texture.name = null
    }

    override fun loadMesh(mesh: Mesh): OpusMesh = CommonOpusMesh(
        primitiveType = when(mesh.primitiveType) {
            Mesh.PrimitiveType.Triangles -> AngleWrapper.GL_TRIANGLES
            Mesh.PrimitiveType.TriangleStrip -> AngleWrapper.GL_TRIANGLE_STRIP
        },
        vertexCount = mesh.vertexCount,
        stride = mesh.stride,
        attributes = mesh.attributes,
        attributeBuffer = makeBuffer(AngleWrapper.GL_ARRAY_BUFFER, AngleWrapper.GL_STATIC_DRAW, mesh.attributeData),
        elementBuffer = mesh.elementData?.let { makeBuffer(
            AngleWrapper.GL_ELEMENT_ARRAY_BUFFER,
            AngleWrapper.GL_STATIC_DRAW, it) })

    override fun deleteMesh(mesh: OpusMesh) {
        mesh as CommonOpusMesh
        glDeleteBuffer(mesh.attributeBuffer)
        mesh.elementBuffer?.let { glDeleteBuffer(it) }
    }

    override fun drawTexturedMesh(mesh: OpusMesh, texture: OpusTexture, transform: Mat4x4) {
        mesh as CommonOpusMesh
        texture as CommonOpusTexture

        if( !mesh.attributes.check { POSITION.matches(FLOAT,3) && TEXCOORD.optionallyMatches(FLOAT,2) } )
            return

        glUseProgram(textured3DShader)

        glBlendFunc(AngleWrapper.GL_SRC_ALPHA, AngleWrapper.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(AngleWrapper.GL_BLEND);
        glBindBuffer(AngleWrapper.GL_ARRAY_BUFFER,mesh.attributeBuffer)
        mesh.vertexAttribPointer(0, Mesh.Attr.POSITION)
        mesh.vertexAttribPointer(1, Mesh.Attr.TEXCOORD)

        transform.copyTo(scratchMatrix)
        glUniformMatrix4fv(textured3DShader_transform, 1, 0, scratchMatrix, 0)
        glActiveTexture(AngleWrapper.GL_TEXTURE0)
        glBindTexture(AngleWrapper.GL_TEXTURE_2D, texture.name!!) // TODO: Use a fallback texture
        glUniform1i(textured3DShader_texture, 0);
        if( mesh.elementBuffer!=null ) {
            glBindBuffer(AngleWrapper.GL_ELEMENT_ARRAY_BUFFER,mesh.elementBuffer)
            //glDrawElements(mesh.primitiveType, mesh.vertexCount, AngleWrapper.GL_UNSIGNED_INT, 0L)
            glBindBuffer(AngleWrapper.GL_ELEMENT_ARRAY_BUFFER,0)
        } else {
            glDrawArrays(mesh.primitiveType, 0, mesh.vertexCount)
        }
        checkGLErrors()
        glBindBuffer(AngleWrapper.GL_ARRAY_BUFFER,0)
        glBindTexture(AngleWrapper.GL_TEXTURE_2D,0)

    }

    private fun CommonOpusMesh.vertexAttribPointer(index: Int, tag: Mesh.Attr.Tag) {
        val attr = attributes[tag]
        if( attr == null ) {
            glDisableVertexAttribArray(index)
        } else {
            glEnableVertexAttribArray(index)
            //glVertexAttribPointer(index,attr.size,attr.type.glType, 0, stride, attr.offset.toLong())
        }
    }

    override fun drawTexturedRect(
        texture: OpusTexture,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float
    ) {
        val texName = (texture as? CommonOpusTexture)?.name ?: return

        // TODO: It's very inefficient making a new buffer every time; we should make a full-screen one and adjust it with uniform parameters or something
        val buf = makeArrayBuffer(
            left, bottom, 0f, 1f,
            right, bottom, 1f, 1f,
            left, top, 0f, 0f,
            right, top, 1f, 0f,
        )

        glBlendFunc(AngleWrapper.GL_SRC_ALPHA, AngleWrapper.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(AngleWrapper.GL_BLEND);

        glBindBuffer(AngleWrapper.GL_ARRAY_BUFFER,buf)
        checkGLErrors()
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)
        checkGLErrors()
        //glVertexAttribPointer(0,2, AngleWrapper.GL_FLOAT, 0, 4*Float.SIZE_BYTES, 0)
        //glVertexAttribPointer(1,2, AngleWrapper.GL_FLOAT, 0, 4*Float.SIZE_BYTES, 2L*Float.SIZE_BYTES)
        checkGLErrors()
        glUseProgram(testTexProgram)
        glActiveTexture(AngleWrapper.GL_TEXTURE0)
        glBindTexture(AngleWrapper.GL_TEXTURE_2D, texName)
        checkGLErrors()
        glDrawArrays(AngleWrapper.GL_TRIANGLE_STRIP,0,4)
        checkGLErrors()
        glBindBuffer(AngleWrapper.GL_ARRAY_BUFFER,0)

        glBindTexture(AngleWrapper.GL_TEXTURE_2D,0)
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

        glBindTexture(AngleWrapper.GL_TEXTURE_2D,tex)
//        glUniform1i(glGetUniformLocation(testTexProgram, "texture1"), 0);
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_WRAP_S, AngleWrapper.GL_CLAMP_TO_EDGE)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_WRAP_T, AngleWrapper.GL_CLAMP_TO_EDGE)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_MIN_FILTER, AngleWrapper.GL_LINEAR)
        glTexParameteri(AngleWrapper.GL_TEXTURE_2D, AngleWrapper.GL_TEXTURE_MAG_FILTER, AngleWrapper.GL_LINEAR)
        glTexImage2D(
            AngleWrapper.GL_TEXTURE_2D, 0,
            AngleWrapper.GL_RGBA, pixmap.width, pixmap.height, 0,
            AngleWrapper.GL_BGRA,
            AngleWrapper.GL_UNSIGNED_BYTE, OpusIntBuffer.wrap(pixmap.buffer))

//        glGenerateMipmap(GL_TEXTURE_2D)

        glBlendFunc(AngleWrapper.GL_SRC_ALPHA, AngleWrapper.GL_ONE_MINUS_SRC_ALPHA);
        glEnable(AngleWrapper.GL_BLEND);

        glBindBuffer(AngleWrapper.GL_ARRAY_BUFFER,texVbo)
        checkGLErrors()
        glEnableVertexAttribArray(0)
        glEnableVertexAttribArray(1)
        checkGLErrors()
        //glVertexAttribPointer(0,2, AngleWrapper.GL_FLOAT, 0, 4*Float.SIZE_BYTES, 0)
        //glVertexAttribPointer(1,2, AngleWrapper.GL_FLOAT, 0, 4*Float.SIZE_BYTES, 2L*Float.SIZE_BYTES)
        checkGLErrors()
        glUseProgram(testTexProgram)
        glActiveTexture(AngleWrapper.GL_TEXTURE0)
        glBindTexture(AngleWrapper.GL_TEXTURE_2D,tex)
        checkGLErrors()
        glDrawArrays(AngleWrapper.GL_TRIANGLE_STRIP,0,4)
        checkGLErrors()

//        glDeleteTexture(tex)
//        TextLayoutResult
//        glTexImage2D()
    }

    override fun test() {
        glBindBuffer(AngleWrapper.GL_ARRAY_BUFFER,vbo)
        checkGLErrors()
        glEnableVertexAttribArray(0)
        checkGLErrors()
        //glVertexAttribPointer(0,4, AngleWrapper.GL_FLOAT, 0, 4*Float.SIZE_BYTES, 0)
        checkGLErrors()
        glUseProgram(testProgram)
        checkGLErrors()
        glDrawArrays(AngleWrapper.GL_TRIANGLE_STRIP,0,3)
        checkGLErrors()
    }

    init { setup() }

}}
