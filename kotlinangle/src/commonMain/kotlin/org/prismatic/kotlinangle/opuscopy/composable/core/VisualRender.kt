package org.prismatic.kotlinangle.opuscopy.composable.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import org.prismatic.kotlinangle.opuscopy.ContainerContext
import org.prismatic.kotlinangle.opuscopy.EntityContext
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusNodeApplier
import org.prismatic.kotlinangle.opuscopy.Time
import org.prismatic.kotlinangle.opuscopy.renderer.Mesh
import org.prismatic.kotlinangle.opuscopy.renderer.OpusMesh
import org.prismatic.kotlinangle.opuscopy.renderer.VisualRenderContext
import org.prismatic.kotlinangle.opuscopy.toMediaTime

@Composable
fun EntityContext.VisualRender(block: VisualRenderScope.()->Unit) {
    ComposeNode<BasicVisualRenderNode, OpusNodeApplier>(
        factory = { BasicVisualRenderNode() },
        update = {
            set(block) {
                val def = VisualRenderScopeImpl().apply(block)
                onRender = def.onRenderBlock
                onPostRender = def.onPostRenderBlock
            }
        },
        content = {}
    )
}

@Immutable
interface GraphicsTexture

@Immutable
internal class GraphicsTextureImpl : GraphicsTexture

@Immutable
interface GraphicsMesh

@Immutable
internal class GraphicsMeshImpl : GraphicsMesh

@Composable
fun rememberMesh(key: Any, block:()->Mesh) : GraphicsMesh {
    val meshId = remember(block) {GraphicsMeshImpl()}
    ComposeNode<MeshNode,OpusNodeApplier>(
        factory = { MeshNode() },
        update = {
            set(key) { this.key = key }
            set(block) { meshFactory = block }
            set(meshId) { this.meshId = meshId}
        }
    )
    return meshId
}

@Composable
fun rememberImageTexture(key: Any, block: (IntSize)->ImageBitmap) : GraphicsTexture {
    val textureId = remember(block) {GraphicsTextureImpl()}
    ComposeNode<GraphicsTextureNode,OpusNodeApplier>(
        factory = { GraphicsTextureNode() },
        update = {
            set(key) { this.key = key }
            set(block) { imageFactory = block }
            set(textureId) { this.textureId = textureId}
        }
    )
    return textureId
}

@Composable
fun rememberDrawnTexture(key: Any, width: Int, height: Int, block: DrawScope.() -> Unit) =
    rememberImageTexture(Unit) {
        val ib = ImageBitmap(width, height)
        val canvas = Canvas(ib)
        val scope = CanvasDrawScope()
        scope.drawContext.canvas = canvas
        scope.drawContext.size = Size(ib.width.toFloat(), ib.height.toFloat())
        scope.block()
        ib
    }

@Composable
fun rememberDrawnTexture(key: Any, size: (opusSize: IntSize)->IntSize = {it}, block: DrawScope.() -> Unit) =
    rememberImageTexture(Unit) { opusSize ->
        val resolvedSize = size(opusSize)
        val ib = ImageBitmap(resolvedSize.width, resolvedSize.height)
        val canvas = Canvas(ib)
        val scope = CanvasDrawScope()
        scope.drawContext.canvas = canvas
        scope.drawContext.size = Size(ib.width.toFloat(), ib.height.toFloat())
        scope.block()
        ib
    }


@Composable
fun ContainerContext.TimedVisualRender(
    startTime: Time = MediaTime.Unspecified,
    duration: Time = MediaTime.Unspecified,
    block: VisualRenderScope.() -> Unit
) {
    ComposeNode<TimedVisualRenderNode, OpusNodeApplier>(
        factory = { TimedVisualRenderNode() },
        update = {
            set(block) {
                onRender = VisualRenderScopeImpl().apply(block).onRenderBlock
            }
            set(startTime) {
                this.startTime = it.toMediaTime()
                this.invalidateTiming()
            }
            set(duration) {
                this.duration = it.toMediaTime()
                this.invalidateTiming()
            }
        },
        content = {}
    )
}

interface VisualRenderScope {
    fun onRender(block: VisualRenderContext.()->Unit)
    fun onPostRender(block: VisualRenderContext.()->Unit)
}

private class VisualRenderScopeImpl: VisualRenderScope {
    var onRenderBlock : (VisualRenderContext.() -> Unit)? = null
    var onPostRenderBlock : (VisualRenderContext.() -> Unit)? = null
    override fun onRender(block: VisualRenderContext.() -> Unit) {
        onRenderBlock = block
    }
    override fun onPostRender(block: VisualRenderContext.() -> Unit) {
        onPostRenderBlock = block
    }
}

internal abstract class VisualRenderResourceNode : OpusNode() {
    override val isTimedNode: Boolean = false
}

internal class GraphicsTextureNode : VisualRenderResourceNode() {
    var key: Any? = null
    var textureId: GraphicsTexture? = null
    var imageFactory: ((IntSize)->ImageBitmap)? = null
}

internal class MeshNode : VisualRenderResourceNode() {
    var key: Any? = null
    var meshId: GraphicsMesh? = null
    var meshFactory: (()->Mesh)? = null
}

internal abstract class VisualRenderNode : OpusNode() {
    var onRender : (VisualRenderContext.()->Unit)? = null
    var onPostRender : (VisualRenderContext.()->Unit)? = null
}

internal class TimedVisualRenderNode : VisualRenderNode() {
    override val isTimedNode: Boolean = true
    var startTime: MediaTime = MediaTime.Unspecified
    var duration: MediaTime = MediaTime.Unspecified
}

internal class BasicVisualRenderNode : VisualRenderNode() {
    override val isTimedNode: Boolean = false
}