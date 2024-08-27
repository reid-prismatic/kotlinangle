package org.prismatic.kotlinangle.OpusBuffer

expect class OpusFloatBuffer : OpusBuffer {
	fun put(float: Float): OpusFloatBuffer
	fun get(): Float
	fun get(index: Int): Float
	fun array(): FloatArray
	companion object {
		fun allocate(capacity: Int): OpusFloatBuffer
		fun wrap(array: FloatArray): OpusFloatBuffer
	}
}