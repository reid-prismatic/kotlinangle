package org.prismatic.kotlinangle.OpusBuffer

actual open class OpusBuffer(private val buffer: ByteArray) {
	private var position: Int = 0
	private var limit: Int = buffer.size
	private var capacity: Int = buffer.size

	actual fun capacity(): Int = capacity

	actual fun position(): Int = position

	actual fun limit(): Int = limit

	actual fun flip(): OpusBuffer {
		limit = position
		position = 0
		return this
	}

	actual fun clear(): OpusBuffer {
		position = 0
		limit = capacity
		return this
	}

	actual fun rewind(): OpusBuffer {
		position = 0
		return this
	}

	open val buf: ByteArray
		get() = buffer
}