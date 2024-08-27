@file:Suppress("OVERRIDE_BY_INLINE")

package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import kotlinx.serialization.Serializable
import kotlin.math.nextDown
import kotlin.math.pow
import kotlin.math.roundToInt

@Serializable
@Immutable
@kotlin.jvm.JvmInline
value class FrameRate(@PublishedApi internal val value: Int) : Comparable<FrameRate> {
    inline operator fun plus(other: FrameRate) : FrameRate = (value+other.value).fps
    inline operator fun minus(other: FrameRate) : FrameRate = (value-other.value).fps
    inline operator fun times(other: FrameRate) : FrameRate = (value*other.value).fps
    inline operator fun div(other: FrameRate) : FrameRate = (value/other.value).fps
    inline operator fun rem(other: FrameRate) : FrameRate = (value%other.value).fps
    inline operator fun times(other: Int) : FrameRate = (value*other).fps
    inline operator fun div(other: Int) : FrameRate = (value/other).fps
    inline operator fun plus(other: Int) : FrameRate = (value+other).fps
    inline operator fun minus(other: Int) : FrameRate = (value-other).fps
    inline operator fun rem(other: Int) : FrameRate = (value%other).fps
    inline operator fun unaryPlus() : FrameRate = this
    inline operator fun unaryMinus() : FrameRate = (-value).fps
    override inline operator fun compareTo(other: FrameRate): Int = value.compareTo(other.value)
    inline operator fun compareTo(other: Int): Int = value.compareTo(other)
    inline fun toInt() = value
    inline fun toLong() = value.toLong()
    inline fun toDouble() = value.toDouble()

    init {
        require(value > 0)
    }

    companion object {
        // Default frame rate of 18000 is lossless over rates of:
        // 1,2,3,4,5,6,8,9,10,12,15,16,18,20,24,25,30,36,40,48,50,60,120,1000
        val DEFAULT = 18000.fps
    }

    override fun toString(): String {
        return "$value.fps"
    }
}
inline val Int.fps get() : FrameRate {
    require( this in 1..65535) {"Expected value in range 1..65535 but got $this"}
    return FrameRate(this)
}
inline fun Int.toFpsOrFailWith(block: ()->Nothing) : FrameRate {
    if( this in 1..65535) return FrameRate(this) else block()
}
inline operator fun Int.rem(other: FrameRate) = this % other.value
inline operator fun Long.rem(other: FrameRate) = this % other.value

@Immutable
interface Time {
    fun toSeconds() : Seconds
    fun toMicros() : Micros
    fun toMillis() : Millis
    fun toNanos() : Nanos
    fun toMediaTime(frameRate: FrameRate) : MediaTime
    val isSpecified : Boolean get() = true
    val isUnspecified : Boolean get() = !isSpecified
    val isBounded : Boolean get() = true
    val isUnbounded : Boolean get() = !isBounded
    val isKnown : Boolean get() = true
    val isUnknown : Boolean get() = !isKnown
    fun takeOrElse(other: ()->Time) = if(isSpecified) this else other()
    @Immutable
    object Zero : Time {
        override inline fun toSeconds(): Seconds = 0.0.seconds
        override inline fun toMicros(): Micros = 0L.micros
        override inline fun toMillis(): Millis = 0L.millis
        override inline fun toNanos(): Nanos = 0L.nanos
        override inline fun toMediaTime(frameRate: FrameRate): MediaTime = MediaTime(0,frameRate)
    }
}

@Immutable
interface TimeType<T:Comparable<T>> : Comparable<T>, Time {
    operator fun plus(other: T) : T
    operator fun minus(other: T) : T
    operator fun times(other: T) : T
    operator fun div(other: T) : Double
    operator fun rem(other: T) : T
    operator fun times(other: Int) : T
    operator fun div(other: Int) : T
    operator fun times(other: Long) : T
    operator fun div(other: Long) : T
    operator fun times(other: Float) : T
    operator fun div(other: Float) : T
    operator fun times(other: Double) : T
    operator fun div(other: Double) : T
    operator fun unaryPlus() : T
    operator fun unaryMinus() : T
    operator fun rangeTo(other: T) : ClosedRange<T>
    infix fun until(other: T) : ClosedRange<T>
}

