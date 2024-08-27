package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

interface EntityTimingContext {
    val parentStart: MediaTime
    val precedingSiblingStart: MediaTime
    val precedingSiblingEnd: MediaTime
    val offset: MediaTime
}

data class MutableEntityTimingContext(
    override var parentStart: MediaTime = MediaTime.Unspecified,
    override var precedingSiblingStart: MediaTime = MediaTime.Unspecified,
    override var precedingSiblingEnd: MediaTime = MediaTime.Unspecified,
    override var offset: MediaTime = MediaTime.Unspecified
) : EntityTimingContext

interface EntityTimingRule {

    fun EntityTimingContext.calculateEntityStart() : MediaTime

    data object Sequential : EntityTimingRule {
        override fun EntityTimingContext.calculateEntityStart(): MediaTime = precedingSiblingEnd + offset
    }

    data object Concurrent : EntityTimingRule {
        override fun EntityTimingContext.calculateEntityStart(): MediaTime = parentStart + offset
    }

    private data class StaggeredImpl(val interval: MediaTime) : EntityTimingRule {
        override fun EntityTimingContext.calculateEntityStart(): MediaTime = precedingSiblingStart + interval + offset
    }

    companion object {
        fun Staggered(interval: Time) : EntityTimingRule = StaggeredImpl(interval.toMediaTime())
    }

}

val LocalEntityTimingRule: ProvidableCompositionLocal<EntityTimingRule> = compositionLocalOf { EntityTimingRule.Concurrent }

interface EntityTiming {
    val startTime: MediaTime
    val endTime: MediaTime
    val prerollTime: MediaTime
    companion object {
        val Unspecified = ImmutableEntityTiming.Unspecified
    }
}

operator fun EntityTiming.contains(time: Time) =
    time.isSpecified
            && startTime.isSpecified
            && endTime.isSpecified
            && time.toMediaTime().let { it >= startTime && it < endTime }

data class MutableEntityTiming(
    override var startTime: MediaTime = MediaTime.ZERO,
    override var endTime: MediaTime = MediaTime.ZERO,
    override var prerollTime: MediaTime = MediaTime.ZERO
) : EntityTiming

@Immutable
data class ImmutableEntityTiming(
    override val startTime: MediaTime,
    override val endTime: MediaTime,
    override val prerollTime: MediaTime
) : EntityTiming {
    companion object {
        val Unspecified = ImmutableEntityTiming(MediaTime.Unspecified, MediaTime.Unspecified, MediaTime.Unspecified)
    }
}

fun MutableEntityTiming.immutable() = ImmutableEntityTiming(startTime, endTime, prerollTime)