package org.prismatic.kotlinangle.OpusBuffer

import java.nio.Buffer

actual open class OpusBuffer(private val buffer: Buffer) {
	actual fun capacity(): Int = buffer.capacity()
	actual fun position(): Int = buffer.position()
	actual fun limit(): Int = buffer.limit()
	actual fun flip(): OpusBuffer {
		buffer.flip()
		return this
	}
	actual fun clear(): OpusBuffer {
		buffer.clear()
		return this
	}
	actual fun rewind(): OpusBuffer {
		buffer.rewind()
		return this
	}

	open val buf: Buffer
		get() = buffer
}