package org.prismatic.kotlinangle

import com.jogamp.common.nio.Buffers
import com.jogamp.common.nio.PointerBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusByteBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusFloatBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusIntBuffer
import java.io.File
import java.nio.ByteBuffer

//import java.nio.Buffer
//import java.nio.ByteBuffer
//import java.nio.FloatBuffer
//import java.nio.IntBuffer


object AngleNative: AngleWrapper {
	init {
		try {
//			val info = DynamicLibraryBundleInfoz
//			val lib = DynamicLibraryBundle()
			val baseDir = System.getProperty("user.dir")
			val dylibPath =
				"$baseDir/../../kotlinangle/build/libs/libPrismAngleLibrary.dylib"
			println("Attempting to load .dylib from: $dylibPath")
			val dylibFile = File(dylibPath)
			if (dylibFile.exists()) {
				System.load(dylibPath)
			} else {
				throw UnsatisfiedLinkError("Library not found at: $dylibPath")
			}
			// System.loadLibrary("PrismAngleLibrary");
		} catch (e: Exception) {
			e.printStackTrace()
			throw e
		}
	}

	/** Interface to C language function: <br></br> `void glActiveTexture(GLenum texture)`<br></br>    */
	external override fun glActiveTexture(texture: Int)

	/** Interface to C language function: <br></br> `void glAttachShader(GLuint program, GLuint shader)`<br></br>    */
	external override fun glAttachShader(program: Int, shader: Int)

	/** Interface to C language function: <br></br> `void glBindAttribLocation(GLuint program, GLuint index, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glBindAttribLocation(program: Int, index: Int, name: OpusByteBuffer?) {
		val name_is_direct: Boolean = Buffers.isDirect(name?.buf)
		glBindAttribLocation1(
			program,
			index,
			if (name_is_direct) name?.buf else Buffers.getArray(name?.buf),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name?.buf) else Buffers.getIndirectBufferByteOffset(
				name?.buf
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `void glBi`ndAttribLocation(GLuint program, GLuint index, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glBindAttribLocation1(
		program: Int,
		index: Int,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glBindAttribLocation(GLuint program, GLuint index, const GLchar *  name)`<br></br>    */
	override fun glBindAttribLocation(program: Int, index: Int, name: ByteArray?, name_offset: Int) {
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		glBindAttribLocation1(program, index, name, name_offset, false)
	}

	/** Interface to C language function: <br></br> `void glBindBuffer(GLenum target, GLuint buffer)`<br></br>    */
	external override fun glBindBuffer(target: Int, buffer: Int)

	/** Interface to C language function: <br></br> `void glBindFramebuffer(GLenum target, GLuint framebuffer)`<br></br>    */
	external override fun glBindFramebuffer(target: Int, framebuffer: Int)

	/** Interface to C language function: <br></br> `void glBindRenderbuffer(GLenum target, GLuint renderbuffer)`<br></br>    */
	external override fun glBindRenderbuffer(target: Int, renderbuffer: Int)

	/** Interface to C language function: <br></br> `void glBindTexture(GLenum target, GLuint texture)`<br></br>    */
	external override fun glBindTexture(target: Int, texture: Int)