inline fun Time.toMediaTime() = toMediaTime(FrameRate.DEFAULT)
inline fun <T:TimeType<T>> T.toMediaTimeMatching(other: MediaTime) = toMediaTime(other.frameRate)

data class MediaTimeRange(override val start: MediaTime, override val endInclusive: MediaTime) : ClosedRange<MediaTime> {
    init {
        require(start.frameRate == endInclusive.frameRate) {"Expected ${start.frameRate} == ${endInclusive.frameRate}"}
    }
}

@Immutable
@Serializable
@kotlin.jvm.JvmInline
value class MediaTime @PublishedApi internal constructor(@PublishedApi internal val packedValue: Long) : TimeType<MediaTime> {
    val frameNumber: Long get() = unpackFrameNumber(packedValue)
    val frameRate: FrameRate get() = unpackFrameRateOrFail(packedValue) {throw IllegalStateException("Invalid operation on $this")}
    override inline fun toNanos(): Nanos = Nanos(frameNumber.toLong() * 1_000_000_000 / frameRate.toLong())
    override inline fun toMicros(): Micros = Micros(frameNumber.toLong() * 1_000_000 / frameRate.toLong())
    override inline fun toMillis(): Millis = Millis(frameNumber.toLong() * 1_000 / frameRate.toLong() )
    override inline fun toSeconds(): Seconds = Seconds(frameNumber.toDouble() / frameRate.toDouble())
    override inline fun toMediaTime(frameRate: FrameRate): MediaTime = this.toFrameRate(frameRate)
    override inline operator fun times(other: MediaTime) = binaryMediaTimeOp(other) { a, b -> a * b }
    override inline operator fun plus(other: MediaTime) = binaryMediaTimeOp(other) { a, b -> a + b }
    override inline operator fun minus(other: MediaTime) = binaryMediaTimeOp(other) { a, b -> a - b }
    override inline operator fun div(other: MediaTime) : Double  = binaryOp(other) { a, b -> (a.frameNumber.toDouble() / b.frameNumber.toDouble()) }
    override inline operator fun rem(other: MediaTime) = binaryMediaTimeOp(other) { a, b -> a % b }
    override inline operator fun unaryMinus() = MediaTime(-frameNumber, frameRate)
    override inline operator fun unaryPlus() = this
    inline operator fun minus(other: Int) = MediaTime(frameNumber-other,frameRate)
    inline operator fun plus(other: Int) = MediaTime(frameNumber+other,frameRate)
    override inline operator fun times(other: Int) = MediaTime(frameNumber*other,frameRate)
    override inline operator fun div(other: Int) = MediaTime(frameNumber/other,frameRate)
    override inline operator fun times(other: Long) = MediaTime(frameNumber*other,frameRate)
    override inline operator fun div(other: Long) = MediaTime(frameNumber/other,frameRate)
    override inline operator fun times(other: Float) = MediaTime((frameNumber.toDouble()*other.toDouble()).toLong(),frameRate)
    override inline operator fun div(other: Float) = MediaTime((frameNumber.toDouble()/other.toDouble()).toLong(),frameRate)
    override inline operator fun times(other: Double) = MediaTime((frameNumber.toDouble()*other).toLong(),frameRate)
    override inline operator fun div(other: Double) = MediaTime((frameNumber.toDouble()/other).toLong(),frameRate)
    override inline operator fun compareTo(other: MediaTime): Int = binaryOp(other) { a, b -> a.frameNumber.compareTo(b.frameNumber)}
    override inline operator fun rangeTo(other: MediaTime) : ClosedRange<MediaTime> = binaryOp(other) { a, b -> MediaTimeRange(a,b-1)}
    override inline infix fun until(other: MediaTime) : ClosedRange<MediaTime> = binaryOp(other) { a, b -> MediaTimeRange(a,b)}
    inline fun toFrameRate(frameRate: FrameRate) : MediaTime {
        if( isUnspecified || isUnbounded || isUnknown ) return this
        if( frameRate == this.frameRate ) return this
        return MediaTime((this.frameNumber * frameRate.toLong() / this.frameRate.toLong()), frameRate)
    }
    companion object {
        val Unspecified = MediaTime(0x00000)
        val Unbounded = MediaTime(0x10000)
        val Unknown = MediaTime(0x20000)
        const val MAX_FRAME_NUMBER = (1L shl 48)-1
        const val MIN_FRAME_NUMBER = -(1L shl 48)
        const val MAX_FRAME_RATE = (1 shl 16)-1
        const val MIN_FRAME_RATE = 1
        val ZERO = MediaTime(0,1.fps)
    }

    override fun toString(): String {
        return when {
            isUnspecified -> "Unspecified"
            isUnbounded -> "Unbounded"
            isUnknown -> "Unknown"
            unpackRawFrameRate(packedValue) !in 1..65535 -> "Invalid(fps=${unpackRawFrameRate(packedValue)})"
            else -> "$frameNumber/$frameRate"
        }
    }

    override inline val isSpecified: Boolean
        get() = this.packedValue != Unspecified.packedValue

    override inline val isUnspecified: Boolean
        get() = this.packedValue == Unspecified.packedValue

    override inline val isBounded: Boolean
        get() = this.packedValue != Unbounded.packedValue

    override inline val isUnbounded: Boolean
        get() = this.packedValue == Unbounded.packedValue

    override inline val isKnown: Boolean
        get() = this.packedValue != Unknown.packedValue

    override inline val isUnknown: Boolean
        get() = this.packedValue == Unknown.packedValue

    inline fun map(block:(MediaTime)->MediaTime) = if(isSpecified) block(this) else this
    inline fun takeOrElse(other: ()->MediaTime) = if(isSpecified) this else other()
    override inline fun takeOrElse(other: ()->Time)  = if(isSpecified) this else other()

    inline fun coerceAtLeast(other: MediaTime) = if(other > this) other else this
    inline fun coerceAtMost(other: MediaTime) = if(other < this) other else this

}

