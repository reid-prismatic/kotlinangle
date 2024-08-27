package org.prismatic.kotlinangle

import org.prismatic.kotlinangle.OpusBuffer.OpusBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusByteBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusFloatBuffer
import org.prismatic.kotlinangle.OpusBuffer.OpusIntBuffer

interface AngleWrapper {

	fun glActiveTexture(texture: Int)

	fun glAttachShader(program: Int, shader: Int)

	fun glBindAttribLocation(program: Int, index: Int, name: OpusByteBuffer?)

	fun glBindAttribLocation(program: Int, index: Int, name: ByteArray?, name_offset: Int)

	fun glBindBuffer(target: Int, buffer: Int)

	fun glBindFramebuffer(target: Int, framebuffer: Int)

	fun glBindRenderbuffer(target: Int, renderbuffer: Int)

	fun glBindTexture(target: Int, texture: Int)

	fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float)

	fun glBlendEquation(mode: Int)

	fun glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int)

	fun glBlendFunc(sfactor: Int, dfactor: Int)

	fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int)

	fun glBufferData(target: Int, size: Int, data: OpusBuffer, usage: Int)

	fun glBufferSubData(target: Int, offset: Int, size: Int, data: OpusBuffer)

	fun glCheckFramebufferStatus(target: Int): Int

	fun glClear(mask: Int)

	fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float)

	fun glClearDepthf(d: Float)

	fun glClearStencil(s: Int)

	fun glColorMask(red: Byte, green: Byte, blue: Byte, alpha: Byte)

	fun glCompileShader(shader: Int)

	fun glCompressedTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int, imageSize: Int, data: OpusBuffer)

	fun glCompressedTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: Int, imageSize: Int, data: OpusBuffer)

	fun glCopyTexImage2D(target: Int, level: Int, internalformat: Int, x: Int, y: Int, width: Int, height: Int, border: Int)

	fun glCopyTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, x: Int, y: Int, width: Int, height: Int)

	fun glCreateProgram(): Int

	fun glCreateShader(type: Int): Int

	fun glCullFace(mode: Int)

	fun glDeleteBuffers(n: Int, buffers: OpusIntBuffer?)

	fun glDeleteBuffers(n: Int, buffers: IntArray?, buffers_offset: Int)

	fun glDeleteFramebuffers(n: Int, framebuffers: OpusIntBuffer?)

	fun glDeleteFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int)

	fun glDeleteProgram(program: Int)

	fun glDeleteRenderbuffers(n: Int, renderbuffers: OpusIntBuffer?)

	fun glDeleteRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int)

	fun glDeleteShader(shader: Int)

	fun glDeleteTextures(n: Int, textures: OpusIntBuffer?)

	fun glDeleteTextures(n: Int, textures: IntArray?, textures_offset: Int)

	fun glDepthFunc(func: Int)

	fun glDepthMask(flag: Byte)

	fun glDepthRangef(n: Float, f: Float)

	fun glDetachShader(program: Int, shader: Int)

	fun glDisable(cap: Int)

	fun glDisableVertexAttribArray(index: Int)

	fun glDrawArrays(mode: Int, first: Int, count: Int)

	fun glDrawElements(mode: Int, count: Int, type: Int, indices: OpusBuffer)

	fun glEnable(cap: Int)

	fun glEnableVertexAttribArray(index: Int)

	fun glFinish()

	fun glFlush()

	fun glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Int)

	fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int, level: Int)

	fun glFrontFace(mode: Int)

	fun glGenBuffers(n: Int, buffers: OpusIntBuffer?)

	fun glGenBuffers(n: Int, buffers: IntArray?, buffers_offset: Int)

	fun glGenerateMipmap(target: Int)

	fun glGenFramebuffers(n: Int, framebuffers: OpusIntBuffer?)

	fun glGenFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int)

	fun glGenRenderbuffers(n: Int, renderbuffers: OpusIntBuffer?)

	fun glGenRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int)

	fun glGenTextures(n: Int, textures: OpusIntBuffer?)
	fun glGenTextures(n: Int, textures: IntArray?, textures_offset: Int)
	fun glGetActiveAttrib(
		program: Int,
		index: Int,
		bufSize: Int,
		length: OpusIntBuffer?,
		size: OpusIntBuffer?,
		type: OpusIntBuffer?,
		name: OpusByteBuffer?
	)
	fun glGetActiveAttrib(
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
	)
	fun glGetActiveUniform(
		program: Int,
		index: Int,
		bufSize: Int,
		length: OpusIntBuffer?,
		size: OpusIntBuffer?,
		type: OpusIntBuffer?,
		name: OpusByteBuffer?
	)
	fun glGetActiveUniform(
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
	)
	fun glGetAttachedShaders(
		program: Int,
		maxCount: Int,
		count: OpusIntBuffer?,
		shaders: OpusIntBuffer?
	)
	fun glGetAttachedShaders(
		program: Int,
		maxCount: Int,
		count: IntArray?,
		count_offset: Int,
		shaders: IntArray?,
		shaders_offset: Int
	)
	fun glGetAttribLocation(program: Int, name: OpusByteBuffer?): Int
	fun glGetAttribLocation(program: Int, name: ByteArray?, name_offset: Int): Int
	fun glGetBooleanv(pname: Int, data: OpusByteBuffer?)
	fun glGetBooleanv(pname: Int, data: ByteArray?, data_offset: Int)
	fun glGetBufferParameteriv(target: Int, pname: Int, params: OpusIntBuffer?)
	fun glGetBufferParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetError(): Int
	fun glGetFloatv(pname: Int, data: OpusFloatBuffer?)
	fun glGetFloatv(pname: Int, data: FloatArray?, data_offset: Int)
	fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: OpusIntBuffer?
	)
	fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: IntArray?,
		params_offset: Int
	)
	fun glGetIntegerv(pname: Int, data: OpusIntBuffer?)
	fun glGetIntegerv(pname: Int, data: IntArray?, data_offset: Int)
	fun glGetProgramiv(program: Int, pname: Int, params: OpusIntBuffer?)
	fun glGetProgramiv(program: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetProgramInfoLog(program: Int, bufSize: Int, length: OpusIntBuffer?, infoLog: OpusByteBuffer?)
	fun glGetProgramInfoLog(
		program: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	)
	fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: OpusIntBuffer?)
	fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetShaderiv(shader: Int, pname: Int, params: OpusIntBuffer?)
	fun glGetShaderiv(shader: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetShaderInfoLog(shader: Int, bufSize: Int, length: OpusIntBuffer?, infoLog: OpusByteBuffer?)
	fun glGetShaderInfoLog(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	)
	fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: OpusIntBuffer?,
		precision: OpusIntBuffer?
	)
	fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: IntArray?,
		range_offset: Int,
		precision: IntArray?,
		precision_offset: Int
	)
	fun glGetShaderSource(shader: Int, bufSize: Int, length: OpusIntBuffer?, source: OpusByteBuffer?)
	fun glGetShaderSource(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		source: ByteArray?,
		source_offset: Int
	)
	fun glGetString(name: Int): OpusByteBuffer?
	fun glGetTexParameterfv(target: Int, pname: Int, params: OpusFloatBuffer?)
	fun glGetTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int)
	fun glGetTexParameteriv(target: Int, pname: Int, params: OpusIntBuffer?)
	fun glGetTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetUniformfv(program: Int, location: Int, params: OpusFloatBuffer?)
	fun glGetUniformfv(program: Int, location: Int, params: FloatArray?, params_offset: Int)
	fun glGetUniformiv(program: Int, location: Int, params: OpusIntBuffer?)
	fun glGetUniformiv(program: Int, location: Int, params: IntArray?, params_offset: Int)
	fun glGetUniformLocation(program: Int, name: OpusByteBuffer?): Int
	fun glGetUniformLocation(program: Int, name: ByteArray?, name_offset: Int): Int
	fun glGetVertexAttribfv(index: Int, pname: Int, params: OpusFloatBuffer?)
	fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatArray?, params_offset: Int)
	fun glGetVertexAttribiv(index: Int, pname: Int, params: OpusIntBuffer?)
	fun glGetVertexAttribiv(index: Int, pname: Int, params: IntArray?, params_offset: Int)
