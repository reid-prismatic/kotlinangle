package org.prismatic.kotlinangle.OpusBuffer

import kotlinx.cinterop.*
import platform.posix.*

actual class OpusIntBuffer(private val intArray: IntArray) : OpusBuffer(intArray) {
	private var position: Int = 0

	actual fun put(int: Int): OpusIntBuffer {
		if (position < intArray.size) {
			intArray[position] = int
			position++
		} else {
			throw IndexOutOfBoundsException("Buffer overflow")
		}
		return this
	}

	actual fun get(): Int {
		if (position < intArray.size) {
			return intArray[position++]
		} else {
			throw IndexOutOfBoundsException("Buffer underflow")
		}
	}

	actual fun get(index: Int): Int {
		if (index in intArray.indices) {
			return intArray[index]
		} else {
			throw IndexOutOfBoundsException("Index out of bounds")
		}
	}

	actual fun array(): IntArray = intArray

	actual companion object {
		actual fun allocate(capacity: Int): OpusIntBuffer {
			return OpusIntBuffer(IntArray(capacity))
		}

		actual fun wrap(array: IntArray): OpusIntBuffer {
			return OpusIntBuffer(array)
		}
	}

	override val buf: IntArray
		get() = intArray
}