inline val Time.isConcrete : Boolean get() = this.isSpecified && this.isKnown && this.isBounded

inline fun abs(time: MediaTime) = if(time.frameNumber<0L) -time else time

object MediaTimeSaver : Saver<MediaTime,Long> {
    override fun restore(value: Long) = MediaTime(packedValue = value)
    override fun SaverScope.save(value: MediaTime) = value.packedValue
}

inline fun MediaTime(frameNumber: Long, frameRate: FrameRate) = MediaTime(packMediaTime(frameNumber, frameRate))

@Immutable
@kotlin.jvm.JvmInline
value class SecondsRange @PublishedApi internal constructor(@PublishedApi internal val value: ClosedFloatingPointRange<Double>) : ClosedFloatingPointRange<Seconds> {
    override val endInclusive: Seconds get() = Seconds(value.endInclusive)
    override val start: Seconds get() = Seconds(value.start)
    override fun lessThanOrEquals(a: Seconds, b: Seconds): Boolean = a <= b
}

@Immutable
@kotlin.jvm.JvmInline
value class Seconds @PublishedApi internal constructor(@PublishedApi internal val value: Double) : TimeType<Seconds> {
    override inline fun toNanos(): Nanos = Nanos((value * 1_000_000_000).toLong())
    override inline fun toMicros(): Micros = Micros((value * 1_000_000).toLong())
    override inline fun toMillis(): Millis = Millis((value * 1_000).toLong())
    override inline fun toSeconds(): Seconds = this
    override inline fun toMediaTime(frameRate: FrameRate): MediaTime = MediaTime((value * frameRate.toDouble()).toLong(),frameRate)
    override inline fun times(other: Int): Seconds = Seconds(value * other)
    override inline fun times(other: Long): Seconds = Seconds(value * other)
    override inline fun times(other: Float): Seconds = Seconds(value * other)
    override inline fun times(other: Double): Seconds = Seconds(value * other)
    override inline fun div(other: Int): Seconds = Seconds(value / other)
    override inline fun div(other: Long): Seconds = Seconds(value / other)
    override inline fun div(other: Float): Seconds = Seconds(value / other)
    override inline fun div(other: Double): Seconds = Seconds(value / other)
    inline operator fun plus(other: Int): Seconds = Seconds(value + other)
    inline operator fun plus(other: Long): Seconds = Seconds(value + other)
    inline operator fun plus(other: Float): Seconds = Seconds(value + other)
    inline operator fun plus(other: Double): Seconds = Seconds(value + other)
    inline operator fun minus(other: Int): Seconds = Seconds(value - other)
    inline operator fun minus(other: Long): Seconds = Seconds(value - other)
    inline operator fun minus(other: Float): Seconds = Seconds(value - other)
    inline operator fun minus(other: Double): Seconds = Seconds(value - other)
    override inline operator fun unaryPlus(): Seconds = this
    override inline operator fun unaryMinus(): Seconds = Seconds(-value)
    override inline operator fun rangeTo(other: Seconds): ClosedRange<Seconds> = SecondsRange(value .. other.value)
    override inline infix fun until(other: Seconds): ClosedRange<Seconds> = SecondsRange(value .. other.value.nextDown())
    override inline operator fun rem(other: Seconds): Seconds = Seconds(value % other.value)
    override inline operator fun div(other: Seconds): Double = value / other.value
    override inline operator fun times(other: Seconds): Seconds = Seconds(value * other.value)
    override inline operator fun minus(other: Seconds): Seconds = Seconds(value - other.value)
    override inline operator fun plus(other: Seconds): Seconds = Seconds(value + other.value)
    override inline operator fun compareTo(other: Seconds): Int = value.compareTo(other.value)
    inline fun asDouble() = value
    inline fun asFloat() = value.toFloat()
}

