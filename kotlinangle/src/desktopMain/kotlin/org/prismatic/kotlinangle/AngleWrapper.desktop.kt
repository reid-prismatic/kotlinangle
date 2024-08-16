package org.prismatic.kotlinangle

class AngleWrapperJvm: AngleWrapper {
	private val angleNative: AngleNative = AngleNative()
	private val contextPtr: Long = angleNative.createPrismAngle()

	override fun callAngleApiTest(): Int {
		return angleNative.testCallingAngle()
	}

	override fun fillScreenRGBAngle(red: Float, green: Float, blue: Float): Int {
		return angleNative.fillScreenRGBAngle(contextPtr, red, green, blue)
	}

	override fun makeAngleContextCurrent(): Int {
		return angleNative.makeAngleContextCurrent(contextPtr)
	}


}

actual fun getAngleWrapper(): AngleWrapper = AngleWrapperJvm()