package org.prismatic.kotlinangle


expect class OpusIntBuffer : OpusBuffer {
	fun put(int: Int): OpusIntBuffer
	fun get(): Int
	fun get(index: Int): Int
	fun array(): IntArray
	companion object {
		fun allocate(capacity: Int): OpusIntBuffer
		fun wrap(array: IntArray): OpusIntBuffer
	}
}