@Immutable
@kotlin.jvm.JvmInline
value class MillisRange @PublishedApi internal constructor(@PublishedApi internal val value: LongRange) : ClosedRange<Millis> {
    override val endInclusive: Millis get() = Millis(value.last)
    override val start: Millis get() = Millis(value.first)
}

@Immutable
@kotlin.jvm.JvmInline
value class Millis @PublishedApi internal constructor(@PublishedApi internal val value: Long) : TimeType<Millis> {
    override inline fun toNanos(): Nanos = Nanos(value * 1_000_000)
    override inline fun toMicros(): Micros = Micros(value * 1_000)
    override inline fun toMillis(): Millis = this
    override inline fun toSeconds(): Seconds = Seconds(value.toDouble() / 1000.0)
    override inline fun toMediaTime(frameRate: FrameRate): MediaTime = MediaTime((value * frameRate.toLong() / 1000),frameRate)
    override inline fun times(other: Int): Millis = Millis(value * other)
    override inline fun times(other: Long): Millis = Millis(value * other)
    override inline fun times(other: Float): Millis = Millis((value * other.toDouble()).toLong())
    override inline fun times(other: Double): Millis = Millis((value * other).toLong())
    override inline fun div(other: Int): Millis = Millis(value / other)
    override inline fun div(other: Long): Millis = Millis(value / other)
    override inline fun div(other: Float): Millis = Millis((value / other.toDouble()).toLong())
    override inline fun div(other: Double): Millis = Millis((value / other).toLong())
    override inline operator fun unaryPlus(): Millis = this
    override inline operator fun unaryMinus(): Millis = Millis(-value)
    override inline operator fun rangeTo(other: Millis): ClosedRange<Millis> = MillisRange(value.. other.value)
    override inline infix fun until(other: Millis): ClosedRange<Millis> = MillisRange(value until other.value)
    override inline operator fun rem(other: Millis): Millis = Millis(value % other.value)
    override inline operator fun div(other: Millis): Double = value.toDouble() / other.value.toDouble()
    override inline operator fun times(other: Millis): Millis = Millis(value * other.value)
    override inline operator fun minus(other: Millis): Millis = Millis(value - other.value)
    override inline operator fun plus(other: Millis): Millis = Millis(value + other.value)
    override inline operator fun compareTo(other: Millis): Int = value.compareTo(other.value)
    inline fun asLong() = value
}
@Immutable
@kotlin.jvm.JvmInline
value class MicrosRange @PublishedApi internal constructor(@PublishedApi internal val value: LongRange) : ClosedRange<Micros> {
    override val endInclusive: Micros get() = Micros(value.last)
    override val start: Micros get() = Micros(value.first)
}
@Immutable
@kotlin.jvm.JvmInline
value class Micros @PublishedApi internal constructor(@PublishedApi internal val value: Long) : TimeType<Micros> {
    override inline fun toNanos(): Nanos = Nanos(value * 1_000)
    override inline fun toMicros(): Micros = Micros(value)
    override inline fun toMillis(): Millis = Millis(value / 1_000)
    override inline fun toSeconds(): Seconds = Seconds(value.toDouble() / 1_000_000.0)
    override inline fun toMediaTime(frameRate: FrameRate): MediaTime = MediaTime((value * frameRate.toLong() / 1_000_000),frameRate)
    override inline fun times(other: Int): Micros = Micros(value * other)
    override inline fun times(other: Long): Micros = Micros(value * other)
    override inline fun times(other: Float): Micros = Micros((value * other.toDouble()).toLong())
    override inline fun times(other: Double): Micros = Micros((value * other).toLong())
    override inline fun div(other: Int): Micros = Micros(value / other)
    override inline fun div(other: Long): Micros = Micros(value / other)
    override inline fun div(other: Float): Micros = Micros((value / other.toDouble()).toLong())
    override inline fun div(other: Double): Micros = Micros((value / other).toLong())
    override inline operator fun unaryPlus(): Micros = this
    override inline operator fun unaryMinus(): Micros = Micros(-value)
    override inline operator fun rangeTo(other: Micros): ClosedRange<Micros> = MicrosRange(value.. other.value)
    override inline infix fun until(other: Micros): ClosedRange<Micros> = MicrosRange(value until other.value)
    override inline operator fun rem(other: Micros): Micros = Micros(value % other.value)
    override inline operator fun div(other: Micros): Double = value.toDouble() / other.value.toDouble()
    override inline operator fun times(other: Micros): Micros = Micros(value * other.value)
    override inline operator fun minus(other: Micros): Micros = Micros(value - other.value)
    override inline operator fun plus(other: Micros): Micros = Micros(value + other.value)
    override inline operator fun compareTo(other: Micros): Int = value.compareTo(other.value)
    inline fun asLong() = value
}
@Immutable
@kotlin.jvm.JvmInline
value class NanosRange @PublishedApi internal constructor(@PublishedApi internal val value: LongRange) : ClosedRange<Nanos> {
    override val endInclusive: Nanos get() = Nanos(value.last)
    override val start: Nanos get() = Nanos(value.first)
}

