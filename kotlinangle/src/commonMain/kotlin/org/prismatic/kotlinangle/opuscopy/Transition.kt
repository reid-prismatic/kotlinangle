package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import org.prismatic.kotlinangle.opuscopy.composable.core.TimedVisualRender
import org.prismatic.kotlinangle.opuscopy.composable.core.VisualRenderScope

@Composable
fun TrackContext.Transition(duration: Time) {
    TimedVisualRender(duration = -duration.toMediaTime()) {
        renderTransition()
    }
}

@Composable
fun GroupContext.Transition(startTime: Time, duration: Time) {
    TimedVisualRender(startTime = startTime, duration = duration) {
        renderTransition()
    }
}

private fun VisualRenderScope.renderTransition() {
    TODO()
}