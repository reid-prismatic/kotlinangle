package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import org.prismatic.kotlinangle.opuscopy.composable.core.Group

@Composable
fun ContainerContext.Track(
    startTime: Time = MediaTime.Unspecified,
    duration: Time = MediaTime.Unspecified,
    content: @Composable TrackContext.() -> Unit
) = Group(
    startTime = startTime,
    duration = duration,
    timingRule = EntityTimingRule.Sequential,
    content = {TrackContextImpl.content()}
)

private object TrackContextImpl: TrackContext
