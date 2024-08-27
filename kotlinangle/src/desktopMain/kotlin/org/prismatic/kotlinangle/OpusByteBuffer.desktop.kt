package org.prismatic.kotlinangle

import java.nio.ByteBuffer

actual class OpusByteBuffer(private val byteBuffer: ByteBuffer) : OpusBuffer(byteBuffer) {
	actual fun put(byte: Byte): OpusByteBuffer {
		byteBuffer.put(byte)
		return this
	}

	actual fun get(): Byte = byteBuffer.get()

	actual fun get(index: Int): Byte = byteBuffer.get(index)

	actual fun array(): ByteArray = byteBuffer.array()

	actual companion object {
		actual fun allocate(capacity: Int): OpusByteBuffer {
			return OpusByteBuffer(java.nio.ByteBuffer.allocate(capacity))
		}

		actual fun wrap(array: ByteArray): OpusByteBuffer {
			return OpusByteBuffer(java.nio.ByteBuffer.wrap(array))
		}
	}

	override val buf: ByteBuffer
		get() = byteBuffer
}