//	fun glGetVertexAttribPointerv(index: Int, pname: Int, pointer: PointerBuffer?)
	fun glHint(target: Int, mode: Int)
	fun glIsBuffer(buffer: Int): Byte
	fun glIsEnabled(cap: Int): Byte
	fun glIsFramebuffer(framebuffer: Int): Byte
	fun glIsProgram(program: Int): Byte
	fun glIsRenderbuffer(renderbuffer: Int): Byte
	fun glIsShader(shader: Int): Byte
	fun glIsTexture(texture: Int): Byte
	fun glLineWidth(width: Float)
	fun glLinkProgram(program: Int)
	fun glPixelStorei(pname: Int, param: Int)
	fun glPolygonOffset(factor: Float, units: Float)
	fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: OpusBuffer)
	fun glReleaseShaderCompiler()
	fun glRenderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int)
	fun glSampleCoverage(value: Float, invert: Byte)
	fun glScissor(x: Int, y: Int, width: Int, height: Int)
	fun glShaderBinary(
		count: Int,
		shaders: OpusIntBuffer?,
		binaryFormat: Int,
		binary: OpusBuffer,
		length: Int
	)
	fun glShaderBinary(
		count: Int,
		shaders: IntArray?,
		shaders_offset: Int,
		binaryFormat: Int,
		binary: OpusBuffer,
		length: Int
	)
//	fun glShaderSource(shader: Int, count: Int, string: PointerBuffer?, length: OpusIntBuffer?)
	fun glShaderSource(shader: Int, count: Int, string: OpusByteBuffer?, length: OpusIntBuffer?)