@Immutable
@kotlin.jvm.JvmInline
value class Nanos @PublishedApi internal constructor(@PublishedApi internal val value: Long) : TimeType<Nanos> {
    override inline fun toNanos(): Nanos = this
    override inline fun toMicros(): Micros = Micros(value / 1_000)
    override inline fun toMillis(): Millis = Millis(value / 1_000_000)
    override inline fun toSeconds(): Seconds = Seconds(value.toDouble() / 1_000_000_000.0)
    override inline fun toMediaTime(frameRate: FrameRate): MediaTime = MediaTime((value * frameRate.toLong() / 1_000_000_000),frameRate)
    override inline fun times(other: Int): Nanos = Nanos(value * other)
    override inline fun times(other: Long): Nanos = Nanos(value * other)
    override inline fun times(other: Float): Nanos = Nanos((value * other.toDouble()).toLong())
    override inline fun times(other: Double): Nanos = Nanos((value * other).toLong())
    override inline fun div(other: Int): Nanos = Nanos(value / other)
    override inline fun div(other: Long): Nanos = Nanos(value / other)
    override inline fun div(other: Float): Nanos = Nanos((value / other.toDouble()).toLong())
    override inline fun div(other: Double): Nanos = Nanos((value / other).toLong())
    override inline operator fun unaryPlus(): Nanos = this
    override inline operator fun unaryMinus(): Nanos = Nanos(-value)
    override inline operator fun rangeTo(other: Nanos): ClosedRange<Nanos> = NanosRange(value.. other.value)
    override inline infix fun until(other: Nanos): ClosedRange<Nanos> = NanosRange(value until other.value)
    override inline operator fun rem(other: Nanos): Nanos = Nanos(value % other.value)
    override inline operator fun div(other: Nanos): Double = value.toDouble() / other.value.toDouble()
    override inline operator fun times(other: Nanos): Nanos = Nanos(value * other.value)
    override inline operator fun minus(other: Nanos): Nanos = Nanos(value - other.value)
    override inline operator fun plus(other: Nanos): Nanos = Nanos(value + other.value)
    override inline operator fun compareTo(other: Nanos): Int = value.compareTo(other.value)
    inline fun asLong() = value
}

