package org.prismatic.kotlinangle

interface AngleWrapper {

	fun glActiveTexture(texture: Int)

	fun glAttachShader(program: Int, shader: Int)

	fun glBindAttribLocation(program: Int, index: Int, name: ByteBuffer?)

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

	fun glBufferData(target: Int, size: Int, data: Buffer, usage: Int)

	fun glBufferSubData(target: Int, offset: Int, size: Int, data: Buffer)

	fun glCheckFramebufferStatus(target: Int): Int

	fun glClear(mask: Int)

	fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float)

	fun glClearDepthf(d: Float)

	fun glClearStencil(s: Int)

	fun glColorMask(red: Byte, green: Byte, blue: Byte, alpha: Byte)

	fun glCompileShader(shader: Int)

	fun glCompressedTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int, imageSize: Int, data: Buffer)

	fun glCompressedTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: Int, imageSize: Int, data: Buffer)

	fun glCopyTexImage2D(target: Int, level: Int, internalformat: Int, x: Int, y: Int, width: Int, height: Int, border: Int)

	fun glCopyTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, x: Int, y: Int, width: Int, height: Int)

	fun glCreateProgram(): Int

	fun glCreateShader(type: Int): Int

	fun glCullFace(mode: Int)

	fun glDeleteBuffers(n: Int, buffers: IntBuffer?)

	fun glDeleteBuffers(n: Int, buffers: IntArray?, buffers_offset: Int)

	fun glDeleteFramebuffers(n: Int, framebuffers: IntBuffer?)

	fun glDeleteFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int)

	fun glDeleteProgram(program: Int)

	fun glDeleteRenderbuffers(n: Int, renderbuffers: IntBuffer?)

	fun glDeleteRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int)

	fun glDeleteShader(shader: Int)

	fun glDeleteTextures(n: Int, textures: IntBuffer?)

	fun glDeleteTextures(n: Int, textures: IntArray?, textures_offset: Int)

	fun glDepthFunc(func: Int)

	fun glDepthMask(flag: Byte)

	fun glDepthRangef(n: Float, f: Float)

	fun glDetachShader(program: Int, shader: Int)

	fun glDisable(cap: Int)

	fun glDisableVertexAttribArray(index: Int)

	fun glDrawArrays(mode: Int, first: Int, count: Int)

	fun glDrawElements(mode: Int, count: Int, type: Int, indices: Buffer)

	fun glEnable(cap: Int)

	fun glEnableVertexAttribArray(index: Int)

	fun glFinish()

	fun glFlush()

	fun glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Int)

	fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int, level: Int)

	fun glFrontFace(mode: Int)

	fun glGenBuffers(n: Int, buffers: IntBuffer?)

	fun glGenBuffers(n: Int, buffers: IntArray?, buffers_offset: Int)

	fun glGenerateMipmap(target: Int)

	fun glGenFramebuffers(n: Int, framebuffers: IntBuffer?)

	fun glGenFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int)

	fun glGenRenderbuffers(n: Int, renderbuffers: IntBuffer?)

	fun glGenRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int)

	fun glGenTextures(n: Int, textures: IntBuffer?)
	fun glGenTextures(n: Int, textures: IntArray?, textures_offset: Int)
	fun glGetActiveAttrib(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntBuffer?,
		size: IntBuffer?,
		type: IntBuffer?,
		name: ByteBuffer?
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
		length: IntBuffer?,
		size: IntBuffer?,
		type: IntBuffer?,
		name: ByteBuffer?
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
		count: IntBuffer?,
		shaders: IntBuffer?
	)
	fun glGetAttachedShaders(
		program: Int,
		maxCount: Int,
		count: IntArray?,
		count_offset: Int,
		shaders: IntArray?,
		shaders_offset: Int
	)
	fun glGetAttribLocation(program: Int, name: ByteBuffer?): Int
	fun glGetAttribLocation(program: Int, name: ByteArray?, name_offset: Int): Int
	fun glGetBooleanv(pname: Int, data: ByteBuffer?)
	fun glGetBooleanv(pname: Int, data: ByteArray?, data_offset: Int)
	fun glGetBufferParameteriv(target: Int, pname: Int, params: IntBuffer?)
	fun glGetBufferParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetError(): Int
	fun glGetFloatv(pname: Int, data: FloatBuffer?)
	fun glGetFloatv(pname: Int, data: FloatArray?, data_offset: Int)
	fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: IntBuffer?
	)
	fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: IntArray?,
		params_offset: Int
	)
	fun glGetIntegerv(pname: Int, data: IntBuffer?)
	fun glGetIntegerv(pname: Int, data: IntArray?, data_offset: Int)
	fun glGetProgramiv(program: Int, pname: Int, params: IntBuffer?)
	fun glGetProgramiv(program: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetProgramInfoLog(program: Int, bufSize: Int, length: IntBuffer?, infoLog: ByteBuffer?)
	fun glGetProgramInfoLog(
		program: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	)
	fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: IntBuffer?)
	fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetShaderiv(shader: Int, pname: Int, params: IntBuffer?)
	fun glGetShaderiv(shader: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetShaderInfoLog(shader: Int, bufSize: Int, length: IntBuffer?, infoLog: ByteBuffer?)
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
		range: IntBuffer?,
		precision: IntBuffer?
	)
	fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: IntArray?,
		range_offset: Int,
		precision: IntArray?,
		precision_offset: Int
	)
	fun glGetShaderSource(shader: Int, bufSize: Int, length: IntBuffer?, source: ByteBuffer?)
	fun glGetShaderSource(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		source: ByteArray?,
		source_offset: Int
	)
	fun glGetString(name: Int): ByteBuffer?
	fun glGetTexParameterfv(target: Int, pname: Int, params: FloatBuffer?)
	fun glGetTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int)
	fun glGetTexParameteriv(target: Int, pname: Int, params: IntBuffer?)
	fun glGetTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int)
	fun glGetUniformfv(program: Int, location: Int, params: FloatBuffer?)
	fun glGetUniformfv(program: Int, location: Int, params: FloatArray?, params_offset: Int)
	fun glGetUniformiv(program: Int, location: Int, params: IntBuffer?)
	fun glGetUniformiv(program: Int, location: Int, params: IntArray?, params_offset: Int)
	fun glGetUniformLocation(program: Int, name: ByteBuffer?): Int
	fun glGetUniformLocation(program: Int, name: ByteArray?, name_offset: Int): Int
	fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatBuffer?)
	fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatArray?, params_offset: Int)
	fun glGetVertexAttribiv(index: Int, pname: Int, params: IntBuffer?)
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
	fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: Buffer)
	fun glReleaseShaderCompiler()
	fun glRenderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int)
	fun glSampleCoverage(value: Float, invert: Byte)
	fun glScissor(x: Int, y: Int, width: Int, height: Int)
	fun glShaderBinary(
		count: Int,
		shaders: IntBuffer?,
		binaryFormat: Int,
		binary: Buffer,
		length: Int
	)
	fun glShaderBinary(
		count: Int,
		shaders: IntArray?,
		shaders_offset: Int,
		binaryFormat: Int,
		binary: Buffer,
		length: Int
	)
	fun glShaderSource(shader: Int, count: Int, string: PointerBuffer?, length: IntBuffer?)
	fun glShaderSource(
		shader: Int,
		count: Int,
		string: PointerBuffer?,
		length: IntArray?,
		length_offset: Int
	)
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
		pixels: Buffer
	)
	fun glTexParameterf(target: Int, pname: Int, param: Float)
	fun glTexParameterfv(target: Int, pname: Int, params: FloatBuffer?)
	fun glTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int)
	fun glTexParameteri(target: Int, pname: Int,

	                    param: Int)
	fun glTexParameteriv(target: Int, pname: Int, params: IntBuffer?)
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
		pixels: Buffer
	)
	fun glUniform1f(location: Int, v0: Float)
	fun glUniform1fv(location: Int, count: Int, value: FloatBuffer?)
	fun glUniform1fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform1i(location: Int, v0: Int)
	fun glUniform1iv(location: Int, count: Int, value: IntBuffer?)
	fun glUniform1iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniform2f(location: Int, v0: Float, v1: Float)
	fun glUniform2fv(location: Int, count: Int, value: FloatBuffer?)
	fun glUniform2fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform2i(location: Int, v0: Int, v1: Int)
	fun glUniform2iv(location: Int, count: Int, value: IntBuffer?)
	fun glUniform2iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniform3f(location: Int, v0: Float, v1: Float, v2: Float)
	fun glUniform3fv(location: Int, count: Int, value: FloatBuffer?)
	fun glUniform3fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform3i(location: Int, v0: Int, v1: Int, v2: Int)
	fun glUniform3iv(location: Int, count: Int, value: IntBuffer?)
	fun glUniform3iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float)
	fun glUniform4fv(location: Int, count: Int, value: FloatBuffer?)
	fun glUniform4fv(location: Int, count: Int, value: FloatArray?, value_offset: Int)
	fun glUniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int)
	fun glUniform4iv(location: Int, count: Int, value: IntBuffer?)
	fun glUniform4iv(location: Int, count: Int, value: IntArray?, value_offset: Int)
	fun glUniformMatrix2fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatBuffer?
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
		value: FloatBuffer?
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
		value: FloatBuffer?
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
	fun glVertexAttrib1fv(index: Int, v: FloatBuffer?)
	fun glVertexAttrib1fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttrib2f(index: Int, x: Float, y: Float)
	fun glVertexAttrib2fv(index: Int, v: FloatBuffer?)
	fun glVertexAttrib2fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttrib3f(index: Int, x: Float, y: Float, z: Float)
	fun glVertexAttrib3fv(index: Int, v: FloatBuffer?)
	fun glVertexAttrib3fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float)
	fun glVertexAttrib4fv(index: Int, v: FloatBuffer?)
	fun glVertexAttrib4fv(index: Int, v: FloatArray?, v_offset: Int)
	fun glVertexAttribPointer(
		index: Int,
		size: Int,
		type: Int,
		normalized: Byte,
		stride: Int,
		pointer: Buffer
	)
	fun glViewport(x: Int, y: Int, width: Int, height: Int)
}


expect fun getAngleWrapper(): AngleWrapper