//	fun glShaderSource(
//		shader: Int,
//		count: Int,
//		string: PointerBuffer?,
//		length: IntArray?,
//		length_offset: Int
//	)
	fun glStencilFunc(func: Int, ref: Int, mask: Int)
	fun glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int)
	fun glStencilMask(mask: Int)
	fun glStencilMaskSeparate(face: Int, mask: Int)
	fun glStencilOp(fail: Int, zfail: Int, zpass: Int)
	fun glStencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int)
	fun glTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		format: Int,
		type: Int,
		pixels: OpusBuffer
	)
	fun glTexParameterf(target: Int, pname: Int, param: Float)
	fun glTexParameterfv(target: Int, pname: Int, params: OpusFloatBuffer?)
	fun glTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int)
	fun glTexParameteri(target: Int, pname: Int,

	                    param: Int)
	fun glTexParameteriv(target: Int, pname: Int, params: OpusIntBuffer?)
	fun glTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: OpusBuffer
	)
	fun glUniform1f(location: Int, v0: Float)
	fun glUniform1fv(location: Int, count: Int, value: OpusFloatBuffer?)
	fun glUniform1fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform1i(location: Int, v0: Int)
	fun glUniform1iv(location: Int, count: Int, value: OpusIntBuffer?)
	fun glUniform1iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniform2f(location: Int, v0: Float, v1: Float)
	fun glUniform2fv(location: Int, count: Int, value: OpusFloatBuffer?)
	fun glUniform2fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform2i(location: Int, v0: Int, v1: Int)
	fun glUniform2iv(location: Int, count: Int, value: OpusIntBuffer?)
	fun glUniform2iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniform3f(location: Int, v0: Float, v1: Float, v2: Float)
	fun glUniform3fv(location: Int, count: Int, value: OpusFloatBuffer?)
	fun glUniform3fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform3i(location: Int, v0: Int, v1: Int, v2: Int)
	fun glUniform3iv(location: Int, count: Int, value: OpusIntBuffer?)
	fun glUniform3iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float)
	fun glUniform4fv(location: Int, count: Int, value: OpusFloatBuffer?)
	fun glUniform4fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int)
	fun glUniform4iv(location: Int, count: Int, value: OpusIntBuffer?)
	fun glUniform4iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniformMatrix2fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: OpusFloatBuffer?
	)
	fun glUniformMatrix2fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	)
	fun glUniformMatrix3fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: OpusFloatBuffer?
	)
	fun glUniformMatrix3fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	)
	fun glUniformMatrix4fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: OpusFloatBuffer?
	)
	fun glUniformMatrix4fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	)
	fun glUseProgram(program: Int)
	fun glValidateProgram(program: Int)
	fun glVertexAttrib1f(index: Int, x: Float)
	fun glVertexAttrib1fv(index: Int, v: OpusFloatBuffer?)
	fun glVertexAttrib1fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttrib2f(index: Int, x: Float, y: Float)
	fun glVertexAttrib2fv(index: Int, v: OpusFloatBuffer?)
	fun glVertexAttrib2fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttrib3f(index: Int, x: Float, y: Float, z: Float)
	fun glVertexAttrib3fv(index: Int, v: OpusFloatBuffer?)
	fun glVertexAttrib3fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float)
	fun glVertexAttrib4fv(index: Int, v: OpusFloatBuffer?)
	fun glVertexAttrib4fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttribPointer(
		index: Int,
		size: Int,
		type: Int,
		normalized: Byte,
		stride: Int,
		pointer: OpusBuffer
	)
	fun glViewport(x: Int, y: Int, width: Int, height: Int)

	companion object {
		/** Defined as part of enum type "khronos_boolean_enum_t" with expression '`0`', CType: int  */
		const val KHRONOS_FALSE: Int = 0x0

		/** Defined as part of enum type "khronos_boolean_enum_t" with expression '`1`', CType: int  */
		const val KHRONOS_TRUE: Int = 0x1

		/** Defined as part of enum type "khronos_boolean_enum_t" with expression '`0x7FFFFFFF`', CType: int  */
		const val KHRONOS_BOOLEAN_ENUM_FORCE_SIZE: Int = 0x7fffffff

		/** Define "GL_CW" with expression '`0x0900`', CType: int  */
		const val GL_CW: Int = 0x900

		/** Define "GL_ALIASED_POINT_SIZE_RANGE" with expression '`0x846D`', CType: int  */
		const val GL_ALIASED_POINT_SIZE_RANGE: Int = 0x846d

		/** Define "GL_MAX_CUBE_MAP_TEXTURE_SIZE" with expression '`0x851C`', CType: int  */
		const val GL_MAX_CUBE_MAP_TEXTURE_SIZE: Int = 0x851c

		/** Define "GL_LEQUAL" with expression '`0x0203`', CType: int  */
		const val GL_LEQUAL: Int = 0x203

		/** Define "GL_LOW_FLOAT" with expression '`0x8DF0`', CType: int  */
		const val GL_LOW_FLOAT: Int = 0x8df0

		/** Define "GL_ONE_MINUS_SRC_COLOR" with expression '`0x0301`', CType: int  */
		const val GL_ONE_MINUS_SRC_COLOR: Int = 0x301

		/** Define "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS" with expression '`0x8CD9`', CType: int  */
		const val GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS: Int = 0x8cd9

		/** Define "GL_ACTIVE_ATTRIBUTE_MAX_LENGTH" with expression '`0x8B8A`', CType: int  */
		const val GL_ACTIVE_ATTRIBUTE_MAX_LENGTH: Int = 0x8b8a

		/** Define "GL_SCISSOR_TEST" with expression '`0x0C11`', CType: int  */
		const val GL_SCISSOR_TEST: Int = 0xc11

		/** Define "GL_ARRAY_BUFFER_BINDING" with expression '`0x8894`', CType: int  */
		const val GL_ARRAY_BUFFER_BINDING: Int = 0x8894

		/** Define "GL_UNSIGNED_INT" with expression '`0x1405`', CType: int  */
		const val GL_UNSIGNED_INT: Int = 0x1405

		/** Define "GL_ONE_MINUS_DST_COLOR" with expression '`0x0307`', CType: int  */
		const val GL_ONE_MINUS_DST_COLOR: Int = 0x307

		/** Define "GL_DELETE_STATUS" with expression '`0x8B80`', CType: int  */
		const val GL_DELETE_STATUS: Int = 0x8b80

		/** Define "GL_GREEN_BITS" with expression '`0x0D53`', CType: int  */
		const val GL_GREEN_BITS: Int = 0xd53

		/** Define "GL_ACTIVE_UNIFORMS" with expression '`0x8B86`', CType: int  */
		const val GL_ACTIVE_UNIFORMS: Int = 0x8b86

		/** Define "GL_FRAMEBUFFER_COMPLETE" with expression '`0x8CD5`', CType: int  */
		const val GL_FRAMEBUFFER_COMPLETE: Int = 0x8cd5

		/** Define "GL_VENDOR" with expression '`0x1F00`', CType: int  */
		const val GL_VENDOR: Int = 0x1f00

		/** Define "GL_CURRENT_VERTEX_ATTRIB" with expression '`0x8626`', CType: int  */
		const val GL_CURRENT_VERTEX_ATTRIB: Int = 0x8626

		/** Define "GL_ALIASED_LINE_WIDTH_RANGE" with expression '`0x846E`', CType: int  */
		const val GL_ALIASED_LINE_WIDTH_RANGE: Int = 0x846e

		/** Define "GL_RENDERBUFFER_BINDING" with expression '`0x8CA7`', CType: int  */
		const val GL_RENDERBUFFER_BINDING: Int = 0x8ca7

		/** Define "GL_INVALID_ENUM" with expression '`0x0500`', CType: int  */
		const val GL_INVALID_ENUM: Int = 0x500

		/** Define "GL_RED_BITS" with expression '`0x0D52`', CType: int  */
		const val GL_RED_BITS: Int = 0xd52

		/** Define "GL_CCW" with expression '`0x0901`', CType: int  */
		const val GL_CCW: Int = 0x901

		/** Define "GL_SHORT" with expression '`0x1402`', CType: int  */
		const val GL_SHORT: Int = 0x1402

		/** Define "GL_VERTEX_SHADER" with expression '`0x8B31`', CType: int  */
		const val GL_VERTEX_SHADER: Int = 0x8b31

		/** Define "GL_COLOR_BUFFER_BIT" with expression '`0x00004000`', CType: int  */
		const val GL_COLOR_BUFFER_BIT: Int = 0x4000

		/** Define "GL_STENCIL_REF" with expression '`0x0B97`', CType: int  */
		const val GL_STENCIL_REF: Int = 0xb97

		/** Define "GL_INFO_LOG_LENGTH" with expression '`0x8B84`', CType: int  */
		const val GL_INFO_LOG_LENGTH: Int = 0x8b84

		/** Define "GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL" with expression '`0x8CD2`', CType: int  */
		const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL: Int = 0x8cd2

		/** Define "GL_DEPTH_ATTACHMENT" with expression '`0x8D00`', CType: int  */
		const val GL_DEPTH_ATTACHMENT: Int = 0x8d00

		/** Define "GL_SHADER_COMPILER" with expression '`0x8DFA`', CType: int  */
		const val GL_SHADER_COMPILER: Int = 0x8dfa

		/** Define "GL_TEXTURE" with expression '`0x1702`', CType: int  */
		const val GL_TEXTURE: Int = 0x1702

		/** Define "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT" with expression '`0x8CD7`', CType: int  */
		const val GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT: Int = 0x8cd7

		/** Define "GL_TEXTURE_CUBE_MAP" with expression '`0x8513`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP: Int = 0x8513

		/** Define "GL_NUM_SHADER_BINARY_FORMATS" with expression '`0x8DF9`', CType: int  */
		const val GL_NUM_SHADER_BINARY_FORMATS: Int = 0x8df9

		/** Define "GL_ALWAYS" with expression '`0x0207`', CType: int  */
		const val GL_ALWAYS: Int = 0x207

		/** Define "GL_SAMPLE_COVERAGE_VALUE" with expression '`0x80AA`', CType: int  */
		const val GL_SAMPLE_COVERAGE_VALUE: Int = 0x80aa

		/** Define "GL_INVALID_FRAMEBUFFER_OPERATION" with expression '`0x0506`', CType: int  */
		const val GL_INVALID_FRAMEBUFFER_OPERATION: Int = 0x506

		/** Define "GL_ONE_MINUS_DST_ALPHA" with expression '`0x0305`', CType: int  */
		const val GL_ONE_MINUS_DST_ALPHA: Int = 0x305

		/** Define "GL_MAX_FRAGMENT_UNIFORM_VECTORS" with expression '`0x8DFD`', CType: int  */
		const val GL_MAX_FRAGMENT_UNIFORM_VECTORS: Int = 0x8dfd

		/** Define "GL_INVERT" with expression '`0x150A`', CType: int  */
		const val GL_INVERT: Int = 0x150a

		/** Define "GL_NEAREST" with expression '`0x2600`', CType: int  */
		const val GL_NEAREST: Int = 0x2600

		/** Define "GL_RENDERBUFFER" with expression '`0x8D41`', CType: int  */
		const val GL_RENDERBUFFER: Int = 0x8d41

		/** Define "GL_MAX_VERTEX_UNIFORM_VECTORS" with expression '`0x8DFB`', CType: int  */
		const val GL_MAX_VERTEX_UNIFORM_VECTORS: Int = 0x8dfb

		/** Define "GL_STENCIL_PASS_DEPTH_PASS" with expression '`0x0B96`', CType: int  */
		const val GL_STENCIL_PASS_DEPTH_PASS: Int = 0xb96

		/** Define "GL_STENCIL_BACK_WRITEMASK" with expression '`0x8CA5`', CType: int  */
		const val GL_STENCIL_BACK_WRITEMASK: Int = 0x8ca5

		/** Define "GL_TRUE" with expression '`1`', CType: int  */
		const val GL_TRUE: Int = 0x1

		/** Define "GL_LINE_STRIP" with expression '`0x0003`', CType: int  */
		const val GL_LINE_STRIP: Int = 0x3

		/** Define "GL_LUMINANCE" with expression '`0x1909`', CType: int  */
		const val GL_LUMINANCE: Int = 0x1909

		/** Define "GL_DEPTH_TEST" with expression '`0x0B71`', CType: int  */
		const val GL_DEPTH_TEST: Int = 0xb71

		/** Define "GL_INVALID_OPERATION" with expression '`0x0502`', CType: int  */
		const val GL_INVALID_OPERATION: Int = 0x502

		/** Define "GL_FRAGMENT_SHADER" with expression '`0x8B30`', CType: int  */
		const val GL_FRAGMENT_SHADER: Int = 0x8b30

		/** Define "GL_CULL_FACE" with expression '`0x0B44`', CType: int  */
		const val GL_CULL_FACE: Int = 0xb44

		/** Define "GL_BLEND_EQUATION_ALPHA" with expression '`0x883D`', CType: int  */
		const val GL_BLEND_EQUATION_ALPHA: Int = 0x883d

		/** Define "GL_BLEND_SRC_RGB" with expression '`0x80C9`', CType: int  */
		const val GL_BLEND_SRC_RGB: Int = 0x80c9

		/** Define "GL_NO_ERROR" with expression '`0`', CType: int  */
		const val GL_NO_ERROR: Int = 0x0

		/** Define "GL_FRONT_AND_BACK" with expression '`0x0408`', CType: int  */
		const val GL_FRONT_AND_BACK: Int = 0x408

		/** Define "GL_DST_COLOR" with expression '`0x0306`', CType: int  */
		const val GL_DST_COLOR: Int = 0x306

		/** Define "GL_VIEWPORT" with expression '`0x0BA2`', CType: int  */
		const val GL_VIEWPORT: Int = 0xba2

		/** Define "GL_LESS" with expression '`0x0201`', CType: int  */
		const val GL_LESS: Int = 0x201

		/** Define "GL_POLYGON_OFFSET_FACTOR" with expression '`0x8038`', CType: int  */
		const val GL_POLYGON_OFFSET_FACTOR: Int = 0x8038

		/** Define "GL_LUMINANCE_ALPHA" with expression '`0x190A`', CType: int  */
		const val GL_LUMINANCE_ALPHA: Int = 0x190a

		/** Define "GL_SHADER_BINARY_FORMATS" with expression '`0x8DF8`', CType: int  */
		const val GL_SHADER_BINARY_FORMATS: Int = 0x8df8

		/** Define "GL_BLEND_COLOR" with expression '`0x8005`', CType: int  */
		const val GL_BLEND_COLOR: Int = 0x8005

		/** Define "GL_STENCIL_VALUE_MASK" with expression '`0x0B93`', CType: int  */
		const val GL_STENCIL_VALUE_MASK: Int = 0xb93

		/** Define "GL_DEPTH_COMPONENT" with expression '`0x1902`', CType: int  */
		const val GL_DEPTH_COMPONENT: Int = 0x1902

		/** Define "GL_TEXTURE_WRAP_S" with expression '`0x2802`', CType: int  */
		const val GL_TEXTURE_WRAP_S: Int = 0x2802

		/** Define "GL_DEPTH_CLEAR_VALUE" with expression '`0x0B73`', CType: int  */
		const val GL_DEPTH_CLEAR_VALUE: Int = 0xb73

		/** Define "GL_TEXTURE_WRAP_T" with expression '`0x2803`', CType: int  */
		const val GL_TEXTURE_WRAP_T: Int = 0x2803

		/** Define "GL_ONE" with expression '`1`', CType: int  */
		const val GL_ONE: Int = 0x1

		/** Define "GL_GREATER" with expression '`0x0204`', CType: int  */
		const val GL_GREATER: Int = 0x204

		/** Define "GL_DEPTH_FUNC" with expression '`0x0B74`', CType: int  */
		const val GL_DEPTH_FUNC: Int = 0xb74

		/** Define "GL_ELEMENT_ARRAY_BUFFER" with expression '`0x8893`', CType: int  */
		const val GL_ELEMENT_ARRAY_BUFFER: Int = 0x8893

		/** Define "GL_KEEP" with expression '`0x1E00`', CType: int  */
		const val GL_KEEP: Int = 0x1e00

		/** Define "GL_LINEAR_MIPMAP_NEAREST" with expression '`0x2701`', CType: int  */
		const val GL_LINEAR_MIPMAP_NEAREST: Int = 0x2701

		/** Define "GL_STENCIL_BACK_FAIL" with expression '`0x8801`', CType: int  */
		const val GL_STENCIL_BACK_FAIL: Int = 0x8801

		/** Define "GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME" with expression '`0x8CD1`', CType: int  */
		const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME: Int = 0x8cd1

		/** Define "GL_MAX_RENDERBUFFER_SIZE" with expression '`0x84E8`', CType: int  */
		const val GL_MAX_RENDERBUFFER_SIZE: Int = 0x84e8

		/** Define "GL_REPLACE" with expression '`0x1E01`', CType: int  */
		const val GL_REPLACE: Int = 0x1e01

		/** Define "GL_STENCIL_BACK_PASS_DEPTH_PASS" with expression '`0x8803`', CType: int  */
		const val GL_STENCIL_BACK_PASS_DEPTH_PASS: Int = 0x8803

		/** Define "GL_MEDIUM_FLOAT" with expression '`0x8DF1`', CType: int  */
		const val GL_MEDIUM_FLOAT: Int = 0x8df1

		/** Define "GL_RENDERBUFFER_STENCIL_SIZE" with expression '`0x8D55`', CType: int  */
		const val GL_RENDERBUFFER_STENCIL_SIZE: Int = 0x8d55

		/** Define "GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS" with expression '`0x8B4C`', CType: int  */
		const val GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS: Int = 0x8b4c

		/** Define "GL_FRAMEBUFFER" with expression '`0x8D40`', CType: int  */
		const val GL_FRAMEBUFFER: Int = 0x8d40

		/** Define "GL_STENCIL_BACK_VALUE_MASK" with expression '`0x8CA4`', CType: int  */
		const val GL_STENCIL_BACK_VALUE_MASK: Int = 0x8ca4

		/** Define "GL_TRIANGLES" with expression '`0x0004`', CType: int  */
		const val GL_TRIANGLES: Int = 0x4

		/** Define "GL_TEXTURE_2D" with expression '`0x0DE1`', CType: int  */
		const val GL_TEXTURE_2D: Int = 0xde1

		/** Define "GL_HIGH_FLOAT" with expression '`0x8DF2`', CType: int  */
		const val GL_HIGH_FLOAT: Int = 0x8df2

		/** Define "GL_ARRAY_BUFFER" with expression '`0x8892`', CType: int  */
		const val GL_ARRAY_BUFFER: Int = 0x8892

		/** Define "GL_STENCIL_BACK_FUNC" with expression '`0x8800`', CType: int  */
		const val GL_STENCIL_BACK_FUNC: Int = 0x8800

		/** Define "GL_FIXED" with expression '`0x140C`', CType: int  */
		const val GL_FIXED: Int = 0x140c

		/** Define "GL_DEPTH_BUFFER_BIT" with expression '`0x00000100`', CType: int  */
		const val GL_DEPTH_BUFFER_BIT: Int = 0x100

		/** Define "GL_LINEAR" with expression '`0x2601`', CType: int  */
		const val GL_LINEAR: Int = 0x2601

		/** Define "GL_ACTIVE_UNIFORM_MAX_LENGTH" with expression '`0x8B87`', CType: int  */
		const val GL_ACTIVE_UNIFORM_MAX_LENGTH: Int = 0x8b87

		/** Define "GL_RGBA" with expression '`0x1908`', CType: int  */
		const val GL_RGBA: Int = 0x1908

		/** Define "GL_NEAREST_MIPMAP_LINEAR" with expression '`0x2702`', CType: int  */
		const val GL_NEAREST_MIPMAP_LINEAR: Int = 0x2702

		/** Define "GL_RENDERBUFFER_RED_SIZE" with expression '`0x8D50`', CType: int  */
		const val GL_RENDERBUFFER_RED_SIZE: Int = 0x8d50

		/** Define "GL_SAMPLE_COVERAGE" with expression '`0x80A0`', CType: int  */
		const val GL_SAMPLE_COVERAGE: Int = 0x80a0

		/** Define "GL_MAX_TEXTURE_SIZE" with expression '`0x0D33`', CType: int  */
		const val GL_MAX_TEXTURE_SIZE: Int = 0xd33

		/** Define "GL_DYNAMIC_DRAW" with expression '`0x88E8`', CType: int  */
		const val GL_DYNAMIC_DRAW: Int = 0x88e8

		/** Define "GL_OUT_OF_MEMORY" with expression '`0x0505`', CType: int  */
		const val GL_OUT_OF_MEMORY: Int = 0x505

		/** Define "GL_EXTENSIONS" with expression '`0x1F03`', CType: int  */
		const val GL_EXTENSIONS: Int = 0x1f03

		/** Define "GL_ACTIVE_TEXTURE" with expression '`0x84E0`', CType: int  */
		const val GL_ACTIVE_TEXTURE: Int = 0x84e0

		/** Define "GL_COLOR_WRITEMASK" with expression '`0x0C23`', CType: int  */
		const val GL_COLOR_WRITEMASK: Int = 0xc23

		/** Define "GL_NONE" with expression '`0`', CType: int  */
		const val GL_NONE: Int = 0x0

		/** Define "GL_UNSIGNED_SHORT_5_6_5" with expression '`0x8363`', CType: int  */
		const val GL_UNSIGNED_SHORT_5_6_5: Int = 0x8363

		/** Define "GL_RENDERBUFFER_WIDTH" with expression '`0x8D42`', CType: int  */
		const val GL_RENDERBUFFER_WIDTH: Int = 0x8d42

		/** Define "GL_DEPTH_BITS" with expression '`0x0D56`', CType: int  */
		const val GL_DEPTH_BITS: Int = 0xd56

		/** Define "GL_SRC_ALPHA_SATURATE" with expression '`0x0308`', CType: int  */
		const val GL_SRC_ALPHA_SATURATE: Int = 0x308

		/** Define "GL_UNPACK_ALIGNMENT" with expression '`0x0CF5`', CType: int  */
		const val GL_UNPACK_ALIGNMENT: Int = 0xcf5

		/** Define "GL_COMPILE_STATUS" with expression '`0x8B81`', CType: int  */
		const val GL_COMPILE_STATUS: Int = 0x8b81

		/** Define "GL_ALPHA" with expression '`0x1906`', CType: int  */
		const val GL_ALPHA: Int = 0x1906

		/** Define "GL_BYTE" with expression '`0x1400`', CType: int  */
		const val GL_BYTE: Int = 0x1400

		/** Define "GL_NEVER" with expression '`0x0200`', CType: int  */
		const val GL_NEVER: Int = 0x200

		/** Define "GL_DONT_CARE" with expression '`0x1100`', CType: int  */
		const val GL_DONT_CARE: Int = 0x1100

		/** Define "GL_MIRRORED_REPEAT" with expression '`0x8370`', CType: int  */
		const val GL_MIRRORED_REPEAT: Int = 0x8370

		/** Define "GL_FALSE" with expression '`0`', CType: int  */
		const val GL_FALSE: Int = 0x0

		/** Define "GL_GENERATE_MIPMAP_HINT" with expression '`0x8192`', CType: int  */
		const val GL_GENERATE_MIPMAP_HINT: Int = 0x8192

		/** Define "GL_RGB5_A1" with expression '`0x8057`', CType: int  */
		const val GL_RGB5_A1: Int = 0x8057

		/** Define "GL_SAMPLER_2D" with expression '`0x8B5E`', CType: int  */
		const val GL_SAMPLER_2D: Int = 0x8b5e

		/** Define "GL_STENCIL_BACK_REF" with expression '`0x8CA3`', CType: int  */
		const val GL_STENCIL_BACK_REF: Int = 0x8ca3

		/** Define "GL_BOOL" with expression '`0x8B56`', CType: int  */
		const val GL_BOOL: Int = 0x8b56

		/** Define "GL_EQUAL" with expression '`0x0202`', CType: int  */
		const val GL_EQUAL: Int = 0x202

		/** Define "GL_VERTEX_ATTRIB_ARRAY_POINTER" with expression '`0x8645`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_POINTER: Int = 0x8645

		/** Define "GL_BUFFER_USAGE" with expression '`0x8765`', CType: int  */
		const val GL_BUFFER_USAGE: Int = 0x8765

		/** Define "GL_BACK" with expression '`0x0405`', CType: int  */
		const val GL_BACK: Int = 0x405

		/** Define "GL_TEXTURE_BINDING_CUBE_MAP" with expression '`0x8514`', CType: int  */
		const val GL_TEXTURE_BINDING_CUBE_MAP: Int = 0x8514

		/** Define "KHRONOS_MAX_ENUM" with expression '`0x7FFFFFFF`', CType: int  */
		const val KHRONOS_MAX_ENUM: Int = 0x7fffffff

		/** Define "GL_IMPLEMENTATION_COLOR_READ_TYPE" with expression '`0x8B9A`', CType: int  */
		const val GL_IMPLEMENTATION_COLOR_READ_TYPE: Int = 0x8b9a

		/** Define "GL_ONE_MINUS_SRC_ALPHA" with expression '`0x0303`', CType: int  */
		const val GL_ONE_MINUS_SRC_ALPHA: Int = 0x303

		/** Define "GL_SAMPLE_ALPHA_TO_COVERAGE" with expression '`0x809E`', CType: int  */
		const val GL_SAMPLE_ALPHA_TO_COVERAGE: Int = 0x809e

		/** Define "GL_ELEMENT_ARRAY_BUFFER_BINDING" with expression '`0x8895`', CType: int  */
		const val GL_ELEMENT_ARRAY_BUFFER_BINDING: Int = 0x8895

		/** Define "GL_INT" with expression '`0x1404`', CType: int  */
		const val GL_INT: Int = 0x1404

		/** Define "GL_VERTEX_ATTRIB_ARRAY_TYPE" with expression '`0x8625`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_TYPE: Int = 0x8625

		/** Define "GL_FRAMEBUFFER_BINDING" with expression '`0x8CA6`', CType: int  */
		const val GL_FRAMEBUFFER_BINDING: Int = 0x8ca6

		/** Define "GL_FLOAT_VEC2" with expression '`0x8B50`', CType: int  */
		const val GL_FLOAT_VEC2: Int = 0x8b50

		/** Define "GL_MEDIUM_INT" with expression '`0x8DF4`', CType: int  */
		const val GL_MEDIUM_INT: Int = 0x8df4

		/** Define "GL_NUM_COMPRESSED_TEXTURE_FORMATS" with expression '`0x86A2`', CType: int  */
		const val GL_NUM_COMPRESSED_TEXTURE_FORMATS: Int = 0x86a2

		/** Define "GL_TRIANGLE_STRIP" with expression '`0x0005`', CType: int  */
		const val GL_TRIANGLE_STRIP: Int = 0x5

		/** Define "GL_FLOAT_VEC4" with expression '`0x8B52`', CType: int  */
		const val GL_FLOAT_VEC4: Int = 0x8b52

		/** Define "GL_FLOAT_VEC3" with expression '`0x8B51`', CType: int  */
		const val GL_FLOAT_VEC3: Int = 0x8b51

		/** Define "GL_LINE_LOOP" with expression '`0x0002`', CType: int  */
		const val GL_LINE_LOOP: Int = 0x2

		/** Define "GL_DEPTH_WRITEMASK" with expression '`0x0B72`', CType: int  */
		const val GL_DEPTH_WRITEMASK: Int = 0xb72

		/** Define "GL_MAX_VARYING_VECTORS" with expression '`0x8DFC`', CType: int  */
		const val GL_MAX_VARYING_VECTORS: Int = 0x8dfc

		/** Define "GL_SAMPLE_BUFFERS" with expression '`0x80A8`', CType: int  */
		const val GL_SAMPLE_BUFFERS: Int = 0x80a8

		/** Define "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT" with expression '`0x8CD6`', CType: int  */
		const val GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT: Int = 0x8cd6

		/** Define "GL_FUNC_REVERSE_SUBTRACT" with expression '`0x800B`', CType: int  */
		const val GL_FUNC_REVERSE_SUBTRACT: Int = 0x800b

		/** Define "GL_STENCIL_BUFFER_BIT" with expression '`0x00000400`', CType: int  */
		const val GL_STENCIL_BUFFER_BIT: Int = 0x400

		/** Define "GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE" with expression '`0x8CD3`', CType: int  */
		const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE: Int = 0x8cd3

		/** Define "GL_SCISSOR_BOX" with expression '`0x0C10`', CType: int  */
		const val GL_SCISSOR_BOX: Int = 0xc10

		/** Define "GL_BUFFER_SIZE" with expression '`0x8764`', CType: int  */
		const val GL_BUFFER_SIZE: Int = 0x8764

		/** Define "GL_VALIDATE_STATUS" with expression '`0x8B83`', CType: int  */
		const val GL_VALIDATE_STATUS: Int = 0x8b83

		/** Define "GL_PACK_ALIGNMENT" with expression '`0x0D05`', CType: int  */
		const val GL_PACK_ALIGNMENT: Int = 0xd05

		/** Define "GL_REPEAT" with expression '`0x2901`', CType: int  */
		const val GL_REPEAT: Int = 0x2901

		/** Define "GL_TEXTURE_CUBE_MAP_POSITIVE_X" with expression '`0x8515`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP_POSITIVE_X: Int = 0x8515

		/** Define "GL_RENDERER" with expression '`0x1F01`', CType: int  */
		const val GL_RENDERER: Int = 0x1f01

		/** Define "GL_TEXTURE_CUBE_MAP_POSITIVE_Y" with expression '`0x8517`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP_POSITIVE_Y: Int = 0x8517

		/** Define "GL_TRIANGLE_FAN" with expression '`0x0006`', CType: int  */
		const val GL_TRIANGLE_FAN: Int = 0x6

		/** Define "GL_TEXTURE_CUBE_MAP_POSITIVE_Z" with expression '`0x8519`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP_POSITIVE_Z: Int = 0x8519

		/** Define "GL_STENCIL_CLEAR_VALUE" with expression '`0x0B91`', CType: int  */
		const val GL_STENCIL_CLEAR_VALUE: Int = 0xb91

		/** Define "GL_RENDERBUFFER_DEPTH_SIZE" with expression '`0x8D54`', CType: int  */
		const val GL_RENDERBUFFER_DEPTH_SIZE: Int = 0x8d54

		/** Define "GL_RENDERBUFFER_ALPHA_SIZE" with expression '`0x8D53`', CType: int  */
		const val GL_RENDERBUFFER_ALPHA_SIZE: Int = 0x8d53

		/** Define "GL_FRONT_FACE" with expression '`0x0B46`', CType: int  */
		const val GL_FRONT_FACE: Int = 0xb46

		/** Define "GL_POLYGON_OFFSET_UNITS" with expression '`0x2A00`', CType: int  */
		const val GL_POLYGON_OFFSET_UNITS: Int = 0x2a00

		/** Define "GL_RGBA4" with expression '`0x8056`', CType: int  */
		const val GL_RGBA4: Int = 0x8056

		/** Define "GL_HIGH_INT" with expression '`0x8DF5`', CType: int  */
		const val GL_HIGH_INT: Int = 0x8df5

		/** Define "KHRONOS_SUPPORT_FLOAT" with expression '`1`', CType: int  */
		const val KHRONOS_SUPPORT_FLOAT: Int = 0x1

		/** Define "GL_TEXTURE0" with expression '`0x84C0`', CType: int  */
		const val GL_TEXTURE0: Int = 0x84c0

		/** Define "GL_STENCIL_INDEX8" with expression '`0x8D48`', CType: int  */
		const val GL_STENCIL_INDEX8: Int = 0x8d48

		/** Define "GL_ZERO" with expression '`0`', CType: int  */
		const val GL_ZERO: Int = 0x0

		/** Define "GL_STENCIL_TEST" with expression '`0x0B90`', CType: int  */
		const val GL_STENCIL_TEST: Int = 0xb90

		/** Define "GL_STREAM_DRAW" with expression '`0x88E0`', CType: int  */
		const val GL_STREAM_DRAW: Int = 0x88e0

		/** Define "GL_DECR" with expression '`0x1E03`', CType: int  */
		const val GL_DECR: Int = 0x1e03

		/** Define "GL_TEXTURE4" with expression '`0x84C4`', CType: int  */
		const val GL_TEXTURE4: Int = 0x84c4

		/** Define "GL_BOOL_VEC4" with expression '`0x8B59`', CType: int  */
		const val GL_BOOL_VEC4: Int = 0x8b59

		/** Define "GL_RENDERBUFFER_INTERNAL_FORMAT" with expression '`0x8D44`', CType: int  */
		const val GL_RENDERBUFFER_INTERNAL_FORMAT: Int = 0x8d44

		/** Define "GL_TEXTURE3" with expression '`0x84C3`', CType: int  */
		const val GL_TEXTURE3: Int = 0x84c3

		/** Define "GL_BOOL_VEC3" with expression '`0x8B58`', CType: int  */
		const val GL_BOOL_VEC3: Int = 0x8b58

		/** Define "GL_TEXTURE2" with expression '`0x84C2`', CType: int  */
		const val GL_TEXTURE2: Int = 0x84c2

		/** Define "GL_BOOL_VEC2" with expression '`0x8B57`', CType: int  */
		const val GL_BOOL_VEC2: Int = 0x8b57

		/** Define "GL_TEXTURE1" with expression '`0x84C1`', CType: int  */
		const val GL_TEXTURE1: Int = 0x84c1

		/** Define "GL_TEXTURE8" with expression '`0x84C8`', CType: int  */
		const val GL_TEXTURE8: Int = 0x84c8

		/** Define "GL_TEXTURE7" with expression '`0x84C7`', CType: int  */
		const val GL_TEXTURE7: Int = 0x84c7

		/** Define "GL_STATIC_DRAW" with expression '`0x88E4`', CType: int  */
		const val GL_STATIC_DRAW: Int = 0x88e4

		/** Define "GL_TEXTURE6" with expression '`0x84C6`', CType: int  */
		const val GL_TEXTURE6: Int = 0x84c6

		/** Define "GL_TEXTURE5" with expression '`0x84C5`', CType: int  */
		const val GL_TEXTURE5: Int = 0x84c5

		/** Define "GL_FUNC_ADD" with expression '`0x8006`', CType: int  */
		const val GL_FUNC_ADD: Int = 0x8006

		/** Define "GL_UNSIGNED_SHORT" with expression '`0x1403`', CType: int  */
		const val GL_UNSIGNED_SHORT: Int = 0x1403

		/** Define "GL_TEXTURE9" with expression '`0x84C9`', CType: int  */
		const val GL_TEXTURE9: Int = 0x84c9

		/** Define "GL_POINTS" with expression '`0x0000`', CType: int  */
		const val GL_POINTS: Int = 0x0

		/** Define "GL_CLAMP_TO_EDGE" with expression '`0x812F`', CType: int  */
		const val GL_CLAMP_TO_EDGE: Int = 0x812f

		/** Define "GL_LINES" with expression '`0x0001`', CType: int  */
		const val GL_LINES: Int = 0x1

		/** Define "GL_TEXTURE20" with expression '`0x84D4`', CType: int  */
		const val GL_TEXTURE20: Int = 0x84d4

		/** Define "KHRONOS_SUPPORT_INT64" with expression '`1`', CType: int  */
		const val KHRONOS_SUPPORT_INT64: Int = 0x1

		/** Define "GL_MAX_VIEWPORT_DIMS" with expression '`0x0D3A`', CType: int  */
		const val GL_MAX_VIEWPORT_DIMS: Int = 0xd3a

		/** Define "GL_DECR_WRAP" with expression '`0x8508`', CType: int  */
		const val GL_DECR_WRAP: Int = 0x8508

		/** Define "GL_COLOR_ATTACHMENT0" with expression '`0x8CE0`', CType: int  */
		const val GL_COLOR_ATTACHMENT0: Int = 0x8ce0

		/** Define "GL_SHADER_TYPE" with expression '`0x8B4F`', CType: int  */
		const val GL_SHADER_TYPE: Int = 0x8b4f

		/** Define "GL_ALPHA_BITS" with expression '`0x0D55`', CType: int  */
		const val GL_ALPHA_BITS: Int = 0xd55

		/** Define "GL_FLOAT_MAT4" with expression '`0x8B5C`', CType: int  */
		const val GL_FLOAT_MAT4: Int = 0x8b5c

		/** Define "GL_FLOAT_MAT3" with expression '`0x8B5B`', CType: int  */
		const val GL_FLOAT_MAT3: Int = 0x8b5b

		/** Define "GL_ONE_MINUS_CONSTANT_COLOR" with expression '`0x8002`', CType: int  */
		const val GL_ONE_MINUS_CONSTANT_COLOR: Int = 0x8002

		/** Define "GL_FLOAT_MAT2" with expression '`0x8B5A`', CType: int  */
		const val GL_FLOAT_MAT2: Int = 0x8b5a

		/** Define "GL_BLEND_DST_ALPHA" with expression '`0x80CA`', CType: int  */
		const val GL_BLEND_DST_ALPHA: Int = 0x80ca

		/** Define "GL_TEXTURE19" with expression '`0x84D3`', CType: int  */
		const val GL_TEXTURE19: Int = 0x84d3

		/** Define "GL_ACTIVE_ATTRIBUTES" with expression '`0x8B89`', CType: int  */
		const val GL_ACTIVE_ATTRIBUTES: Int = 0x8b89

		/** Define "GL_TEXTURE18" with expression '`0x84D2`', CType: int  */
		const val GL_TEXTURE18: Int = 0x84d2

		/** Define "GL_TEXTURE17" with expression '`0x84D1`', CType: int  */
		const val GL_TEXTURE17: Int = 0x84d1

		/** Define "GL_TEXTURE16" with expression '`0x84D0`', CType: int  */
		const val GL_TEXTURE16: Int = 0x84d0

		/** Define "GL_TEXTURE15" with expression '`0x84CF`', CType: int  */
		const val GL_TEXTURE15: Int = 0x84cf

		/** Define "GL_TEXTURE14" with expression '`0x84CE`', CType: int  */
		const val GL_TEXTURE14: Int = 0x84ce

		/** Define "GL_MAX_TEXTURE_IMAGE_UNITS" with expression '`0x8872`', CType: int  */
		const val GL_MAX_TEXTURE_IMAGE_UNITS: Int = 0x8872

		/** Define "GL_TEXTURE13" with expression '`0x84CD`', CType: int  */
		const val GL_TEXTURE13: Int = 0x84cd

		/** Define "GL_GLES_PROTOTYPES" with expression '`1`', CType: int  */
		const val GL_GLES_PROTOTYPES: Int = 0x1

		/** Define "GL_TEXTURE12" with expression '`0x84CC`', CType: int  */
		const val GL_TEXTURE12: Int = 0x84cc

		/** Define "GL_BLEND_SRC_ALPHA" with expression '`0x80CB`', CType: int  */
		const val GL_BLEND_SRC_ALPHA: Int = 0x80cb

		/** Define "GL_TEXTURE11" with expression '`0x84CB`', CType: int  */
		const val GL_TEXTURE11: Int = 0x84cb

		/** Define "GL_MAX_VERTEX_ATTRIBS" with expression '`0x8869`', CType: int  */
		const val GL_MAX_VERTEX_ATTRIBS: Int = 0x8869

		/** Define "GL_TEXTURE10" with expression '`0x84CA`', CType: int  */
		const val GL_TEXTURE10: Int = 0x84ca

		/** Define "GL_LINEAR_MIPMAP_LINEAR" with expression '`0x2703`', CType: int  */
		const val GL_LINEAR_MIPMAP_LINEAR: Int = 0x2703

		/** Define "GL_TEXTURE31" with expression '`0x84DF`', CType: int  */
		const val GL_TEXTURE31: Int = 0x84df

		/** Define "GL_COLOR_CLEAR_VALUE" with expression '`0x0C22`', CType: int  */
		const val GL_COLOR_CLEAR_VALUE: Int = 0xc22

		/** Define "GL_TEXTURE30" with expression '`0x84DE`', CType: int  */
		const val GL_TEXTURE30: Int = 0x84de

		/** Define "GL_BLEND_EQUATION_RGB" with expression '`0x8009`', CType: int  */
		const val GL_BLEND_EQUATION_RGB: Int = 0x8009

		/** Define "GL_TEXTURE_CUBE_MAP_NEGATIVE_X" with expression '`0x8516`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP_NEGATIVE_X: Int = 0x8516

		/** Define "GL_TEXTURE_CUBE_MAP_NEGATIVE_Z" with expression '`0x851A`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Z: Int = 0x851a

		/** Define "GL_INCR_WRAP" with expression '`0x8507`', CType: int  */
		const val GL_INCR_WRAP: Int = 0x8507

		/** Define "GL_TEXTURE_CUBE_MAP_NEGATIVE_Y" with expression '`0x8518`', CType: int  */
		const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Y: Int = 0x8518

		/** Define "GL_UNSIGNED_SHORT_4_4_4_4" with expression '`0x8033`', CType: int  */
		const val GL_UNSIGNED_SHORT_4_4_4_4: Int = 0x8033

		/** Define "GL_VERTEX_ATTRIB_ARRAY_STRIDE" with expression '`0x8624`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_STRIDE: Int = 0x8624

		/** Define "GL_NEAREST_MIPMAP_NEAREST" with expression '`0x2700`', CType: int  */
		const val GL_NEAREST_MIPMAP_NEAREST: Int = 0x2700

		/** Define "GL_CONSTANT_ALPHA" with expression '`0x8003`', CType: int  */
		const val GL_CONSTANT_ALPHA: Int = 0x8003

		/** Define "GL_STENCIL_PASS_DEPTH_FAIL" with expression '`0x0B95`', CType: int  */
		const val GL_STENCIL_PASS_DEPTH_FAIL: Int = 0xb95

		/** Define "GL_SAMPLE_COVERAGE_INVERT" with expression '`0x80AB`', CType: int  */
		const val GL_SAMPLE_COVERAGE_INVERT: Int = 0x80ab

		/** Define "GL_TEXTURE29" with expression '`0x84DD`', CType: int  */
		const val GL_TEXTURE29: Int = 0x84dd

		/** Define "GL_ONE_MINUS_CONSTANT_ALPHA" with expression '`0x8004`', CType: int  */
		const val GL_ONE_MINUS_CONSTANT_ALPHA: Int = 0x8004

		/** Define "GL_TEXTURE28" with expression '`0x84DC`', CType: int  */
		const val GL_TEXTURE28: Int = 0x84dc

		/** Define "GL_TEXTURE27" with expression '`0x84DB`', CType: int  */
		const val GL_TEXTURE27: Int = 0x84db

		/** Define "GL_TEXTURE26" with expression '`0x84DA`', CType: int  */
		const val GL_TEXTURE26: Int = 0x84da

		/** Define "GL_TEXTURE25" with expression '`0x84D9`', CType: int  */
		const val GL_TEXTURE25: Int = 0x84d9

		/** Define "GL_CURRENT_PROGRAM" with expression '`0x8B8D`', CType: int  */
		const val GL_CURRENT_PROGRAM: Int = 0x8b8d

		/** Define "GL_TEXTURE24" with expression '`0x84D8`', CType: int  */
		const val GL_TEXTURE24: Int = 0x84d8

		/** Define "GL_INT_VEC4" with expression '`0x8B55`', CType: int  */
		const val GL_INT_VEC4: Int = 0x8b55

		/** Define "GL_TEXTURE23" with expression '`0x84D7`', CType: int  */
		const val GL_TEXTURE23: Int = 0x84d7

		/** Define "GL_TEXTURE22" with expression '`0x84D6`', CType: int  */
		const val GL_TEXTURE22: Int = 0x84d6

		/** Define "GL_INT_VEC2" with expression '`0x8B53`', CType: int  */
		const val GL_INT_VEC2: Int = 0x8b53

		/** Define "GL_TEXTURE21" with expression '`0x84D5`', CType: int  */
		const val GL_TEXTURE21: Int = 0x84d5

		/** Define "GL_INT_VEC3" with expression '`0x8B54`', CType: int  */
		const val GL_INT_VEC3: Int = 0x8b54

		/** Define "GL_DEPTH_RANGE" with expression '`0x0B70`', CType: int  */
		const val GL_DEPTH_RANGE: Int = 0xb70

		/** Define "GL_BLUE_BITS" with expression '`0x0D54`', CType: int  */
		const val GL_BLUE_BITS: Int = 0xd54

		/** Define "GL_FLOAT" with expression '`0x1406`', CType: int  */
		const val GL_FLOAT: Int = 0x1406

		/** Define "GL_BLEND_EQUATION" with expression '`0x8009`', CType: int  */
		const val GL_BLEND_EQUATION: Int = 0x8009

		/** Define "GL_RENDERBUFFER_HEIGHT" with expression '`0x8D43`', CType: int  */
		const val GL_RENDERBUFFER_HEIGHT: Int = 0x8d43

		/** Define "GL_INCR" with expression '`0x1E02`', CType: int  */
		const val GL_INCR: Int = 0x1e02

		/** Define "GL_POLYGON_OFFSET_FILL" with expression '`0x8037`', CType: int  */
		const val GL_POLYGON_OFFSET_FILL: Int = 0x8037

		/** Define "GL_STENCIL_ATTACHMENT" with expression '`0x8D20`', CType: int  */
		const val GL_STENCIL_ATTACHMENT: Int = 0x8d20

		/** Define "GL_INVALID_VALUE" with expression '`0x0501`', CType: int  */
		const val GL_INVALID_VALUE: Int = 0x501

		/** Define "GL_FUNC_SUBTRACT" with expression '`0x800A`', CType: int  */
		const val GL_FUNC_SUBTRACT: Int = 0x800a

		/** Define "GL_FRAMEBUFFER_UNSUPPORTED" with expression '`0x8CDD`', CType: int  */
		const val GL_FRAMEBUFFER_UNSUPPORTED: Int = 0x8cdd

		/** Define "GL_GEQUAL" with expression '`0x0206`', CType: int  */
		const val GL_GEQUAL: Int = 0x206

		/** Define "GL_TEXTURE_MIN_FILTER" with expression '`0x2801`', CType: int  */
		const val GL_TEXTURE_MIN_FILTER: Int = 0x2801

		/** Define "GL_ES_VERSION_2_0" with expression '`1`', CType: int  */
		const val GL_ES_VERSION_2_0: Int = 0x1

		/** Define "GL_NICEST" with expression '`0x1102`', CType: int  */
		const val GL_NICEST: Int = 0x1102

		/** Define "GL_STENCIL_FUNC" with expression '`0x0B92`', CType: int  */
		const val GL_STENCIL_FUNC: Int = 0xb92

		/** Define "GL_UNSIGNED_BYTE" with expression '`0x1401`', CType: int  */
		const val GL_UNSIGNED_BYTE: Int = 0x1401

		/** Define "GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE" with expression '`0x8CD0`', CType: int  */
		const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE: Int = 0x8cd0

		/** Define "GL_SHADING_LANGUAGE_VERSION" with expression '`0x8B8C`', CType: int  */
		const val GL_SHADING_LANGUAGE_VERSION: Int = 0x8b8c

		/** Define "GL_VERSION" with expression '`0x1F02`', CType: int  */
		const val GL_VERSION: Int = 0x1f02

		/** Define "GL_CONSTANT_COLOR" with expression '`0x8001`', CType: int  */
		const val GL_CONSTANT_COLOR: Int = 0x8001

		/** Define "GL_IMPLEMENTATION_COLOR_READ_FORMAT" with expression '`0x8B9B`', CType: int  */
		const val GL_IMPLEMENTATION_COLOR_READ_FORMAT: Int = 0x8b9b

		/** Define "GL_TEXTURE_MAG_FILTER" with expression '`0x2800`', CType: int  */
		const val GL_TEXTURE_MAG_FILTER: Int = 0x2800

		/** Define "GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING" with expression '`0x889F`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING: Int = 0x889f

		/** Define "GL_DST_ALPHA" with expression '`0x0304`', CType: int  */
		const val GL_DST_ALPHA: Int = 0x304

		/** Define "GL_STENCIL_BACK_PASS_DEPTH_FAIL" with expression '`0x8802`', CType: int  */
		const val GL_STENCIL_BACK_PASS_DEPTH_FAIL: Int = 0x8802

		/** Define "GL_LINK_STATUS" with expression '`0x8B82`', CType: int  */
		const val GL_LINK_STATUS: Int = 0x8b82

		/** Define "GL_COMPRESSED_TEXTURE_FORMATS" with expression '`0x86A3`', CType: int  */
		const val GL_COMPRESSED_TEXTURE_FORMATS: Int = 0x86a3

		/** Define "GL_ATTACHED_SHADERS" with expression '`0x8B85`', CType: int  */
		const val GL_ATTACHED_SHADERS: Int = 0x8b85

		/** Define "GL_RENDERBUFFER_BLUE_SIZE" with expression '`0x8D52`', CType: int  */
		const val GL_RENDERBUFFER_BLUE_SIZE: Int = 0x8d52

		/** Define "GL_LOW_INT" with expression '`0x8DF3`', CType: int  */
		const val GL_LOW_INT: Int = 0x8df3

		/** Define "__gles2_gl2_h_" with expression '`1`', CType: int  */
		const val __gles2_gl2_h_: Int = 0x1

		/** Define "GL_DITHER" with expression '`0x0BD0`', CType: int  */
		const val GL_DITHER: Int = 0xbd0

		/** Define "GL_RGB565" with expression '`0x8D62`', CType: int  */
		const val GL_RGB565: Int = 0x8d62

		/** Define "GL_VERTEX_ATTRIB_ARRAY_SIZE" with expression '`0x8623`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_SIZE: Int = 0x8623

		/** Define "GL_SAMPLES" with expression '`0x80A9`', CType: int  */
		const val GL_SAMPLES: Int = 0x80a9

		/** Define "GL_SRC_COLOR" with expression '`0x0300`', CType: int  */
		const val GL_SRC_COLOR: Int = 0x300

		/** Define "GL_LINE_WIDTH" with expression '`0x0B21`', CType: int  */
		const val GL_LINE_WIDTH: Int = 0xb21

		/** Define "GL_STENCIL_FAIL" with expression '`0x0B94`', CType: int  */
		const val GL_STENCIL_FAIL: Int = 0xb94

		/** Define "GL_STENCIL_WRITEMASK" with expression '`0x0B98`', CType: int  */
		const val GL_STENCIL_WRITEMASK: Int = 0xb98

		/** Define "GL_FRONT" with expression '`0x0404`', CType: int  */
		const val GL_FRONT: Int = 0x404

		/** Define "GL_TEXTURE_BINDING_2D" with expression '`0x8069`', CType: int  */
		const val GL_TEXTURE_BINDING_2D: Int = 0x8069

		/** Define "GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS" with expression '`0x8B4D`', CType: int  */
		const val GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS: Int = 0x8b4d

		/** Define "GL_NOTEQUAL" with expression '`0x0205`', CType: int  */
		const val GL_NOTEQUAL: Int = 0x205

		/** Define "GL_SUBPIXEL_BITS" with expression '`0x0D50`', CType: int  */
		const val GL_SUBPIXEL_BITS: Int = 0xd50

		/** Define "GL_RGB" with expression '`0x1907`', CType: int  */
		const val GL_RGB: Int = 0x1907

		/** Define "GL_VERTEX_ATTRIB_ARRAY_ENABLED" with expression '`0x8622`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_ENABLED: Int = 0x8622

		/** Define "GL_SHADER_SOURCE_LENGTH" with expression '`0x8B88`', CType: int  */
		const val GL_SHADER_SOURCE_LENGTH: Int = 0x8b88

		/** Define "GL_STENCIL_BITS" with expression '`0x0D57`', CType: int  */
		const val GL_STENCIL_BITS: Int = 0xd57

		/** Define "GL_CULL_FACE_MODE" with expression '`0x0B45`', CType: int  */
		const val GL_CULL_FACE_MODE: Int = 0xb45

		/** Define "GL_RENDERBUFFER_GREEN_SIZE" with expression '`0x8D51`', CType: int  */
		const val GL_RENDERBUFFER_GREEN_SIZE: Int = 0x8d51

		/** Define "GL_UNSIGNED_SHORT_5_5_5_1" with expression '`0x8034`', CType: int  */
		const val GL_UNSIGNED_SHORT_5_5_5_1: Int = 0x8034

		/** Define "GL_SAMPLER_CUBE" with expression '`0x8B60`', CType: int  */
		const val GL_SAMPLER_CUBE: Int = 0x8b60

		/** Define "GL_FASTEST" with expression '`0x1101`', CType: int  */
		const val GL_FASTEST: Int = 0x1101

		/** Define "GL_BLEND_DST_RGB" with expression '`0x80C8`', CType: int  */
		const val GL_BLEND_DST_RGB: Int = 0x80c8

		/** Define "GL_VERTEX_ATTRIB_ARRAY_NORMALIZED" with expression '`0x886A`', CType: int  */
		const val GL_VERTEX_ATTRIB_ARRAY_NORMALIZED: Int = 0x886a

		/** Define "GL_BLEND" with expression '`0x0BE2`', CType: int  */
		const val GL_BLEND: Int = 0xbe2

		/** Define "GL_SRC_ALPHA" with expression '`0x0302`', CType: int  */
		const val GL_SRC_ALPHA: Int = 0x302

		/** Define "GL_DEPTH_COMPONENT16" with expression '`0x81A5`', CType: int  */
		const val GL_DEPTH_COMPONENT16: Int = 0x81a5
	}
}


expect fun getAngleWrapper(): AngleWrapper