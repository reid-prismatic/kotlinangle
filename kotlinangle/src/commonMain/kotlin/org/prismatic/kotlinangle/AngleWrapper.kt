package org.prismatic.kotlinangle


interface AngleWrapper {
	fun callAngleApiTest(): Int
	fun fillScreenRGBAngle(red: Float, green: Float, blue: Float): Int
	fun makeAngleContextCurrent(): Int
}


expect fun getAngleWrapper(): AngleWrapper