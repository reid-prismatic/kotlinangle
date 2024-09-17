package org.prismatic.kotlinangle

//class AngleWrapperJvm: AngleWrapper {
//	//private val angleNative: AngleNative = AngleNative()
//	//private val contextPtr: Long = angleNative.createPrismAngle()
//
//	override fun callAngleApiTest(): Int {
////		val buffers = IntBuffer.allocate(1)
////		glGenBuffers(1,buffers)
////		checkGLErrors()
////		return( GLBufferName(buffers[0]) )
////		val buffer = makeBuffer()
////		glBindBuffer(target,buffer)
////		glBufferData(target,elements.size.toLong() * Float.SIZE_BYTES, FloatBuffer.wrap(elements), usage)
////		checkGLErrors()
////		glBindBuffer(target,0)
////		return buffer
//		return 0
//		//return angleNative.testCallingAngle()
//	}
//
//	override fun fillScreenRGB(red: Float, green: Float, blue: Float): Int {
//		AngleNative.glClearColor(red, green, blue, 1f)
//		AngleNative.glClear(AngleNative.GL_COLOR_BUFFER_BIT)
//		return 0
//		//return angleNative.fillScreenRGBAngle(contextPtr, red, green, blue)
//	}
//
//	override fun makeAngleContextCurrent(): Int {
//		return 0
//		//return angleNative.makeAngleContextCurrent(contextPtr)
//	}
//
//
//}

//actual fun getAngleWrapper(): AngleWrapper = AngleWrapperJvm()
//actual fun getAngleWrapper(): AngleWrapper {
//	val angle = AngleNative()
//	angle.createPrismAngle()
//
//	return angle
//}
//actual fun getAngleWrapper(): AngleWrapper = AngleNative
actual fun getAngleWrapper(windowHandle: Long): AngleWrapper {
	//AngleNative.createPrismAngle()
	return AngleNative(windowHandle)
}