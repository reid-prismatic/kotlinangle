package org.prismatic.kotlinangle

actual class OpusFloatBuffer : OpusBuffer() {
	actual fun put(float: Float): OpusFloatBuffer {
		TODO("Not yet implemented")
	}

	actual fun get(): Float {
		TODO("Not yet implemented")
	}

	actual fun get(index: Int): Float {
		TODO("Not yet implemented")
	}

	actual fun array(): FloatArray {
		TODO("Not yet implemented")
	}

	actual companion object {
		actual fun allocate(capacity: Int): OpusFloatBuffer {
			TODO("Not yet implemented")
		}

		actual fun wrap(array: FloatArray): OpusFloatBuffer {
			TODO("Not yet implemented")
		}
	}
}