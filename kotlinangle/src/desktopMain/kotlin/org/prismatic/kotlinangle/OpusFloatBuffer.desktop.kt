package org.prismatic.kotlinangle

import java.nio.FloatBuffer

actual class OpusFloatBuffer(private val floatBuffer: FloatBuffer) : OpusBuffer(floatBuffer) {
	actual fun put(float: Float): OpusFloatBuffer {
		floatBuffer.put(float)
		return this
	}

	actual fun get(): Float = floatBuffer.get()

	actual fun get(index: Int): Float = floatBuffer.get(index)

	actual fun array(): FloatArray = floatBuffer.array()

	actual companion object {
		actual fun allocate(capacity: Int): OpusFloatBuffer {
			return OpusFloatBuffer(java.nio.FloatBuffer.allocate(capacity))
		}

		actual fun wrap(array: FloatArray): OpusFloatBuffer {
			return OpusFloatBuffer(java.nio.FloatBuffer.wrap(array))
		}
	}

	override val buf: FloatBuffer
		get() = floatBuffer
}