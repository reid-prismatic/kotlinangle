package org.prismatic.kotlinangle.opuscopy


internal interface AudioClip {
	fun play()
	fun stop()
	fun pause()
	fun seek(time: MediaTime)
	fun dispose()
	val playing: Boolean
	val currentTime: MediaTime
	val duration: MediaTime
}

internal expect fun audioClipFromFile(path: String): Result<AudioClip>

internal inline fun <T> AudioClip.use(block:(AudioClip)->T) : T = try {
    block(this)
} finally {
    dispose()
}

private val audioFileDurationCache = mutableMapOf<String,MediaTime>()

internal fun getAudioFileDuration(path: String) =
	audioFileDurationCache.getOrPut(path) {
		extractAudioFileDuration(path)
	}

private fun extractAudioFileDuration(path: String) =
	if (path == "")
		MediaTime.ZERO
	else
		audioClipFromFile(path)
			.onFailure { println("Failed to get duration for '$path'; ${it.message}") }
			.map { it.use { it.duration } }.
			getOrElse { MediaTime.ZERO }