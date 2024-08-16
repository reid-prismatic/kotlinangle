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

}

actual fun getAngleWrapper(): AngleWrapper = AngleWrapperJvm()