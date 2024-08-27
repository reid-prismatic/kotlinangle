package org.prismatic.opus

import androidx.compose.runtime.snapshots.ObserverHandle
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.jogamp.opengl.GL4
import com.jogamp.opengl.GL4.*
import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.prismatic.kotlinangle.AngleWrapper
import org.prismatic.kotlinangle.getAngleWrapper
import org.prismatic.kotlinangle.opuscopy.OpusTree
import org.prismatic.kotlinangle.opuscopy.impl.CommonVisualRenderContext
import org.prismatic.kotlinangle.opuscopy.impl.makeVisualRenderContext
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer
import org.prismatic.kotlinangle.opuscopy.util.intersects
import org.prismatic.opus.math.Mat4x4
import java.nio.IntBuffer

typealias GLVER = AngleWrapper

@OptIn(ExperimentalCoroutinesApi::class)
private val displayDispatcher = Dispatchers.Default.limitedParallelism(1)

internal class OpusDesktopRenderer(
    private val coroutineScope: CoroutineScope,
    private val treeFlow: StateFlow<OpusTree>,
    private val rendererFactory: () -> OpusRenderer
) : GLEventListener {

    private var renderContext : CommonVisualRenderContext? = null
    private var autoDrawable: GLAutoDrawable? = null
    private var unregisterApplyObserver: ObserverHandle? = null
    private var renderer: OpusRenderer? = null

    override fun init(drawable: GLAutoDrawable?) {
        //val gl = drawable?.gl?.gL4 ?: return
        autoDrawable = drawable
        renderContext = makeVisualRenderContext(getAngleWrapper())
        renderer = rendererFactory()
        unregisterApplyObserver = Snapshot.registerApplyObserver { changed, _ ->
            if(readSet.intersects(changed)) {
                readSet.clear()
                requestDisplay()
            }
        }
    }

    fun requestDisplay() {
        coroutineScope.launch(displayDispatcher) {
            autoDrawable?.display()
        }
    }

    override fun dispose(drawable: GLAutoDrawable?) {
        renderer?.dispose()
        renderer = null
        unregisterApplyObserver?.dispose()
        renderContext?.dispose()
        unregisterApplyObserver = null
        renderContext = null
        autoDrawable = null
    }

    private val readSet = mutableSetOf<Any>()
    private val readObserver: (Any) -> Unit = { readSet.add(it) }

    override fun display(drawable: GLAutoDrawable?) {
        readSet.clear()
        val tree = treeFlow.value
        val context = renderContext ?: return
        val currentRenderer = renderer ?: return
        context.playheadTime = tree.currentTime
        // It's important to observe changes to State that are ready by the `renderBlock` lambda
        // because, even though playhead time changes (during seeking/playback) cause re-rendering
        // automatically, if there is an external change (for example, the user adjusting something
        // in UI) that isn't referened in any @Composable block, but IS referenced in a render block,
        // we need to know about it so we can trigger a re-render.
        Snapshot.takeSnapshot(readObserver).run {
            try {
                enter{
                    with(currentRenderer) {
                        context.renderTree(tree)
                    }
                }
            } finally {
                dispose()
            }
        }
    }

    override fun reshape(drawable: GLAutoDrawable?, x: Int, y: Int, width: Int, height: Int) {

    }

    init {
        coroutineScope.launch {
            treeFlow.collect {
                requestDisplay()
            }
        }
    }

}