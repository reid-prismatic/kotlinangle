package org.prismatic.kotlinangle.opuscopy.composable.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import org.prismatic.kotlinangle.opuscopy.ContainerContext
import org.prismatic.kotlinangle.opuscopy.EntityTimingContext
import org.prismatic.kotlinangle.opuscopy.EntityTimingRule
import org.prismatic.kotlinangle.opuscopy.GroupContext
import org.prismatic.kotlinangle.opuscopy.LocalEntityTimingRule
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusNodeApplier
import org.prismatic.kotlinangle.opuscopy.PreferredNodeTiming
import org.prismatic.kotlinangle.opuscopy.Time
import org.prismatic.kotlinangle.opuscopy.toMediaTime

@Composable
fun ContainerContext.Group(
    startTime: Time = MediaTime.Unspecified,
    duration: Time = MediaTime.Unspecified,
    timingRule: EntityTimingRule = LocalEntityTimingRule.current,
    content: @Composable GroupContext.() -> Unit
) {
    ComposeNode<GroupNode, OpusNodeApplier>(
        factory = { GroupNode() },
        update = {
            set(timingRule) {this.timingRule = it}
            set(startTime) {
                this.startTime = it.toMediaTime()
                this.invalidateTiming()
            }
            set(duration) {
                this.duration = it.toMediaTime()
                this.invalidateTiming()
            }
        },
        content = { GroupContextImpl.content()}
    )
}

@Composable
internal fun RootGroup(content: @Composable ContainerContext.() -> Unit) = GroupContextImpl.Group(content=content)

private object GroupContextImpl: GroupContext

internal class GroupNode(
    var timingRule : EntityTimingRule = EntityTimingRule.Concurrent,
    var startTime: MediaTime = MediaTime.Unspecified,
    var duration: MediaTime = MediaTime.Unspecified
) : OpusNode() {
    override val isTimedNode: Boolean = true
    override fun getPreferredTiming() = PreferredNodeTiming(
        startTime = startTime,
        duration = duration.takeOrElse { latestChildEndTime }
    )

    override fun EntityTimingContext.calculateChildStartTime(): MediaTime =
        with(timingRule) { calculateEntityStart() }
}