package org.prismatic.kotlinangle

actual class OpusByteBuffer : OpusBuffer() {
	actual fun put(byte: Byte): OpusByteBuffer {
		TODO("Not yet implemented")
	}

	actual fun get(): Byte {
		TODO("Not yet implemented")
	}

	actual fun get(index: Int): Byte {
		TODO("Not yet implemented")
	}

	actual fun array(): ByteArray {
		TODO("Not yet implemented")
	}

	actual companion object {
		actual fun allocate(capacity: Int): OpusByteBuffer {
			TODO("Not yet implemented")
		}

		actual fun wrap(array: ByteArray): OpusByteBuffer {
			TODO("Not yet implemented")
		}
	}
}