inline val Double.seconds : Seconds get() = Seconds(this)
inline val Float.seconds : Seconds get() = Seconds(this.toDouble())
inline val Int.seconds : Seconds get() = Seconds(this.toDouble())

inline val Long.millis : Millis get() = Millis(this)
inline val Int.millis : Millis get() = Millis(this.toLong())

inline val Long.nanos : Nanos get() = Nanos(this)
inline val Int.nanos : Nanos get() = Nanos(this.toLong())

inline val Long.micros : Micros get() = Micros(this)
inline val Int.micros : Micros get() = Micros(this.toLong())

@PublishedApi
internal fun packMediaTime(frameNumber: Long, frameRate: FrameRate): Long {
    return frameNumber.shl(16) or (frameRate.toLong() and 0xFFFF)
}

@PublishedApi
internal fun unpackFrameNumber(value: Long): Long {
    return value.shr(16)
}

@PublishedApi
internal fun unpackFrameRate(value: Long): FrameRate {
    return value.and(0xFFFF).toUShort().toInt().fps
}

@PublishedApi
internal fun unpackFrameRateOrFail(value: Long, failure: ()->Nothing): FrameRate {
    return value.and(0xFFFF).toUShort().toInt().toFpsOrFailWith(failure)
}

@PublishedApi
internal fun unpackRawFrameRate(value: Long): Int {
    return value.and(0xFFFF).toUShort().toInt()
}

@PublishedApi
internal inline fun lcm(a: Long, b: Long): Long {
    return a * (b / gcd(a, b))
}

@PublishedApi
internal tailrec fun gcd( a : Long, b: Long ) : Long {
    return if( b == 0L )
        a
    else
        gcd(b, a % b)
}

@PublishedApi
internal inline fun lcm(a: Int, b: Int): Int {
    return a * (b / gcd(a, b))
}

@PublishedApi
internal inline fun lcm(a: FrameRate, b: FrameRate): FrameRate {
    return lcm(a.toInt(),b.toInt()).fps
}

@PublishedApi
internal tailrec fun gcd( a : Int, b: Int ) : Int {
    return if( b == 0 )
        a
    else
        gcd(b, a % b)
}


@PublishedApi
internal inline fun MediaTime.binaryMediaTimeOp(other: MediaTime, crossinline operation:(a:Long, b:Long)->Long) : MediaTime {
    if(other.frameRate == frameRate ) return MediaTime(operation(frameNumber,other.frameNumber), frameRate)
    return mismatchedBinaryMediaTimeOp(other){ a, b -> operation(a,b)}
}

@PublishedApi
internal inline fun <T> MediaTime.binaryOp(other: MediaTime, crossinline operation:(a:MediaTime, b:MediaTime)->T) : T {
    if(other.frameRate == frameRate ) return operation(this,other)
    return mismatchedBinaryOp(other){ a, b -> operation(a,b)}
}

@PublishedApi
internal fun <T> MediaTime.mismatchedBinaryOp(other: MediaTime, operation:(a:MediaTime, b:MediaTime)->T) : T {
    require(other.frameRate != frameRate ) {"Expected ${other.frameRate} != $frameRate"}
    val newFrameRate = lcm(frameRate,other.frameRate)
    return operation(this.toFrameRate(newFrameRate), other.toFrameRate(newFrameRate))
}


