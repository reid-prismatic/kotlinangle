package org.prismatic.kotlinangle.opuscopy.composable.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.IntSize
import org.prismatic.kotlinangle.opuscopy.EntityContext
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusNodeApplier
import org.prismatic.opus.math.Mat4x4
import org.prismatic.opus.math.Vec2
import org.prismatic.opus.math.Vec3
import org.prismatic.opus.math.scale
import org.prismatic.opus.math.times
import org.prismatic.opus.math.translate
import kotlin.jvm.JvmInline

@Composable
fun EntityContext.Transform(
    matrixTransformer: (Mat4x4)->Mat4x4,
    content: @Composable EntityContext.()->Unit
) {
    ComposeNode<TransformNode, OpusNodeApplier>(
        factory = { TransformNode() },
        update = {
            set(matrixTransformer) { this.matrixTransformer = matrixTransformer }
        },
        content = {content()}
    )
}

@Composable
inline fun EntityContext.Transform(
    matrix: Mat4x4,
    noinline content: @Composable EntityContext.()->Unit
) = Transform({it * matrix},content)

@Composable
inline fun EntityContext.Offset(
    offset: Vec3,
    noinline content: @Composable EntityContext.()->Unit
) = Transform({it * Mat4x4.translate(offset.x,offset.y,offset.z)},content)

@Composable
inline fun EntityContext.Scale(
    scale: Float,
    noinline content: @Composable EntityContext.()->Unit
) = Transform({it * Mat4x4.scale(scale,scale,scale)},content)

internal class TransformNode : OpusNode() {
    override val isTimedNode: Boolean = false
    var matrixTransformer: (Mat4x4)->Mat4x4 = {it}
}
