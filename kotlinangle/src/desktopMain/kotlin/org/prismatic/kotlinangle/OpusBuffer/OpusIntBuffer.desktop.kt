package org.prismatic.kotlinangle.OpusBuffer

import java.nio.IntBuffer

actual class OpusIntBuffer(private val intBuffer: IntBuffer) : OpusBuffer(intBuffer) {
	actual fun put(int: Int): OpusIntBuffer {
		intBuffer.put(int)
		return this
	}

	actual fun get(): Int = intBuffer.get()

	actual fun get(index: Int): Int = intBuffer.get(index)

	actual fun array(): IntArray = intBuffer.array()

	actual companion object {
		actual fun allocate(capacity: Int): OpusIntBuffer {
			return OpusIntBuffer(IntBuffer.allocate(capacity))
		}

		actual fun wrap(array: IntArray): OpusIntBuffer {
			return OpusIntBuffer(IntBuffer.wrap(array))
		}
	}

	override val buf: IntBuffer
		get() = intBuffer
}