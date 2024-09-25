package org.prismatic.kotlinangle.OpusBuffer

import kotlinx.cinterop.*
import platform.posix.*

actual class OpusByteBuffer(private val byteArray: ByteArray) : OpusBuffer(byteArray) {
	private var position: Int = 0

	actual fun put(byte: Byte): OpusByteBuffer {
		if (position < byteArray.size) {
			byteArray[position] = byte
			position++
		} else {
			throw IndexOutOfBoundsException("Buffer overflow")
		}
		return this
	}

	actual fun get(): Byte {
		if (position < byteArray.size) {
			return byteArray[position++]
		} else {
			throw IndexOutOfBoundsException("Buffer underflow")
		}
	}

	actual fun get(index: Int): Byte {
		if (index in byteArray.indices) {
			return byteArray[index]
		} else {
			throw IndexOutOfBoundsException("Index out of bounds")
		}
	}

	actual fun array(): ByteArray = byteArray

	actual companion object {
		actual fun allocate(capacity: Int): OpusByteBuffer {
			return OpusByteBuffer(ByteArray(capacity))
		}

		actual fun wrap(array: ByteArray): OpusByteBuffer {
			return OpusByteBuffer(array)
		}
	}

	override val buf: ByteArray
		get() = byteArray
}