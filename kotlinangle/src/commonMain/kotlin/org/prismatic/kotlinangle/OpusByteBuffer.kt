package org.prismatic.kotlinangle


expect class OpusByteBuffer : OpusBuffer {
	fun put(byte: Byte): OpusByteBuffer
	fun get(): Byte
	fun get(index: Int): Byte
	fun array(): ByteArray
	companion object {
		fun allocate(capacity: Int): OpusByteBuffer
	}
}