	/** Interface to C language function: <br></br> `void glBlendColor(GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha)`<br></br>    */
	external override fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float)

	/** Interface to C language function: <br></br> `void glBlendEquation(GLenum mode)`<br></br>    */
	external override fun glBlendEquation(mode: Int)

	/** Interface to C language function: <br></br> `void glBlendEquationSeparate(GLenum modeRGB, GLenum modeAlpha)`<br></br>    */
	external override fun glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int)

	/** Interface to C language function: <br></br> `void glBlendFunc(GLenum sfactor, GLenum dfactor)`<br></br>    */
	external override fun glBlendFunc(sfactor: Int, dfactor: Int)

	/** Interface to C language function: <br></br> `void glBlendFuncSeparate(GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha)`<br></br>    */
	external override fun glBlendFuncSeparate(
		sfactorRGB: Int,
		dfactorRGB: Int,
		sfactorAlpha: Int,
		dfactorAlpha: Int
	)

	/** Interface to C language function: <br></br> `void glBufferData(GLenum target, GLsizeiptr size, const void *  data, GLenum usage)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glBufferData(target: Int, size: Int, data: OpusBuffer, usage: Int) {
		val data_is_direct: Boolean = Buffers.isDirect(data.buf)
		glBufferData1(
			target,
			size,
			if (data_is_direct) data.buf else Buffers.getArray(data.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data.buf) else Buffers.getIndirectBufferByteOffset(
				data.buf
			),
			data_is_direct,
			usage
		)
	}

	/** Entry point to C language function: `void glBufferData(GLenum target, GLsizeiptr size, const void *  data, GLenum usage)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glBufferData1(
		target: Int,
		size: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean,
		usage: Int
	)

	/** Interface to C language function: <br></br> `void glBufferSubData(GLenum target, GLintptr offset, GLsizeiptr size, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glBufferSubData(target: Int, offset: Int, size: Int, data: OpusBuffer) {
		val data_is_direct: Boolean = Buffers.isDirect(data.buf)
		glBufferSubData1(
			target,
			offset,
			size,
			if (data_is_direct) data.buf else Buffers.getArray(data.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data.buf) else Buffers.getIndirectBufferByteOffset(
				data.buf
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glBufferSubData(GLenum target, GLintptr offset, GLsizeiptr size, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glBufferSubData1(
		target: Int,
		offset: Int,
		size: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `GLenum glCheckFramebufferStatus(GLenum target)`<br></br>    */
	external override fun glCheckFramebufferStatus(target: Int): Int

	/** Interface to C language function: <br></br> `void glClear(GLbitfield mask)`<br></br>    */
	external override fun glClear(mask: Int)

	/** Interface to C language function: <br></br> `void glClearColor(GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha)`<br></br>    */
	external override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float)

	/** Interface to C language function: <br></br> `void glClearDepthf(GLfloat d)`<br></br>    */
	external override fun glClearDepthf(d: Float)

	/** Interface to C language function: <br></br> `void glClearStencil(GLint s)`<br></br>    */
	external override fun glClearStencil(s: Int)

	/** Interface to C language function: <br></br> `void glColorMask(GLboolean red, GLboolean green, GLboolean blue, GLboolean alpha)`<br></br>    */
	external override fun glColorMask(red: Byte, green: Byte, blue: Byte, alpha: Byte)

	/** Interface to C language function: <br></br> `void glCompileShader(GLuint shader)`<br></br>    */
	external override fun glCompileShader(shader: Int)

	/** Interface to C language function: <br></br> `void glCompressedTexImage2D(GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glCompressedTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		imageSize: Int,
		data: OpusBuffer
	) {
		val data_is_direct: Boolean = Buffers.isDirect(data.buf)
		glCompressedTexImage2D1(
			target,
			level,
			internalformat,
			width,
			height,
			border,
			imageSize,
			if (data_is_direct) data.buf else Buffers.getArray(data.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data.buf) else Buffers.getIndirectBufferByteOffset(
				data.buf
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glCompressedTexImage2D(GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glCompressedTexImage2D1(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		imageSize: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glCompressedTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glCompressedTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		imageSize: Int,
		data: OpusBuffer
	) {
		val data_is_direct: Boolean = Buffers.isDirect(data.buf)
		glCompressedTexSubImage2D1(
			target,
			level,
			xoffset,
			yoffset,
			width,
			height,
			format,
			imageSize,
			if (data_is_direct) data.buf else Buffers.getArray(data.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data.buf) else Buffers.getIndirectBufferByteOffset(
				data.buf
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glCompressedTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glCompressedTexSubImage2D1(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		imageSize: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glCopyTexImage2D(GLenum target, GLint level, GLenum internalformat, GLint x, GLint y, GLsizei width, GLsizei height, GLint border)`<br></br>    */
	external override fun glCopyTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		border: Int
	)

	/** Interface to C language function: <br></br> `void glCopyTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glCopyTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		x: Int,
		y: Int,
		width: Int,
		height: Int
	)

	/** Interface to C language function: <br></br> `GLuint glCreateProgram()`<br></br>    */
	external override fun glCreateProgram(): Int

	/** Interface to C language function: <br></br> `GLuint glCreateShader(GLenum type)`<br></br>    */
	external override fun glCreateShader(type: Int): Int

	/** Interface to C language function: <br></br> `void glCullFace(GLenum mode)`<br></br>    */
	external override fun glCullFace(mode: Int)

	/** Interface to C language function: <br></br> `void glDeleteBuffers(GLsizei n, const GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteBuffers(n: Int, buffers: OpusIntBuffer?) {
		val buffers_is_direct: Boolean = Buffers.isDirect(buffers?.buf)
		glDeleteBuffers1(
			n,
			if (buffers_is_direct) buffers?.buf else Buffers.getArray(buffers?.buf),
			if (buffers_is_direct) Buffers.getDirectBufferByteOffset(buffers?.buf) else Buffers.getIndirectBufferByteOffset(
				buffers?.buf
			),
			buffers_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteBuffers(GLsizei n, const GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteBuffers1(
		n: Int,
		buffers: Any?,
		buffers_byte_offset: Int,
		buffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteBuffers(GLsizei n, const GLuint *  buffers)`<br></br>    */
	override fun glDeleteBuffers(n: Int, buffers: IntArray?, buffers_offset: Int) {
		if (buffers != null && buffers.size <= buffers_offset) throw RuntimeException("array offset argument \"buffers_offset\" (" + buffers_offset + ") equals or exceeds array length (" + buffers.size + ")")
		glDeleteBuffers1(n, buffers, Buffers.SIZEOF_INT * buffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDeleteFramebuffers(GLsizei n, const GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteFramebuffers(n: Int, framebuffers: OpusIntBuffer?) {
		val framebuffers_is_direct: Boolean = Buffers.isDirect(framebuffers?.buf)
		glDeleteFramebuffers1(
			n,
			if (framebuffers_is_direct) framebuffers?.buf else Buffers.getArray(framebuffers?.buf),
			if (framebuffers_is_direct) Buffers.getDirectBufferByteOffset(framebuffers?.buf) else Buffers.getIndirectBufferByteOffset(
				framebuffers?.buf
			),
			framebuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteFramebuffers(GLsizei n, const GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteFramebuffers1(
		n: Int,
		framebuffers: Any?,
		framebuffers_byte_offset: Int,
		framebuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteFramebuffers(GLsizei n, const GLuint *  framebuffers)`<br></br>    */
	override fun glDeleteFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int) {
		if (framebuffers != null && framebuffers.size <= framebuffers_offset) throw RuntimeException(
			"array offset argument \"framebuffers_offset\" (" + framebuffers_offset + ") equals or exceeds array length (" + framebuffers.size + ")"
		)
		glDeleteFramebuffers1(n, framebuffers, Buffers.SIZEOF_INT * framebuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDeleteProgram(GLuint program)`<br></br>    */
	external override fun glDeleteProgram(program: Int)

	/** Interface to C language function: <br></br> `void glDeleteRenderbuffers(GLsizei n, const GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteRenderbuffers(n: Int, renderbuffers: OpusIntBuffer?) {
		val renderbuffers_is_direct: Boolean = Buffers.isDirect(renderbuffers?.buf)
		glDeleteRenderbuffers1(
			n,
			if (renderbuffers_is_direct) renderbuffers?.buf else Buffers.getArray(renderbuffers?.buf),
			if (renderbuffers_is_direct) Buffers.getDirectBufferByteOffset(renderbuffers?.buf) else Buffers.getIndirectBufferByteOffset(
				renderbuffers?.buf
			),
			renderbuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteRenderbuffers(GLsizei n, const GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteRenderbuffers1(
		n: Int,
		renderbuffers: Any?,
		renderbuffers_byte_offset: Int,
		renderbuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteRenderbuffers(GLsizei n, const GLuint *  renderbuffers)`<br></br>    */
	override fun glDeleteRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int) {
		if (renderbuffers != null && renderbuffers.size <= renderbuffers_offset) throw RuntimeException(
			"array offset argument \"renderbuffers_offset\" (" + renderbuffers_offset + ") equals or exceeds array length (" + renderbuffers.size + ")"
		)
		glDeleteRenderbuffers1(n, renderbuffers, Buffers.SIZEOF_INT * renderbuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDeleteShader(GLuint shader)`<br></br>    */
	external override fun glDeleteShader(shader: Int)

	/** Interface to C language function: <br></br> `void glDeleteTextures(GLsizei n, const GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteTextures(n: Int, textures: OpusIntBuffer?) {
		val textures_is_direct: Boolean = Buffers.isDirect(textures?.buf)
		glDeleteTextures1(
			n,
			if (textures_is_direct) textures?.buf else Buffers.getArray(textures?.buf),
			if (textures_is_direct) Buffers.getDirectBufferByteOffset(textures?.buf) else Buffers.getIndirectBufferByteOffset(
				textures?.buf
			),
			textures_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteTextures(GLsizei n, const GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteTextures1(
		n: Int,
		textures: Any?,
		textures_byte_offset: Int,
		textures_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteTextures(GLsizei n, const GLuint *  textures)`<br></br>    */
	override fun glDeleteTextures(n: Int, textures: IntArray?, textures_offset: Int) {
		if (textures != null && textures.size <= textures_offset) throw RuntimeException("array offset argument \"textures_offset\" (" + textures_offset + ") equals or exceeds array length (" + textures.size + ")")
		glDeleteTextures1(n, textures, Buffers.SIZEOF_INT * textures_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDepthFunc(GLenum func)`<br></br>    */
	external override fun glDepthFunc(func: Int)

	/** Interface to C language function: <br></br> `void glDepthMask(GLboolean flag)`<br></br>    */
	external override fun glDepthMask(flag: Byte)

	/** Interface to C language function: <br></br> `void glDepthRangef(GLfloat n, GLfloat f)`<br></br>    */
	external override fun glDepthRangef(n: Float, f: Float)

	/** Interface to C language function: <br></br> `void glDetachShader(GLuint program, GLuint shader)`<br></br>    */
	external override fun glDetachShader(program: Int, shader: Int)

	/** Interface to C language function: <br></br> `void glDisable(GLenum cap)`<br></br>    */
	external override fun glDisable(cap: Int)

	/** Interface to C language function: <br></br> `void glDisableVertexAttribArray(GLuint index)`<br></br>    */
	external override fun glDisableVertexAttribArray(index: Int)

	/** Interface to C language function: <br></br> `void glDrawArrays(GLenum mode, GLint first, GLsizei count)`<br></br>    */
	external override fun glDrawArrays(mode: Int, first: Int, count: Int)

	/** Interface to C language function: <br></br> `void glDrawElements(GLenum mode, GLsizei count, GLenum type, const void *  indices)`<br></br>
	 * @param indices a direct or array-backed [java.nio.Buffer]
	 */
	override fun glDrawElements(mode: Int, count: Int, type: Int, indices: OpusBuffer) {
		val indices_is_direct: Boolean = Buffers.isDirect(indices.buf)
		glDrawElements1(
			mode,
			count,
			type,
			if (indices_is_direct) indices.buf else Buffers.getArray(indices.buf),
			if (indices_is_direct) Buffers.getDirectBufferByteOffset(indices.buf) else Buffers.getIndirectBufferByteOffset(
				indices.buf
			),
			indices_is_direct
		)
	}

	/** Entry point to C language function: `void glDrawElements(GLenum mode, GLsizei count, GLenum type, const void *  indices)`<br></br>
	 * @param indices a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glDrawElements1(
		mode: Int,
		count: Int,
		type: Int,
		indices: Any,
		indices_byte_offset: Int,
		indices_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glEnable(GLenum cap)`<br></br>    */
	external override fun glEnable(cap: Int)

	/** Interface to C language function: <br></br> `void glEnableVertexAttribArray(GLuint index)`<br></br>    */
	external override fun glEnableVertexAttribArray(index: Int)

	/** Interface to C language function: <br></br> `void glFinish()`<br></br>    */
	external override fun glFinish()

	/** Interface to C language function: <br></br> `void glFlush()`<br></br>    */
	external override fun glFlush()

	/** Interface to C language function: <br></br> `void glFramebufferRenderbuffer(GLenum target, GLenum attachment, GLenum renderbuffertarget, GLuint renderbuffer)`<br></br>    */
	external override fun glFramebufferRenderbuffer(
		target: Int,
		attachment: Int,
		renderbuffertarget: Int,
		renderbuffer: Int
	)

	/** Interface to C language function: <br></br> `void glFramebufferTexture2D(GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level)`<br></br>    */
	external override fun glFramebufferTexture2D(
		target: Int,
		attachment: Int,
		textarget: Int,
		texture: Int,
		level: Int
	)

	/** Interface to C language function: <br></br> `void glFrontFace(GLenum mode)`<br></br>    */
	external override fun glFrontFace(mode: Int)

	/** Interface to C language function: <br></br> `void glGenBuffers(GLsizei n, GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenBuffers(n: Int, buffers: OpusIntBuffer?) {
		val buffers_is_direct: Boolean = Buffers.isDirect(buffers?.buf)
		glGenBuffers1(
			n,
			if (buffers_is_direct) buffers?.buf else Buffers.getArray(buffers?.buf),
			if (buffers_is_direct) Buffers.getDirectBufferByteOffset(buffers?.buf) else Buffers.getIndirectBufferByteOffset(
				buffers?.buf
			),
			buffers_is_direct
		)
	}

	/** Entry point to C language function: `void glGenBuffers(GLsizei n, GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenBuffers1(
		n: Int,
		buffers: Any?,
		buffers_byte_offset: Int,
		buffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenBuffers(GLsizei n, GLuint *  buffers)`<br></br>    */
	override fun glGenBuffers(n: Int, buffers: IntArray?, buffers_offset: Int) {
		if (buffers != null && buffers.size <= buffers_offset) throw RuntimeException("array offset argument \"buffers_offset\" (" + buffers_offset + ") equals or exceeds array length (" + buffers.size + ")")
		glGenBuffers1(n, buffers, Buffers.SIZEOF_INT * buffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGenerateMipmap(GLenum target)`<br></br>    */
	external override fun glGenerateMipmap(target: Int)

	/** Interface to C language function: <br></br> `void glGenFramebuffers(GLsizei n, GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenFramebuffers(n: Int, framebuffers: OpusIntBuffer?) {
		val framebuffers_is_direct: Boolean = Buffers.isDirect(framebuffers?.buf)
		glGenFramebuffers1(
			n,
			if (framebuffers_is_direct) framebuffers?.buf else Buffers.getArray(framebuffers?.buf),
			if (framebuffers_is_direct) Buffers.getDirectBufferByteOffset(framebuffers?.buf) else Buffers.getIndirectBufferByteOffset(
				framebuffers?.buf
			),
			framebuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glGenFramebuffers(GLsizei n, GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenFramebuffers1(
		n: Int,
		framebuffers: Any?,
		framebuffers_byte_offset: Int,
		framebuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenFramebuffers(GLsizei n, GLuint *  framebuffers)`<br></br>    */
	override fun glGenFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int) {
		if (framebuffers != null && framebuffers.size <= framebuffers_offset) throw RuntimeException(
			"array offset argument \"framebuffers_offset\" (" + framebuffers_offset + ") equals or exceeds array length (" + framebuffers.size + ")"
		)
		glGenFramebuffers1(n, framebuffers, Buffers.SIZEOF_INT * framebuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGenRenderbuffers(GLsizei n, GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenRenderbuffers(n: Int, renderbuffers: OpusIntBuffer?) {
		val renderbuffers_is_direct: Boolean = Buffers.isDirect(renderbuffers?.buf)
		glGenRenderbuffers1(
			n,
			if (renderbuffers_is_direct) renderbuffers?.buf else Buffers.getArray(renderbuffers?.buf),
			if (renderbuffers_is_direct) Buffers.getDirectBufferByteOffset(renderbuffers?.buf) else Buffers.getIndirectBufferByteOffset(
				renderbuffers?.buf
			),
			renderbuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glGenRenderbuffers(GLsizei n, GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenRenderbuffers1(
		n: Int,
		renderbuffers: Any?,
		renderbuffers_byte_offset: Int,
		renderbuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenRenderbuffers(GLsizei n, GLuint *  renderbuffers)`<br></br>    */
	override fun glGenRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int) {
		if (renderbuffers != null && renderbuffers.size <= renderbuffers_offset) throw RuntimeException(
			"array offset argument \"renderbuffers_offset\" (" + renderbuffers_offset + ") equals or exceeds array length (" + renderbuffers.size + ")"
		)
		glGenRenderbuffers1(n, renderbuffers, Buffers.SIZEOF_INT * renderbuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGenTextures(GLsizei n, GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenTextures(n: Int, textures: OpusIntBuffer?) {
		val textures_is_direct: Boolean = Buffers.isDirect(textures?.buf)
		glGenTextures1(
			n,
			if (textures_is_direct) textures?.buf else Buffers.getArray(textures?.buf),
			if (textures_is_direct) Buffers.getDirectBufferByteOffset(textures?.buf) else Buffers.getIndirectBufferByteOffset(
				textures?.buf
			),
			textures_is_direct
		)
	}

	/** Entry point to C language function: `void glGenTextures(GLsizei n, GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenTextures1(
		n: Int,
		textures: Any?,
		textures_byte_offset: Int,
		textures_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenTextures(GLsizei n, GLuint *  textures)`<br></br>    */
	override fun glGenTextures(n: Int, textures: IntArray?, textures_offset: Int) {
		if (textures != null && textures.size <= textures_offset) throw RuntimeException("array offset argument \"textures_offset\" (" + textures_offset + ") equals or exceeds array length (" + textures.size + ")")
		glGenTextures1(n, textures, Buffers.SIZEOF_INT * textures_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetActiveAttrib(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetActiveAttrib(
		program: Int,
		index: Int,
		bufSize: Int,
		length: OpusIntBuffer?,
		size: OpusIntBuffer?,
		type: OpusIntBuffer?,
		name: OpusByteBuffer?
	) {


		val length_is_direct: Boolean = Buffers.isDirect(length?.buf)
		val size_is_direct: Boolean = Buffers.isDirect(size?.buf)
		val type_is_direct: Boolean = Buffers.isDirect(type?.buf)
		val name_is_direct: Boolean = Buffers.isDirect(name?.buf)
		glGetActiveAttrib1(
			program,
			index,
			bufSize,
			if (length_is_direct) length?.buf else Buffers.getArray(length?.buf),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length?.buf) else Buffers.getIndirectBufferByteOffset(
				length?.buf
			),
			length_is_direct,
			if (size_is_direct) size?.buf else Buffers.getArray(size?.buf),
			if (size_is_direct) Buffers.getDirectBufferByteOffset(size?.buf) else Buffers.getIndirectBufferByteOffset(
				size?.buf
			),
			size_is_direct,
			if (type_is_direct) type?.buf else Buffers.getArray(type?.buf),
			if (type_is_direct) Buffers.getDirectBufferByteOffset(type?.buf) else Buffers.getIndirectBufferByteOffset(
				type?.buf
			),
			type_is_direct,
			if (name_is_direct) name?.buf else Buffers.getArray(name?.buf),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name?.buf) else Buffers.getIndirectBufferByteOffset(
				name?.buf
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `void glGetActiveAttrib(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetActiveAttrib1(
		program: Int,
		index: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		size: Any?,
		size_byte_offset: Int,
		size_is_direct: Boolean,
		type: Any?,
		type_byte_offset: Int,
		type_is_direct: Boolean,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetActiveAttrib(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>    */
	override fun glGetActiveAttrib(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		size: IntArray?,
		size_offset: Int,
		type: IntArray?,
		type_offset: Int,
		name: ByteArray?,
		name_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (size != null && size.size <= size_offset) throw RuntimeException("array offset argument \"size_offset\" (" + size_offset + ") equals or exceeds array length (" + size.size + ")")
		if (type != null && type.size <= type_offset) throw RuntimeException("array offset argument \"type_offset\" (" + type_offset + ") equals or exceeds array length (" + type.size + ")")
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		glGetActiveAttrib1(
			program,
			index,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			size,
			Buffers.SIZEOF_INT * size_offset,
			false,
			type,
			Buffers.SIZEOF_INT * type_offset,
			false,
			name,
			name_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetActiveUniform(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetActiveUniform(
		program: Int,
		index: Int,
		bufSize: Int,
		length: OpusIntBuffer?,
		size: OpusIntBuffer?,
		type: OpusIntBuffer?,
		name: OpusByteBuffer?
	) {
		val length_is_direct: Boolean = Buffers.isDirect(length?.buf)
		val size_is_direct: Boolean = Buffers.isDirect(size?.buf)
		val type_is_direct: Boolean = Buffers.isDirect(type?.buf)
		val name_is_direct: Boolean = Buffers.isDirect(name?.buf)
		glGetActiveUniform1(
			program,
			index,
			bufSize,
			if (length_is_direct) length?.buf else Buffers.getArray(length?.buf),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length?.buf) else Buffers.getIndirectBufferByteOffset(
				length?.buf
			),
			length_is_direct,
			if (size_is_direct) size?.buf else Buffers.getArray(size?.buf),
			if (size_is_direct) Buffers.getDirectBufferByteOffset(size?.buf) else Buffers.getIndirectBufferByteOffset(
				size?.buf
			),
			size_is_direct,
			if (type_is_direct) type?.buf else Buffers.getArray(type?.buf),
			if (type_is_direct) Buffers.getDirectBufferByteOffset(type?.buf) else Buffers.getIndirectBufferByteOffset(
				type?.buf
			),
			type_is_direct,
			if (name_is_direct) name?.buf else Buffers.getArray(name?.buf),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name?.buf) else Buffers.getIndirectBufferByteOffset(
				name?.buf
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `void glGetActiveUniform(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetActiveUniform1(
		program: Int,
		index: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		size: Any?,
		size_byte_offset: Int,
		size_is_direct: Boolean,
		type: Any?,
		type_byte_offset: Int,
		type_is_direct: Boolean,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetActiveUniform(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>    */
	override fun glGetActiveUniform(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		size: IntArray?,
		size_offset: Int,
		type: IntArray?,
		type_offset: Int,
		name: ByteArray?,
		name_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (size != null && size.size <= size_offset) throw RuntimeException("array offset argument \"size_offset\" (" + size_offset + ") equals or exceeds array length (" + size.size + ")")
		if (type != null && type.size <= type_offset) throw RuntimeException("array offset argument \"type_offset\" (" + type_offset + ") equals or exceeds array length (" + type.size + ")")
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		glGetActiveUniform1(
			program,
			index,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			size,
			Buffers.SIZEOF_INT * size_offset,
			false,
			type,
			Buffers.SIZEOF_INT * type_offset,
			false,
			name,
			name_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetAttachedShaders(GLuint program, GLsizei maxCount, GLsizei *  count, GLuint *  shaders)`<br></br>
	 * @param count a direct or array-backed [java.nio.IntBuffer]
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetAttachedShaders(program: Int, maxCount: Int, count: OpusIntBuffer?, shaders: OpusIntBuffer?) {
		val count_is_direct: Boolean = Buffers.isDirect(count?.buf)
		val shaders_is_direct: Boolean = Buffers.isDirect(shaders?.buf)
		glGetAttachedShaders1(
			program,
			maxCount,
			if (count_is_direct) count?.buf else Buffers.getArray(count?.buf),
			if (count_is_direct) Buffers.getDirectBufferByteOffset(count?.buf) else Buffers.getIndirectBufferByteOffset(
				count?.buf
			),
			count_is_direct,
			if (shaders_is_direct) shaders?.buf else Buffers.getArray(shaders?.buf),
			if (shaders_is_direct) Buffers.getDirectBufferByteOffset(shaders?.buf) else Buffers.getIndirectBufferByteOffset(
				shaders?.buf
			),
			shaders_is_direct
		)
	}

	/** Entry point to C language function: `void glGetAttachedShaders(GLuint program, GLsizei maxCount, GLsizei *  count, GLuint *  shaders)`<br></br>
	 * @param count a direct or array-backed [java.nio.IntBuffer]
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetAttachedShaders1(
		program: Int,
		maxCount: Int,
		count: Any?,
		count_byte_offset: Int,
		count_is_direct: Boolean,
		shaders: Any?,
		shaders_byte_offset: Int,
		shaders_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetAttachedShaders(GLuint program, GLsizei maxCount, GLsizei *  count, GLuint *  shaders)`<br></br>    */
	override fun glGetAttachedShaders(
		program: Int,
		maxCount: Int,
		count: IntArray?,
		count_offset: Int,
		shaders: IntArray?,
		shaders_offset: Int
	) {
		if (count != null && count.size <= count_offset) throw RuntimeException("array offset argument \"count_offset\" (" + count_offset + ") equals or exceeds array length (" + count.size + ")")
		if (shaders != null && shaders.size <= shaders_offset) throw RuntimeException("array offset argument \"shaders_offset\" (" + shaders_offset + ") equals or exceeds array length (" + shaders.size + ")")
		glGetAttachedShaders1(
			program,
			maxCount,
			count,
			Buffers.SIZEOF_INT * count_offset,
			false,
			shaders,
			Buffers.SIZEOF_INT * shaders_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `GLint glGetAttribLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetAttribLocation(program: Int, name: OpusByteBuffer?): Int {
		val name_is_direct: Boolean = Buffers.isDirect(name?.buf)
		return glGetAttribLocation1(
			program,
			if (name_is_direct) name?.buf else Buffers.getArray(name?.buf),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name?.buf) else Buffers.getIndirectBufferByteOffset(
				name?.buf
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `GLint glGetAttribLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetAttribLocation1(
		program: Int,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	): Int

	/** Interface to C language function: <br></br> `GLint glGetAttribLocation(GLuint program, const GLchar *  name)`<br></br>    */
	override fun glGetAttribLocation(program: Int, name: ByteArray?, name_offset: Int): Int {
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		return glGetAttribLocation1(program, name, name_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetBooleanv(GLenum pname, GLboolean *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetBooleanv(pname: Int, data: OpusByteBuffer?) {
		val data_is_direct: Boolean = Buffers.isDirect(data?.buf)
		glGetBooleanv1(
			pname,
			if (data_is_direct) data?.buf else Buffers.getArray(data?.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data?.buf) else Buffers.getIndirectBufferByteOffset(
				data?.buf
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glGetBooleanv(GLenum pname, GLboolean *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetBooleanv1(
		pname: Int,
		data: Any?,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetBooleanv(GLenum pname, GLboolean *  data)`<br></br>    */
	override fun glGetBooleanv(pname: Int, data: ByteArray?, data_offset: Int) {
		if (data != null && data.size <= data_offset) throw RuntimeException("array offset argument \"data_offset\" (" + data_offset + ") equals or exceeds array length (" + data.size + ")")
		glGetBooleanv1(pname, data, data_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetBufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetBufferParameteriv(target: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetBufferParameteriv1(
			target,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetBufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetBufferParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetBufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetBufferParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetBufferParameteriv1(target, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `GLenum glGetError()`<br></br>    */
	external override fun glGetError(): Int

	/** Interface to C language function: <br></br> `void glGetFloatv(GLenum pname, GLfloat *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetFloatv(pname: Int, data: OpusFloatBuffer?) {
		val data_is_direct: Boolean = Buffers.isDirect(data?.buf)
		glGetFloatv1(
			pname,
			if (data_is_direct) data?.buf else Buffers.getArray(data?.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data?.buf) else Buffers.getIndirectBufferByteOffset(
				data?.buf
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glGetFloatv(GLenum pname, GLfloat *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetFloatv1(
		pname: Int,
		data: Any?,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetFloatv(GLenum pname, GLfloat *  data)`<br></br>    */
	override fun glGetFloatv(pname: Int, data: FloatArray?, data_offset: Int) {
		if (data != null && data.size <= data_offset) throw RuntimeException("array offset argument \"data_offset\" (" + data_offset + ") equals or exceeds array length (" + data.size + ")")
		glGetFloatv1(pname, data, Buffers.SIZEOF_FLOAT * data_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetFramebufferAttachmentParameteriv(GLenum target, GLenum attachment, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: OpusIntBuffer?
	) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetFramebufferAttachmentParameteriv1(
			target,
			attachment,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetFramebufferAttachmentParameteriv(GLenum target, GLenum attachment, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetFramebufferAttachmentParameteriv1(
		target: Int,
		attachment: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetFramebufferAttachmentParameteriv(GLenum target, GLenum attachment, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: IntArray?,
		params_offset: Int
	) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetFramebufferAttachmentParameteriv1(
			target,
			attachment,
			pname,
			params,
			Buffers.SIZEOF_INT * params_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetIntegerv(GLenum pname, GLint *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetIntegerv(pname: Int, data: OpusIntBuffer?) {
		val data_is_direct: Boolean = Buffers.isDirect(data?.buf)
		glGetIntegerv1(
			pname,
			if (data_is_direct) data?.buf else Buffers.getArray(data?.buf),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data?.buf) else Buffers.getIndirectBufferByteOffset(
				data?.buf
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glGetIntegerv(GLenum pname, GLint *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetIntegerv1(
		pname: Int,
		data: Any?,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetIntegerv(GLenum pname, GLint *  data)`<br></br>    */
	override fun glGetIntegerv(pname: Int, data: IntArray?, data_offset: Int) {
		if (data != null && data.size <= data_offset) throw RuntimeException("array offset argument \"data_offset\" (" + data_offset + ") equals or exceeds array length (" + data.size + ")")
		glGetIntegerv1(pname, data, Buffers.SIZEOF_INT * data_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetProgramiv(GLuint program, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetProgramiv(program: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetProgramiv1(
			program,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetProgramiv(GLuint program, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetProgramiv1(
		program: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetProgramiv(GLuint program, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetProgramiv(program: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetProgramiv1(program, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetProgramInfoLog(GLuint program, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetProgramInfoLog(program: Int, bufSize: Int, length: OpusIntBuffer?, infoLog: OpusByteBuffer?) {
		val length_is_direct: Boolean = Buffers.isDirect(length?.buf)
		val infoLog_is_direct: Boolean = Buffers.isDirect(infoLog?.buf)
		glGetProgramInfoLog1(
			program,
			bufSize,
			if (length_is_direct) length?.buf else Buffers.getArray(length?.buf),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length?.buf) else Buffers.getIndirectBufferByteOffset(
				length?.buf
			),
			length_is_direct,
			if (infoLog_is_direct) infoLog?.buf else Buffers.getArray(infoLog?.buf),
			if (infoLog_is_direct) Buffers.getDirectBufferByteOffset(infoLog?.buf) else Buffers.getIndirectBufferByteOffset(
				infoLog?.buf
			),
			infoLog_is_direct
		)
	}

	/** Entry point to C language function: `void glGetProgramInfoLog(GLuint program, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetProgramInfoLog1(
		program: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		infoLog: Any?,
		infoLog_byte_offset: Int,
		infoLog_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetProgramInfoLog(GLuint program, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>    */
	override fun glGetProgramInfoLog(
		program: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (infoLog != null && infoLog.size <= infoLog_offset) throw RuntimeException("array offset argument \"infoLog_offset\" (" + infoLog_offset + ") equals or exceeds array length (" + infoLog.size + ")")
		glGetProgramInfoLog1(
			program,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			infoLog,
			infoLog_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetRenderbufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetRenderbufferParameteriv1(
			target,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetRenderbufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetRenderbufferParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetRenderbufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetRenderbufferParameteriv(
		target: Int,
		pname: Int,
		params: IntArray?,
		params_offset: Int
	) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetRenderbufferParameteriv1(
			target,
			pname,
			params,
			Buffers.SIZEOF_INT * params_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetShaderiv(GLuint shader, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetShaderiv(shader: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetShaderiv1(
			shader,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderiv(GLuint shader, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetShaderiv1(
		shader: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderiv(GLuint shader, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetShaderiv(shader: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetShaderiv1(shader, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetShaderInfoLog(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetShaderInfoLog(shader: Int, bufSize: Int, length: OpusIntBuffer?, infoLog: OpusByteBuffer?) {
		val length_is_direct: Boolean = Buffers.isDirect(length?.buf)
		val infoLog_is_direct: Boolean = Buffers.isDirect(infoLog?.buf)
		glGetShaderInfoLog1(
			shader,
			bufSize,
			if (length_is_direct) length?.buf else Buffers.getArray(length?.buf),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length?.buf) else Buffers.getIndirectBufferByteOffset(
				length?.buf
			),
			length_is_direct,
			if (infoLog_is_direct) infoLog?.buf else Buffers.getArray(infoLog?.buf),
			if (infoLog_is_direct) Buffers.getDirectBufferByteOffset(infoLog?.buf) else Buffers.getIndirectBufferByteOffset(
				infoLog?.buf
			),
			infoLog_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderInfoLog(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetShaderInfoLog1(
		shader: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		infoLog: Any?,
		infoLog_byte_offset: Int,
		infoLog_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderInfoLog(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>    */
	override fun glGetShaderInfoLog(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (infoLog != null && infoLog.size <= infoLog_offset) throw RuntimeException("array offset argument \"infoLog_offset\" (" + infoLog_offset + ") equals or exceeds array length (" + infoLog.size + ")")
		glGetShaderInfoLog1(
			shader,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			infoLog,
			infoLog_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetShaderPrecisionFormat(GLenum shadertype, GLenum precisiontype, GLint *  range, GLint *  precision)`<br></br>
	 * @param range a direct or array-backed [java.nio.IntBuffer]
	 * @param precision a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: OpusIntBuffer?,
		precision: OpusIntBuffer?
	) {
		val range_is_direct: Boolean = Buffers.isDirect(range?.buf)
		val precision_is_direct: Boolean = Buffers.isDirect(precision?.buf)
		glGetShaderPrecisionFormat1(
			shadertype,
			precisiontype,
			if (range_is_direct) range?.buf else Buffers.getArray(range?.buf),
			if (range_is_direct) Buffers.getDirectBufferByteOffset(range?.buf) else Buffers.getIndirectBufferByteOffset(
				range?.buf
			),
			range_is_direct,
			if (precision_is_direct) precision?.buf else Buffers.getArray(precision?.buf),
			if (precision_is_direct) Buffers.getDirectBufferByteOffset(precision?.buf) else Buffers.getIndirectBufferByteOffset(
				precision?.buf
			),
			precision_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderPrecisionFormat(GLenum shadertype, GLenum precisiontype, GLint *  range, GLint *  precision)`<br></br>
	 * @param range a direct or array-backed [java.nio.IntBuffer]
	 * @param precision a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetShaderPrecisionFormat1(
		shadertype: Int,
		precisiontype: Int,
		range: Any?,
		range_byte_offset: Int,
		range_is_direct: Boolean,
		precision: Any?,
		precision_byte_offset: Int,
		precision_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderPrecisionFormat(GLenum shadertype, GLenum precisiontype, GLint *  range, GLint *  precision)`<br></br>    */
	override fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: IntArray?,
		range_offset: Int,
		precision: IntArray?,
		precision_offset: Int
	) {
		if (range != null && range.size <= range_offset) throw RuntimeException("array offset argument \"range_offset\" (" + range_offset + ") equals or exceeds array length (" + range.size + ")")
		if (precision != null && precision.size <= precision_offset) throw RuntimeException("array offset argument \"precision_offset\" (" + precision_offset + ") equals or exceeds array length (" + precision.size + ")")
		glGetShaderPrecisionFormat1(
			shadertype,
			precisiontype,
			range,
			Buffers.SIZEOF_INT * range_offset,
			false,
			precision,
			Buffers.SIZEOF_INT * precision_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetShaderSource(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  source)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param source a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetShaderSource(shader: Int, bufSize: Int, length: OpusIntBuffer?, source: OpusByteBuffer?) {
		val length_is_direct: Boolean = Buffers.isDirect(length?.buf)
		val source_is_direct: Boolean = Buffers.isDirect(source?.buf)
		glGetShaderSource1(
			shader,
			bufSize,
			if (length_is_direct) length?.buf else Buffers.getArray(length?.buf),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length?.buf) else Buffers.getIndirectBufferByteOffset(
				length?.buf
			),
			length_is_direct,
			if (source_is_direct) source?.buf else Buffers.getArray(source?.buf),
			if (source_is_direct) Buffers.getDirectBufferByteOffset(source?.buf) else Buffers.getIndirectBufferByteOffset(
				source?.buf
			),
			source_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderSource(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  source)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param source a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetShaderSource1(
		shader: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		source: Any?,
		source_byte_offset: Int,
		source_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderSource(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  source)`<br></br>    */
	override fun glGetShaderSource(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		source: ByteArray?,
		source_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (source != null && source.size <= source_offset) throw RuntimeException("array offset argument \"source_offset\" (" + source_offset + ") equals or exceeds array length (" + source.size + ")")
		glGetShaderSource1(
			shader,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			source,
			source_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `const GLubyte *  glGetString(GLenum name)`<br></br>    */
	override fun glGetString(name: Int): OpusByteBuffer? {
		val _res = glGetString1(name) ?: return null
		Buffers.nativeOrder(_res)
		return OpusByteBuffer(_res)
	}

	/** Entry point to C language function: `const GLubyte *  glGetString(GLenum name)`<br></br>    */
	private external fun glGetString1(name: Int): ByteBuffer?

	/** Interface to C language function: <br></br> `void glGetTexParameterfv(GLenum target, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetTexParameterfv(target: Int, pname: Int, params: OpusFloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetTexParameterfv1(
			target,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetTexParameterfv(GLenum target, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetTexParameterfv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetTexParameterfv(GLenum target, GLenum pname, GLfloat *  params)`<br></br>    */
	override fun glGetTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetTexParameterfv1(target, pname, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetTexParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetTexParameteriv(target: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetTexParameteriv1(
			target,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetTexParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetTexParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetTexParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetTexParameteriv1(target, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetUniformfv(GLuint program, GLint location, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetUniformfv(program: Int, location: Int, params: OpusFloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetUniformfv1(
			program,
			location,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetUniformfv(GLuint program, GLint location, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetUniformfv1(
		program: Int,
		location: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetUniformfv(GLuint program, GLint location, GLfloat *  params)`<br></br>    */
	override fun glGetUniformfv(program: Int, location: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetUniformfv1(program, location, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetUniformiv(GLuint program, GLint location, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetUniformiv(program: Int, location: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetUniformiv1(
			program,
			location,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetUniformiv(GLuint program, GLint location, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetUniformiv1(
		program: Int,
		location: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetUniformiv(GLuint program, GLint location, GLint *  params)`<br></br>    */
	override fun glGetUniformiv(program: Int, location: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetUniformiv1(program, location, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `GLint glGetUniformLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetUniformLocation(program: Int, name: OpusByteBuffer?): Int {
		val name_is_direct: Boolean = Buffers.isDirect(name?.buf)
		return glGetUniformLocation1(
			program,
			if (name_is_direct) name?.buf else Buffers.getArray(name?.buf),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name?.buf) else Buffers.getIndirectBufferByteOffset(
				name?.buf
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `GLint glGetUniformLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetUniformLocation1(
		program: Int,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	): Int

	/** Interface to C language function: <br></br> `GLint glGetUniformLocation(GLuint program, const GLchar *  name)`<br></br>    */
	override fun glGetUniformLocation(program: Int, name: ByteArray?, name_offset: Int): Int {
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		return glGetUniformLocation1(program, name, name_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetVertexAttribfv(GLuint index, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetVertexAttribfv(index: Int, pname: Int, params: OpusFloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetVertexAttribfv1(
			index,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetVertexAttribfv(GLuint index, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetVertexAttribfv1(
		index: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetVertexAttribfv(GLuint index, GLenum pname, GLfloat *  params)`<br></br>    */
	override fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetVertexAttribfv1(index, pname, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetVertexAttribiv(GLuint index, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetVertexAttribiv(index: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glGetVertexAttribiv1(
			index,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetVertexAttribiv(GLuint index, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetVertexAttribiv1(
		index: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetVertexAttribiv(GLuint index, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetVertexAttribiv(index: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetVertexAttribiv1(index, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

//	/** Interface to C language function: <br></br> `void glGetVertexAttribPointerv(GLuint index, GLenum pname, void *  *  pointer)`<br></br>
//	 * @param pointer a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
//	 */
//	override fun glGetVertexAttribPointerv(index: Int, pname: Int, pointer: PointerBuffer?) {
//		val pointer_is_direct: Boolean = Buffers.isDirect(pointer)
//		glGetVertexAttribPointerv1(
//			index,
//			pname,
//			if (pointer_is_direct) (if (pointer != null) pointer.getBuffer() else null) else Buffers.getArray(
//				pointer
//			),
//			if (pointer_is_direct) Buffers.getDirectBufferByteOffset(pointer) else Buffers.getIndirectBufferByteOffset(
//				pointer
//			),
//			pointer_is_direct
//		)
//	}

	/** Entry point to C language function: `void glGetVertexAttribPointerv(GLuint index, GLenum pname, void *  *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 */
	private external fun glGetVertexAttribPointerv1(
		index: Int,
		pname: Int,
		pointer: Any?,
		pointer_byte_offset: Int,
		pointer_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glHint(GLenum target, GLenum mode)`<br></br>    */
	external override fun glHint(target: Int, mode: Int)

	/** Interface to C language function: <br></br> `GLboolean glIsBuffer(GLuint buffer)`<br></br>    */
	external override fun glIsBuffer(buffer: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsEnabled(GLenum cap)`<br></br>    */
	external override fun glIsEnabled(cap: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsFramebuffer(GLuint framebuffer)`<br></br>    */
	external override fun glIsFramebuffer(framebuffer: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsProgram(GLuint program)`<br></br>    */
	external override fun glIsProgram(program: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsRenderbuffer(GLuint renderbuffer)`<br></br>    */
	external override fun glIsRenderbuffer(renderbuffer: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsShader(GLuint shader)`<br></br>    */
	external override fun glIsShader(shader: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsTexture(GLuint texture)`<br></br>    */
	external override fun glIsTexture(texture: Int): Byte

	/** Interface to C language function: <br></br> `void glLineWidth(GLfloat width)`<br></br>    */
	external override fun glLineWidth(width: Float)

	/** Interface to C language function: <br></br> `void glLinkProgram(GLuint program)`<br></br>    */
	external override fun glLinkProgram(program: Int)

	/** Interface to C language function: <br></br> `void glPixelStorei(GLenum pname, GLint param)`<br></br>    */
	external override fun glPixelStorei(pname: Int, param: Int)

	/** Interface to C language function: <br></br> `void glPolygonOffset(GLfloat factor, GLfloat units)`<br></br>    */
	external override fun glPolygonOffset(factor: Float, units: Float)

	/** Interface to C language function: <br></br> `void glReadPixels(GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	override fun glReadPixels(
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: OpusBuffer
	) {
		val pixels_is_direct: Boolean = Buffers.isDirect(pixels.buf)
		glReadPixels1(
			x,
			y,
			width,
			height,
			format,
			type,
			if (pixels_is_direct) pixels.buf else Buffers.getArray(pixels.buf),
			if (pixels_is_direct) Buffers.getDirectBufferByteOffset(pixels.buf) else Buffers.getIndirectBufferByteOffset(
				pixels.buf
			),
			pixels_is_direct
		)
	}

	/** Entry point to C language function: `void glReadPixels(GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glReadPixels1(
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: Any,
		pixels_byte_offset: Int,
		pixels_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glReleaseShaderCompiler()`<br></br>    */
	external override fun glReleaseShaderCompiler()

	/** Interface to C language function: <br></br> `void glRenderbufferStorage(GLenum target, GLenum internalformat, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glRenderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int)

	/** Interface to C language function: <br></br> `void glSampleCoverage(GLfloat value, GLboolean invert)`<br></br>    */
	external override fun glSampleCoverage(value: Float, invert: Byte)

	/** Interface to C language function: <br></br> `void glScissor(GLint x, GLint y, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glScissor(x: Int, y: Int, width: Int, height: Int)

	/** Interface to C language function: <br></br> `void glShaderBinary(GLsizei count, const GLuint *  shaders, GLenum binaryFormat, const void *  binary, GLsizei length)`<br></br>
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 * @param binary a direct or array-backed [java.nio.Buffer]
	 */
	override fun glShaderBinary(
		count: Int,
		shaders: OpusIntBuffer?,
		binaryFormat: Int,
		binary: OpusBuffer,
		length: Int
	) {
		val shaders_is_direct: Boolean = Buffers.isDirect(shaders?.buf)
		val binary_is_direct: Boolean = Buffers.isDirect(binary.buf)
		glShaderBinary1(
			count,
			if (shaders_is_direct) shaders?.buf else Buffers.getArray(shaders?.buf),
			if (shaders_is_direct) Buffers.getDirectBufferByteOffset(shaders?.buf) else Buffers.getIndirectBufferByteOffset(
				shaders?.buf
			),
			shaders_is_direct,
			binaryFormat,
			if (binary_is_direct) binary.buf else Buffers.getArray(binary.buf),
			if (binary_is_direct) Buffers.getDirectBufferByteOffset(binary.buf) else Buffers.getIndirectBufferByteOffset(
				binary.buf
			),
			binary_is_direct,
			length
		)
	}

	/** Entry point to C language function: `void glShaderBinary(GLsizei count, const GLuint *  shaders, GLenum binaryFormat, const void *  binary, GLsizei length)`<br></br>
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 * @param binary a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glShaderBinary1(
		count: Int,
		shaders: Any?,
		shaders_byte_offset: Int,
		shaders_is_direct: Boolean,
		binaryFormat: Int,
		binary: Any,
		binary_byte_offset: Int,
		binary_is_direct: Boolean,
		length: Int
	)

	/** Interface to C language function: <br></br> `void glShaderBinary(GLsizei count, const GLuint *  shaders, GLenum binaryFormat, const void *  binary, GLsizei length)`<br></br>
	 * @param binary a direct or array-backed [java.nio.Buffer]
	 */
	override fun glShaderBinary(
		count: Int,
		shaders: IntArray?,
		shaders_offset: Int,
		binaryFormat: Int,
		binary: OpusBuffer,
		length: Int
	) {
		if (shaders != null && shaders.size <= shaders_offset) throw RuntimeException("array offset argument \"shaders_offset\" (" + shaders_offset + ") equals or exceeds array length (" + shaders.size + ")")
		val binary_is_direct: Boolean = Buffers.isDirect(binary.buf)
		glShaderBinary1(
			count,
			shaders,
			Buffers.SIZEOF_INT * shaders_offset,
			false,
			binaryFormat,
			if (binary_is_direct) binary.buf else Buffers.getArray(binary.buf),
			if (binary_is_direct) Buffers.getDirectBufferByteOffset(binary.buf) else Buffers.getIndirectBufferByteOffset(
				binary.buf
			),
			binary_is_direct,
			length
		)
	}

//	/** Interface to C language function: <br></br> `void glShaderSource(GLuint shader, GLsizei count, const GLchar * const  *  string, const GLint *  length)`<br></br>
//	 * @param string a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
//	 * @param length a direct or array-backed [java.nio.IntBuffer]
//	 */
//	override fun glShaderSource(shader: Int, count: Int, string: PointerBuffer?, length: OpusIntBuffer?) {
//		val string_is_direct: Boolean = Buffers.isDirect(string)
//		val length_is_direct: Boolean = Buffers.isDirect(length)
//		glShaderSource1(
//			shader,
//			count,
//			if (string_is_direct) (if (string != null) string.getBuffer() else null) else Buffers.getArray(
//				string
//			),
//			if (string_is_direct) Buffers.getDirectBufferByteOffset(string) else Buffers.getIndirectBufferByteOffset(
//				string
//			),
//			string_is_direct,
//			if (length_is_direct) length else Buffers.getArray(length),
//			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
//				length
//			),
//			length_is_direct
//		)
//	}


	// TODO: This function was temporarily modified. I'm not sure if it works correctly.
	override fun glShaderSource(shader: Int, count: Int, string: OpusByteBuffer?, length: OpusIntBuffer?) {
		val pointerString = PointerBuffer.wrap(string?.buf)
		val string_is_direct: Boolean = Buffers.isDirect(pointerString)
		val length_is_direct: Boolean = Buffers.isDirect(length?.buf)
		glShaderSource1(
			shader,
			count,
			if (string_is_direct) (if (pointerString != null) pointerString.buffer else null) else Buffers.getArray(
				pointerString
			),
			if (string_is_direct) Buffers.getDirectBufferByteOffset(pointerString) else Buffers.getIndirectBufferByteOffset(
				pointerString
			),
			string_is_direct,
			if (length_is_direct) length?.buf else Buffers.getArray(length?.buf),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length?.buf) else Buffers.getIndirectBufferByteOffset(
				length?.buf
			),
			length_is_direct
		)
	}

	/** Entry point to C language function: `void glShaderSource(GLuint shader, GLsizei count, const GLchar * const  *  string, const GLint *  length)`<br></br>
	 * @param string a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glShaderSource1(
		shader: Int,
		count: Int,
		string: Any?,
		string_byte_offset: Int,
		string_is_direct: Boolean,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean
	)

//	/** Interface to C language function: <br></br> `void glShaderSource(GLuint shader, GLsizei count, const GLchar * const  *  string, const GLint *  length)`<br></br>
//	 * @param string a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
//	 */
//	override fun glShaderSource(
//		shader: Int,
//		count: Int,
//		string: PointerBuffer?,
//		length: IntArray?,
//		length_offset: Int
//	) {
//		val string_is_direct: Boolean = Buffers.isDirect(string)
//		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
//		glShaderSource1(
//			shader,
//			count,
//			if (string_is_direct) (if (string != null) string.getBuffer() else null) else Buffers.getArray(
//				string
//			),
//			if (string_is_direct) Buffers.getDirectBufferByteOffset(string) else Buffers.getIndirectBufferByteOffset(
//				string
//			),
//			string_is_direct,
//			length,
//			Buffers.SIZEOF_INT * length_offset,
//			false
//		)
//	}

	/** Interface to C language function: <br></br> `void glStencilFunc(GLenum func, GLint ref, GLuint mask)`<br></br>    */
	external override fun glStencilFunc(func: Int, ref: Int, mask: Int)

	/** Interface to C language function: <br></br> `void glStencilFuncSeparate(GLenum face, GLenum func, GLint ref, GLuint mask)`<br></br>    */
	external override fun glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int)

	/** Interface to C language function: <br></br> `void glStencilMask(GLuint mask)`<br></br>    */
	external override fun glStencilMask(mask: Int)

	/** Interface to C language function: <br></br> `void glStencilMaskSeparate(GLenum face, GLuint mask)`<br></br>    */
	external override fun glStencilMaskSeparate(face: Int, mask: Int)

	/** Interface to C language function: <br></br> `void glStencilOp(GLenum fail, GLenum zfail, GLenum zpass)`<br></br>    */
	external override fun glStencilOp(fail: Int, zfail: Int, zpass: Int)

	/** Interface to C language function: <br></br> `void glStencilOpSeparate(GLenum face, GLenum sfail, GLenum dpfail, GLenum dppass)`<br></br>    */
	external override fun glStencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int)

	/** Interface to C language function: <br></br> `void glTexImage2D(GLenum target, GLint level, GLint internalformat, GLsizei width, GLsizei height, GLint border, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	override fun glTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		format: Int,
		type: Int,
		pixels: OpusBuffer
	) {
		val pixels_is_direct: Boolean = Buffers.isDirect(pixels.buf)
		glTexImage2D1(
			target,
			level,
			internalformat,
			width,
			height,
			border,
			format,
			type,
			if (pixels_is_direct) pixels.buf else Buffers.getArray(pixels.buf),
			if (pixels_is_direct) Buffers.getDirectBufferByteOffset(pixels.buf) else Buffers.getIndirectBufferByteOffset(
				pixels.buf
			),
			pixels_is_direct
		)
	}

	/** Entry point to C language function: `void glTexImage2D(GLenum target, GLint level, GLint internalformat, GLsizei width, GLsizei height, GLint border, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glTexImage2D1(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		format: Int,
		type: Int,
		pixels: Any,
		pixels_byte_offset: Int,
		pixels_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glTexParameterf(GLenum target, GLenum pname, GLfloat param)`<br></br>    */
	external override fun glTexParameterf(target: Int, pname: Int, param: Float)

	/** Interface to C language function: <br></br> `void glTexParameterfv(GLenum target, GLenum pname, const GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glTexParameterfv(target: Int, pname: Int, params: OpusFloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glTexParameterfv1(
			target,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glTexParameterfv(GLenum target, GLenum pname, const GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glTexParameterfv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glTexParameterfv(GLenum target, GLenum pname, const GLfloat *  params)`<br></br>    */
	override fun glTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glTexParameterfv1(target, pname, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glTexParameteri(GLenum target, GLenum pname, GLint param)`<br></br>    */
	external override fun glTexParameteri(target: Int, pname: Int, param: Int)

	/** Interface to C language function: <br></br> `void glTexParameteriv(GLenum target, GLenum pname, const GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glTexParameteriv(target: Int, pname: Int, params: OpusIntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params?.buf)
		glTexParameteriv1(
			target,
			pname,
			if (params_is_direct) params?.buf else Buffers.getArray(params?.buf),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params?.buf) else Buffers.getIndirectBufferByteOffset(
				params?.buf
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glTexParameteriv(GLenum target, GLenum pname, const GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glTexParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glTexParameteriv(GLenum target, GLenum pname, const GLint *  params)`<br></br>    */
	override fun glTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glTexParameteriv1(target, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	override fun glTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: OpusBuffer
	) {
		val pixels_is_direct: Boolean = Buffers.isDirect(pixels.buf)
		glTexSubImage2D1(
			target,
			level,
			xoffset,
			yoffset,
			width,
			height,
			format,
			type,
			if (pixels_is_direct) pixels.buf else Buffers.getArray(pixels.buf),
			if (pixels_is_direct) Buffers.getDirectBufferByteOffset(pixels.buf) else Buffers.getIndirectBufferByteOffset(
				pixels.buf
			),
			pixels_is_direct
		)
	}

	/** Entry point to C language function: `void glTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glTexSubImage2D1(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: Any,
		pixels_byte_offset: Int,
		pixels_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform1f(GLint location, GLfloat v0)`<br></br>    */
	external override fun glUniform1f(location: Int, v0: Float)

	/** Interface to C language function: <br></br> `void glUniform1fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform1fv(location: Int, count: Int, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform1fv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform1fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform1fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform1fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform1fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform1fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform1i(GLint location, GLint v0)`<br></br>    */
	external override fun glUniform1i(location: Int, v0: Int)

	/** Interface to C language function: <br></br> `void glUniform1iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform1iv(location: Int, count: Int, value: OpusIntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform1iv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform1iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform1iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform1iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform1iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform1iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform2f(GLint location, GLfloat v0, GLfloat v1)`<br></br>    */
	external override fun glUniform2f(location: Int, v0: Float, v1: Float)

	/** Interface to C language function: <br></br> `void glUniform2fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform2fv(location: Int, count: Int, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform2fv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform2fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform2fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform2fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform2fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform2fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform2i(GLint location, GLint v0, GLint v1)`<br></br>    */
	external override fun glUniform2i(location: Int, v0: Int, v1: Int)

	/** Interface to C language function: <br></br> `void glUniform2iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform2iv(location: Int, count: Int, value: OpusIntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform2iv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform2iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform2iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform2iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform2iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform2iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform3f(GLint location, GLfloat v0, GLfloat v1, GLfloat v2)`<br></br>    */
	external override fun glUniform3f(location: Int, v0: Float, v1: Float, v2: Float)

	/** Interface to C language function: <br></br> `void glUniform3fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform3fv(location: Int, count: Int, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform3fv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform3fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform3fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform3fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform3fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform3fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform3i(GLint location, GLint v0, GLint v1, GLint v2)`<br></br>    */
	external override fun glUniform3i(location: Int, v0: Int, v1: Int, v2: Int)

	/** Interface to C language function: <br></br> `void glUniform3iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform3iv(location: Int, count: Int, value: OpusIntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform3iv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform3iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform3iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform3iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform3iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform3iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform4f(GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3)`<br></br>    */
	external override fun glUniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float)

	/** Interface to C language function: <br></br> `void glUniform4fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform4fv(location: Int, count: Int, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform4fv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform4fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform4fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform4fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform4fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform4fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform4i(GLint location, GLint v0, GLint v1, GLint v2, GLint v3)`<br></br>    */
	external override fun glUniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int)

	/** Interface to C language function: <br></br> `void glUniform4iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform4iv(location: Int, count: Int, value: OpusIntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniform4iv1(
			location,
			count,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform4iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform4iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform4iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform4iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform4iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniformMatrix2fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniformMatrix2fv(location: Int, count: Int, transpose: Byte, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniformMatrix2fv1(
			location,
			count,
			transpose,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniformMatrix2fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniformMatrix2fv1(
		location: Int,
		count: Int,
		transpose: Byte,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniformMatrix2fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>    */
	override fun glUniformMatrix2fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniformMatrix2fv1(
			location,
			count,
			transpose,
			value,
			Buffers.SIZEOF_FLOAT * value_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glUniformMatrix3fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniformMatrix3fv(location: Int, count: Int, transpose: Byte, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniformMatrix3fv1(
			location,
			count,
			transpose,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniformMatrix3fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniformMatrix3fv1(
		location: Int,
		count: Int,
		transpose: Byte,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniformMatrix3fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>    */
	override fun glUniformMatrix3fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniformMatrix3fv1(
			location,
			count,
			transpose,
			value,
			Buffers.SIZEOF_FLOAT * value_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glUniformMatrix4fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniformMatrix4fv(location: Int, count: Int, transpose: Byte, value: OpusFloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value?.buf)
		glUniformMatrix4fv1(
			location,
			count,
			transpose,
			if (value_is_direct) value?.buf else Buffers.getArray(value?.buf),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value?.buf) else Buffers.getIndirectBufferByteOffset(
				value?.buf
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniformMatrix4fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniformMatrix4fv1(
		location: Int,
		count: Int,
		transpose: Byte,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniformMatrix4fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>    */
	override fun glUniformMatrix4fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniformMatrix4fv1(
			location,
			count,
			transpose,
			value,
			Buffers.SIZEOF_FLOAT * value_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glUseProgram(GLuint program)`<br></br>    */
	external override fun glUseProgram(program: Int)

	/** Interface to C language function: <br></br> `void glValidateProgram(GLuint program)`<br></br>    */
	external override fun glValidateProgram(program: Int)

	/** Interface to C language function: <br></br> `void glVertexAttrib1f(GLuint index, GLfloat x)`<br></br>    */
	external override fun glVertexAttrib1f(index: Int, x: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib1fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib1fv(index: Int, v: OpusFloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v?.buf)
		glVertexAttrib1fv1(
			index,
			if (v_is_direct) v?.buf else Buffers.getArray(v?.buf),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v?.buf) else Buffers.getIndirectBufferByteOffset(
				v?.buf
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib1fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib1fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib1fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib1fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib1fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttrib2f(GLuint index, GLfloat x, GLfloat y)`<br></br>    */
	external override fun glVertexAttrib2f(index: Int, x: Float, y: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib2fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib2fv(index: Int, v: OpusFloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v?.buf)
		glVertexAttrib2fv1(
			index,
			if (v_is_direct) v?.buf else Buffers.getArray(v?.buf),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v?.buf) else Buffers.getIndirectBufferByteOffset(
				v?.buf
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib2fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib2fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib2fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib2fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib2fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttrib3f(GLuint index, GLfloat x, GLfloat y, GLfloat z)`<br></br>    */
	external override fun glVertexAttrib3f(index: Int, x: Float, y: Float, z: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib3fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib3fv(index: Int, v: OpusFloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v?.buf)
		glVertexAttrib3fv1(
			index,
			if (v_is_direct) v?.buf else Buffers.getArray(v?.buf),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v?.buf) else Buffers.getIndirectBufferByteOffset(
				v?.buf
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib3fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib3fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib3fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib3fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib3fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttrib4f(GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w)`<br></br>    */
	external override fun glVertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib4fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib4fv(index: Int, v: OpusFloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v?.buf)
		glVertexAttrib4fv1(
			index,
			if (v_is_direct) v?.buf else Buffers.getArray(v?.buf),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v?.buf) else Buffers.getIndirectBufferByteOffset(
				v?.buf
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib4fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib4fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib4fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib4fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib4fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttribPointer(GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const void *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [java.nio.Buffer]
	 */
	override fun glVertexAttribPointer(
		index: Int,
		size: Int,
		type: Int,
		normalized: Byte,
		stride: Int,
		pointer: OpusBuffer
	) {
		
		val pointer_is_direct: Boolean = Buffers.isDirect(pointer.buf)
		glVertexAttribPointer1(
			index,
			size,
			type,
			normalized,
			stride,
			if (pointer_is_direct) pointer.buf else Buffers.getArray(pointer.buf),
			if (pointer_is_direct) Buffers.getDirectBufferByteOffset(pointer.buf) else Buffers.getIndirectBufferByteOffset(
				pointer.buf
			),
			pointer_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttribPointer(GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const void *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glVertexAttribPointer1(
		index: Int,
		size: Int,
		type: Int,
		normalized: Byte,
		stride: Int,
		pointer: Any,
		pointer_byte_offset: Int,
		pointer_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glViewport(GLint x, GLint y, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glViewport(x: Int, y: Int, width: Int, height: Int)
} // end of class AngleNative


//class AngleNative {
//	@Suppress("UnsafeDynamicallyLoadedCode")
//	companion object {
//		init {
//			val baseDir = System.getProperty("user.dir")
//			val dylibPath = "$baseDir/../../kotlinangle/build/libs/libPrismAngleLibrary.dylib"
//			println("Attempting to load .dylib from: $dylibPath")
//			if (File(dylibPath).exists()) {
//				System.load(dylibPath)
//			} else {
//				throw UnsatisfiedLinkError("Library not found at: $dylibPath")
//			}
//			//System.loadLibrary("PrismAngleLibrary")
//		}
//	}
//
//	external override fun createPrismAngle(): Long
//	external override fun fillScreenRGBAngle(nativePtr: Long, red: Float, green: Float, blue: Float): Int
//	external override fun testCallingAngle(): Int
//	external override fun makeAngleContextCurrent(nativePtr: Long): Int
//}