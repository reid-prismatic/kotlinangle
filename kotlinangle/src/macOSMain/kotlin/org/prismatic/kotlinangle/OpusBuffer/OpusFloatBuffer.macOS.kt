package org.prismatic.kotlinangle.OpusBuffer

import kotlinx.cinterop.*
import platform.posix.*

actual class OpusFloatBuffer(private val floatArray: FloatArray) : OpusBuffer(floatArray) {
	private var position: Int = 0

	actual fun put(float: Float): OpusFloatBuffer {
		if (position < floatArray.size) {
			floatArray[position] = float
			position++
		} else {
			throw IndexOutOfBoundsException("Buffer overflow")
		}
		return this
	}

	actual fun get(): Float {
		if (position < floatArray.size) {
			return floatArray[position++]
		} else {
			throw IndexOutOfBoundsException("Buffer underflow")
		}
	}

	actual fun get(index: Int): Float {
		if (index in floatArray.indices) {
			return floatArray[index]
		} else {
			throw IndexOutOfBoundsException("Index out of bounds")
		}
	}

	actual fun array(): FloatArray = floatArray

	actual companion object {
		actual fun allocate(capacity: Int): OpusFloatBuffer {
			return OpusFloatBuffer(FloatArray(capacity))
		}

		actual fun wrap(array: FloatArray): OpusFloatBuffer {
			return OpusFloatBuffer(array)
		}
	}

	override val buf: FloatArray
		get() = floatArray
}