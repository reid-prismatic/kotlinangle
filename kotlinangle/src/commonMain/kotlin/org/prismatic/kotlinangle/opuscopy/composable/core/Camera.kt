package org.prismatic.kotlinangle.opuscopy.composable.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import org.prismatic.kotlinangle.opuscopy.EntityContext
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusNodeApplier
import org.prismatic.opus.math.Mat4x4
import org.prismatic.opus.math.Vec3
import org.prismatic.opus.math.lookAt

@Composable
fun EntityContext.Camera(
    position: Vec3 = Vec3(0f,0f,-10f),
    lookAt: Vec3 = Vec3.ZERO,
    up: Vec3 = Vec3(0f, 1f, 0f),
    transform: Mat4x4 =  Mat4x4.lookAt(
        eye = position,
        center = lookAt,
        upDir = up
    ),
    projection: ProjectionType = ProjectionType.Perspective,
    fovAxis: Axis = Axis.Horizontal,
    fov: Float = 60f,
    priority: Int = 0
) {
    ComposeNode<CameraNode, OpusNodeApplier>(
        factory = { CameraNode() },
        update = {
            set(transform) { this.transform = transform }
            set(projection) { this.projection = projection }
            set(fovAxis) { this.fovAxis = fovAxis }
            set(fov) { this.fov = fov }
            set(priority) { this.priority = priority }
        }
    )
}

internal class CameraNode : OpusNode() {
    override val isTimedNode: Boolean = false
    var transform: Mat4x4 = Mat4x4.IDENTITY
    var projection: ProjectionType = ProjectionType.Perspective
    var fovAxis: Axis = Axis.Horizontal
    var fov: Float = 60f
    var priority: Int = 0
}


enum class ProjectionType {
    Perspective, Orthographic
}

enum class Axis {
    Horizontal, Vertical
}