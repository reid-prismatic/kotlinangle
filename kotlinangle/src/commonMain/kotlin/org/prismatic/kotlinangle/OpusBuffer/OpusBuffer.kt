package org.prismatic.kotlinangle.OpusBuffer

expect open class OpusBuffer {
	fun capacity(): Int
	fun position(): Int
	fun limit(): Int
	fun flip(): OpusBuffer
	fun clear(): OpusBuffer
	fun rewind(): OpusBuffer
}