@PublishedApi
internal fun MediaTime.mismatchedBinaryMediaTimeOp(other: MediaTime, operation:(a:Long, b:Long)->Long) : MediaTime {
    require(other.frameRate != frameRate ) {"Expected ${other.frameRate} != $frameRate"}
    val newFrameRate = lcm(frameRate,other.frameRate)
    return MediaTime(operation(this.toFrameRate(newFrameRate).frameNumber, other.toFrameRate(newFrameRate).frameNumber), newFrameRate)
}

fun TimeType<*>.formatAsSeconds(secondsWidth: Int = 0, decimalPlaces: Int = 3, secondsPadding: Char = ' ') : String {
    val seconds = toSeconds().asDouble()
    val wholeSeconds = (seconds + 0.5/10.0.pow(decimalPlaces)).toInt().toString()
    val fractionalSeconds = if(decimalPlaces<1) "" else (seconds * 10.0.pow(decimalPlaces)).roundToInt().toString().takeLast(decimalPlaces).let { "." + "0".repeat((decimalPlaces-it.length).coerceAtLeast(0))+ it }
    return wholeSeconds.padStart(secondsWidth,secondsPadding) + fractionalSeconds
}

fun TimeType<*>.formatAsMinutesSeconds(minutesWidth: Int = 2, decimalPlaces: Int = 3, hideZeroMin : Boolean = true) : String {
    val seconds = toSeconds()
    val minutes = (seconds.asDouble() / 60).toInt()
    val remainingSeconds = seconds - minutes * 60

    return (if(minutes > 0 || !hideZeroMin) minutes.toString().padStart(minutesWidth,'0') + ":" else "") +
            remainingSeconds.formatAsSeconds(secondsWidth = 2, decimalPlaces= decimalPlaces, secondsPadding = '0')
}

fun TimeType<*>.formatAsHoursMinutesSeconds(hoursWidth: Int = 2, decimalPlaces: Int = 3) : String {
    val seconds = toSeconds()
    val hours = (seconds.asDouble() / (60*60)).toInt()
    val minutes = ((seconds.asDouble() - hours*60*60) / 60).toInt()
    val remainingSeconds = seconds - minutes * 60 - hours * 60 * 60
    return (hours.toString().padStart(hoursWidth, '0') + ":" +
            minutes.toString().padStart(2, '0') + ":" +
            remainingSeconds.formatAsSeconds(decimalPlaces))
}

fun TimeType<*>.formatAsSecondsAndFrames(frameRate: FrameRate, secondsWidth: Int = 0, secondsPadding: Char = ' ') : String {
    val frameRateWidth = when {
        frameRate >= 1000 -> 4
        frameRate >= 100 -> 3
        frameRate >= 10 -> 2
        else -> 1
    }
    val wholeSeconds = toSeconds().asDouble().toInt().toString().padStart(secondsWidth,secondsPadding)
    return wholeSeconds + ":" + (toMediaTime(frameRate).frameNumber % frameRate).toString().padStart(frameRateWidth,'0')
}

fun TimeType<*>.formatAsMinutesSecondsFrames(minutesWidth: Int = 2, frameRate: FrameRate) : String {
    val seconds = toSeconds()
    val minutes = (seconds.asDouble() / 60).toInt()
    val remainingSeconds = seconds - minutes * 60
    return minutes.toString().padStart(minutesWidth,'0') + ":" + remainingSeconds.formatAsSecondsAndFrames(frameRate,2,'0')
}

fun TimeType<*>.formatAsHoursMinutesSecondsFrames(hoursWidth: Int = 2, frameRate: FrameRate) : String {
    val seconds = toSeconds()
    val hours = (seconds.asDouble() / (60*60)).toInt()
    val minutes = ((seconds.asDouble() - hours*60*60) / 60).toInt()
    val remainingSeconds = seconds - minutes * 60 - hours * 60 * 60
    return (hours.toString().padStart(hoursWidth, '0') + ":" +
            minutes.toString().padStart(2, '0') + ":" +
            remainingSeconds.formatAsSecondsAndFrames(frameRate,2,'0'))
}
