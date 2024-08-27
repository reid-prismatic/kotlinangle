package org.prismatic.kotlinangle.opuscopy.composable.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import org.prismatic.kotlinangle.opuscopy.ContainerContext
import org.prismatic.kotlinangle.opuscopy.EntityContext
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusNodeApplier
import org.prismatic.kotlinangle.opuscopy.PreferredNodeTiming
import org.prismatic.kotlinangle.opuscopy.Time
import org.prismatic.kotlinangle.opuscopy.toMediaTime

@Composable
fun ContainerContext.Entity(
    startTime: Time = MediaTime.Unspecified,
    duration: Time = MediaTime.Unspecified,
    content: (@Composable EntityContext.() -> Unit)? = null
) {
    ComposeNode<EntityNode, OpusNodeApplier>(
        factory = { EntityNode() },
        update = {
            set(startTime) {
                this.startTime = it.toMediaTime()
                this.invalidateTiming()
            }
            set(duration) {
                this.duration = it.toMediaTime()
                this.invalidateTiming()
            }
        },
        content = {if(content!=null) EntityContextImpl.content()}
    )
}

private object EntityContextImpl: EntityContext

internal class EntityNode(
    var startTime: MediaTime = MediaTime.Unspecified,
    var duration: MediaTime = MediaTime.Unspecified
) : OpusNode() {
    override fun getPreferredTiming() = PreferredNodeTiming(
        startTime = startTime,
        duration = duration.takeOrElse { latestChildEndTime }
    )
    override val isTimedNode: Boolean = true
}
