package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.snapshots.ObserverHandle
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.prismatic.kotlinangle.AngleWrapper
import org.prismatic.kotlinangle.getAngleWrapper
import org.prismatic.kotlinangle.opuscopy.impl.CommonVisualRenderContext
import org.prismatic.kotlinangle.opuscopy.impl.makeVisualRenderContext
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer
import org.prismatic.kotlinangle.opuscopy.util.intersects

typealias GLVER = AngleWrapper

@OptIn(ExperimentalCoroutinesApi::class)
private val displayDispatcher = Dispatchers.Default.limitedParallelism(1)

internal class OpusDesktopRenderer(
    private val coroutineScope: CoroutineScope,
    private val treeFlow: StateFlow<OpusTree>,
    private val rendererFactory: () -> OpusRenderer
) {

    private var renderContext : CommonVisualRenderContext? = null
    //private var autoDrawable: GLAutoDrawable? = null
    private var unregisterApplyObserver: ObserverHandle? = null
    private var renderer: OpusRenderer? = null

    fun init() {
        //val gl = drawable?.gl?.gL4 ?: return
        //autoDrawable = drawable
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
            //autoDrawable?.display()
        }
    }

    fun dispose() {
        renderer?.dispose()
        renderer = null
        unregisterApplyObserver?.dispose()
        renderContext?.dispose()
        unregisterApplyObserver = null
        renderContext = null
//        autoDrawable = null
    }

    private val readSet = mutableSetOf<Any>()
    private val readObserver: (Any) -> Unit = { readSet.add(it) }

    fun display() {
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

    fun reshape(x: Int, y: Int, width: Int, height: Int) {

    }

    init {
        coroutineScope.launch {
            treeFlow.collect {
                requestDisplay()
            }
        }
    }

}