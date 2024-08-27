package org.prismatic.kotlinangle.OpusBuffer

actual class OpusIntBuffer : OpusBuffer() {
	actual fun put(int: Int): OpusIntBuffer {
		TODO("Not yet implemented")
	}

	actual fun get(): Int {
		TODO("Not yet implemented")
	}

	actual fun get(index: Int): Int {
		TODO("Not yet implemented")
	}

	actual fun array(): IntArray {
		TODO("Not yet implemented")
	}

	actual companion object {
		actual fun allocate(capacity: Int): OpusIntBuffer {
			TODO("Not yet implemented")
		}

		actual fun wrap(array: IntArray): OpusIntBuffer {
			TODO("Not yet implemented")
		}
	}
}