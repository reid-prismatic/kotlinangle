package org.prismatic.kotlinangle.opuscopy.impl

//import com.jogamp.opengl.GL
//import com.jogamp.opengl.GL4.*
//import com.jogamp.opengl.glu.GLU
//import kotlinx.coroutines.runBlocking
import kotlinangle.kotlinangle.generated.resources.Res
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.io.bytestring.ByteString
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.prismatic.kotlinangle.AngleWrapper
import org.prismatic.kotlinangle.OpusBuffer.OpusByteBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusFloatBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusIntBuffer
import kotlin.jvm.JvmInline

//import org.prismatic.kotlinangle.opuscopy.generated.resources.Res
//import java.nio.OpusByteBuffer
//import java.nio.OpusFloatBuffer
//import java.nio.OpusIntBuffer

@JvmInline
internal value class GLBufferName(val name : Int) {
    companion object {
        val NONE = GLBufferName(0)
    }
}

@JvmInline
internal value class GLTextureName(val name : Int) {
    companion object {
        val NONE = GLTextureName(0)
    }
}

internal fun AngleWrapper.makeBuffer() : GLBufferName {
    val buffers = OpusIntBuffer.allocate(1)
    glGenBuffers(1,buffers)
    checkGLErrors()
    return( GLBufferName(buffers.get(0)) )
}

internal fun AngleWrapper.makeTexture() : GLTextureName {
    val textures = OpusIntBuffer.allocate(1)
    glGenTextures(1,textures)
    checkGLErrors()
    return( GLTextureName(textures.get(0)) )
}

internal fun AngleWrapper.glDeleteTexture(texture: GLTextureName) {
    val textures = OpusIntBuffer.allocate(1)
    textures.array()[0] = texture.name
    glDeleteTextures(1,textures)
    checkGLErrors()
}

internal fun AngleWrapper.glBindTexture(target: Int, texture: GLTextureName) {
    glBindTexture(target,texture.name)
    checkGLErrors()
}


internal fun AngleWrapper.glBindBuffer(target: Int, buffer: GLBufferName) {
    glBindBuffer(target,buffer.name)
    checkGLErrors()
}

internal fun AngleWrapper.glDeleteBuffer(buffer: GLBufferName) {
    val buffers = OpusIntBuffer.allocate(1)
    buffers.array()[0] = buffer.name
    glDeleteBuffers(1,buffers)
    checkGLErrors()
}

internal fun AngleWrapper.makeArrayBuffer(vararg elements: Float ) : GLBufferName =
    makeBuffer(AngleWrapper.GL_ARRAY_BUFFER, AngleWrapper.GL_STATIC_DRAW, elements)

internal fun AngleWrapper.makeBuffer(target: Int, usage: Int, elements: FloatArray ) : GLBufferName {
    val buffer = makeBuffer()
    glBindBuffer(target,buffer)
    glBufferData(target,elements.size * Float.SIZE_BYTES, OpusFloatBuffer.wrap(elements), usage)
    checkGLErrors()
    glBindBuffer(target,0)
    return buffer
}

internal fun AngleWrapper.makeBuffer(target: Int, usage: Int, data: ByteString ) : GLBufferName {
    val buffer = makeBuffer()
    glBindBuffer(target,buffer)
    glBufferData(target,data.size, OpusByteBuffer.allocate(data.size).also { data.copyInto(it.array()) }, usage)
    checkGLErrors()
    glBindBuffer(target,0)
    return buffer
}

internal fun AngleWrapper.checkGLErrors() {
    val error = glGetError()
    if(error != AngleWrapper.GL_NO_ERROR) {
        //throw GLError( error, GLU.createGLU(this).gluErrorString(error) )
        throw GLError( error,  "Error: OpenGL-Angle")
    }
}

//val vertCode = runBlocking { Res.readBytes("files/shaders/vertex.glsl") }.decodeToString()

@OptIn(ExperimentalResourceApi::class)
//internal fun GLVER.createVertexShaderFromResource(resourceId: String) =
//    createVertexShader(
//        code = runBlocking { Res.readBytes("files/shaders/$resourceId.glsl") }.decodeToString(),
//        debugTag = "vertex: $resourceId")
fun GLVER.createVertexShaderFromResource(resourceId: String): Int {
    var shader: Int = 0
    val job = CoroutineScope(Dispatchers.Default).launch {
        val shaderCode = Res.readBytes("files/shaders/$resourceId.glsl").decodeToString()
        shader = createVertexShader(
            code = shaderCode,
            debugTag = "vertex: $resourceId"
        )
    }

    while (!job.isCompleted) {}
    job.invokeOnCompletion { throwable ->
        throwable?.let {
            println("Error: ${it.message}")
        }
    }

    return shader
}


