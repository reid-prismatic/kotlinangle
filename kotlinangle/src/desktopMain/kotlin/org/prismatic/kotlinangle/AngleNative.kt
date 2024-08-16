package org.prismatic.kotlinangle

import java.io.File


class AngleNative {
	@Suppress("UnsafeDynamicallyLoadedCode")
	companion object {
		init {
			val baseDir = System.getProperty("user.dir")
			val dylibPath = "$baseDir/../../kotlinangle/build/libs/libPrismAngleLibrary.dylib"
			println("Attempting to load .dylib from: $dylibPath")
			if (File(dylibPath).exists()) {
				System.load(dylibPath)
			} else {
				throw UnsatisfiedLinkError("Library not found at: $dylibPath")
			}
			//System.loadLibrary("PrismAngleLibrary")
		}
	}

	external fun createPrismAngle(): Long
	external fun fillScreenRGBAngle(nativePtr: Long, red: Float, green: Float, blue: Float): Int
	external fun testCallingAngle(): Int
	external fun makeAngleContextCurrent(nativePtr: Long): Int
}