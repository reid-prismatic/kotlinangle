package org.prismatic.kotlinangle.opuscopy.impl

//import javax.sound.sampled.AudioSystem
//import javax.sound.sampled.Clip
//import java.io.File
//import javax.sound.sampled.LineEvent
//
//internal actual fun audioClipFromFile(path: String): Result<AudioClip> {
//	return runCatching { AudioClipDesktop(path) }
//}
//
//class AudioClipDesktop(audioPath: String) : AudioClip {
//	private val clip: Clip = AudioSystem.getClip()
//	private val frameRate = FrameRate.DEFAULT
//	private var pausePosition: Long = 0
//
//	init {
//		val audioFile = File(audioPath)
//		val audioInputStream = AudioSystem.getAudioInputStream(audioFile)
//		clip.open(audioInputStream)
//		addEndListener()
//	}
//
//	override val playing: Boolean
//		get() = clip.isRunning
//
//	override val currentTime: MediaTime
//		get() {
//			val currentMS = clip.microsecondPosition
//			return currentMS.micros.toMediaTime(frameRate)
//		}
//	override val duration: MediaTime
//		get() {
//			val durationMS = clip.microsecondLength
//			return durationMS.micros.toMediaTime(frameRate)
//		}
//
//	override fun play() {
//		clip.microsecondPosition = pausePosition
//		clip.start()
//	}
//
//	override fun stop() {
//		pausePosition = 0
//		clip.stop()
//	}
//
//	override fun pause() {
//		pausePosition = clip.microsecondPosition
//		clip.stop()
//	}
//
//	override fun seek(time: MediaTime) {
//		val timeInMicros = time.toMicros().asLong()
//		clip.microsecondPosition = timeInMicros
//		pausePosition = timeInMicros
//	}
//
//	private fun addEndListener() {
//		clip.addLineListener { event ->
//			if (event.type == LineEvent.Type.STOP) {
//				println("End of file. Stopped.")
//				pausePosition = 0
//			}
//		}
//	}
//
//	override fun dispose() {
//		println("${clip.debugId} clip is disposed")
//		clip.close()
//	}
//}