@OptIn(ExperimentalResourceApi::class)
//internal fun GLVER.createFragmentShaderFromResource(resourceId: String) =
//    createFragmentShader(
//        code = runBlocking { Res.readBytes("files/shaders/$resourceId.glsl") }.decodeToString(),
//        debugTag = "fragment: $resourceId")
fun GLVER.createFragmentShaderFromResource(resourceId: String): Int {
    var shader: Int = 0

    val job = CoroutineScope(Dispatchers.Default).launch {
        val shaderCode = Res.readBytes("files/shaders/$resourceId.glsl").decodeToString()
        shader = createFragmentShader(
            code = shaderCode,
            debugTag = "fragment: $resourceId"
        )
    }
    while (!job.isCompleted) {}
    job.invokeOnCompletion { throwable ->
        throwable?.let {
            println("Error: ${it.message}")
        }
    }

    return shader
}


internal fun GLVER.createVertexShader(code: String, debugTag: String? = null) =
    createShader(AngleWrapper.GL_VERTEX_SHADER,code,debugTag)
internal fun GLVER.createFragmentShader(code: String, debugTag: String? = null) =
    createShader(AngleWrapper.GL_FRAGMENT_SHADER,code,debugTag)

internal fun GLVER.glGetShaderiv(shader: Int, pname: Int) : Int {
    val result = OpusIntBuffer.allocate(1)
    glGetShaderiv(shader, pname, result)
    checkGLErrors()
    return result.get(0)
}

internal fun GLVER.glGetProgramiv(shader: Int, pname: Int) : Int {
    val result = OpusIntBuffer.allocate(1)
    glGetProgramiv(shader, pname, result)
    checkGLErrors()
    return result.get(0)
}

private fun GLVER.createShader(type: Int, code: String, debugTag: String? = null) : Int {
    val shader = glCreateShader(type)
    glShaderSource(shader,1, stringToOpusByteBuffer(code), null )
    glCompileShader(shader)
    checkGLErrors()
    val success = glGetShaderiv(shader, AngleWrapper.GL_COMPILE_STATUS) != 0
    if( !success ) {
        val infoLog = OpusByteBuffer.allocate(4096)
        glGetShaderInfoLog(shader, infoLog.capacity(), null, infoLog)
        val errorMessage = infoLog.array().decodeToString().substringBefore('\u0000').trim(' ', '\n','\t')
        throw GLShaderCompileError("Shader compile error [$debugTag]: $errorMessage")
    } else {
        return shader
    }
}

internal fun stringToOpusByteBuffer(string: String): OpusByteBuffer {
    val stringBytes = string.encodeToByteArray()
    return OpusByteBuffer.wrap(stringBytes)
}

internal fun GLVER.createProgramFromResources(
    vertexShaderResourceId: String,
    fragmentShaderResourceId: String
) : Int {
    val vert = createVertexShaderFromResource(vertexShaderResourceId)
    val frag = createFragmentShaderFromResource(fragmentShaderResourceId)
    val program = createProgram(vert,frag)
    glDeleteShader(vert)
    glDeleteShader(frag)
    return program
}


internal fun GLVER.createProgram(vararg shaders: Int) : Int {
    val program = glCreateProgram()
    checkGLErrors()
    shaders.forEach {
        glAttachShader(program,it)
        checkGLErrors()
    }
    glLinkProgram(program)
    checkGLErrors()
    val success = glGetProgramiv(program, AngleWrapper.GL_LINK_STATUS) != 0
    if(!success) {
        val infoLog = OpusByteBuffer.allocate(4096)
        glGetProgramInfoLog(program, infoLog.capacity(), null, infoLog)
        val errorMessage = infoLog.array().decodeToString().substringBefore('\u0000').trim(' ', '\n','\t')
        throw GLShaderCompileError("Shader link error: $errorMessage")
    } else {
        return program
    }
}

class GLShaderCompileError(description: String) : Exception(description)
class GLError(code: Int, description: String) : Exception("OpenGL Error $code: $description")