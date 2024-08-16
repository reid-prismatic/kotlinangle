package org.prismatic.kotlinangle


interface AngleWrapper {
	fun callAngleApiTest(): Int
	fun fillScreenRGB(red: Float, green: Float, blue: Float): Int
	fun makeAngleContextCurrent(): Int
}


expect fun getAngleWrapper(): AngleWrapper