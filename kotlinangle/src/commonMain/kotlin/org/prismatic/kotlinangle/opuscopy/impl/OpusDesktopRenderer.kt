//package org.prismatic.kotlinangle.opuscopy.impl
//
//import androidx.compose.runtime.snapshots.ObserverHandle
//import androidx.compose.runtime.snapshots.Snapshot
//import com.jogamp.opengl.GL4
//import com.jogamp.opengl.GL4.*
//import com.jogamp.opengl.GLAutoDrawable
//import com.jogamp.opengl.GLEventListener
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import org.prismatic.kotlinangle.AngleWrapper.Companion.GL_FLOAT
//import org.prismatic.kotlinangle.AngleWrapper.Companion.GL_INT
//import org.prismatic.kotlinangle.opuscopy.OpusTree
//import org.prismatic.kotlinangle.opuscopy.renderer.Mesh
//import org.prismatic.kotlinangle.opuscopy.renderer.OpusMesh
//import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer
//import org.prismatic.kotlinangle.opuscopy.renderer.OpusTexture
//import org.prismatic.kotlinangle.opuscopy.util.intersects
//
//typealias GLVER = GL4
//
//@OptIn(ExperimentalCoroutinesApi::class)
//private val displayDispatcher = Dispatchers.Default.limitedParallelism(1)
//
//internal class OpusDesktopRenderer(
//    private val coroutineScope: CoroutineScope,
//    private val treeFlow: StateFlow<OpusTree>,
//    private val rendererFactory: () -> OpusRenderer
//) : GLEventListener {
//
//    private var renderContext : DesktopVisualRenderContext? = null
//    private var autoDrawable: GLAutoDrawable? = null
//    private var unregisterApplyObserver: ObserverHandle? = null
//    private var renderer: OpusRenderer? = null
//
//    override fun init(drawable: GLAutoDrawable?) {
//        val gl = drawable?.gl?.gL4 ?: return
//        autoDrawable = drawable
//        renderContext = makeVisualRenderContext(gl)
//        renderer = rendererFactory()
//        unregisterApplyObserver = Snapshot.registerApplyObserver { changed, _ ->
//            if(readSet.intersects(changed)) {
//                readSet.clear()
//                requestDisplay()
//            }
//        }
//    }
//
//    fun requestDisplay() {
//        coroutineScope.launch(displayDispatcher) {
//            autoDrawable?.display()
//        }
//    }
//
//    override fun dispose(drawable: GLAutoDrawable?) {
//        renderer?.dispose()
//        renderer = null
//        unregisterApplyObserver?.dispose()
//        renderContext?.dispose()
//        unregisterApplyObserver = null
//        renderContext = null
//        autoDrawable = null
//    }
//
//    private val readSet = mutableSetOf<Any>()
//    private val readObserver: (Any) -> Unit = { readSet.add(it) }
//
//    override fun display(drawable: GLAutoDrawable?) {
//        readSet.clear()
//        val tree = treeFlow.value
//        val context = renderContext ?: return
//        val currentRenderer = renderer ?: return
//        context.playheadTime = tree.currentTime
//        // It's important to observe changes to State that are ready by the `renderBlock` lambda
//        // because, even though playhead time changes (during seeking/playback) cause re-rendering
//        // automatically, if there is an external change (for example, the user adjusting something
//        // in UI) that isn't referened in any @Composable block, but IS referenced in a render block,
//        // we need to know about it so we can trigger a re-render.
//        Snapshot.takeSnapshot(readObserver).run {
//            try {
//                enter{
//                    with(currentRenderer) {
//                        context.renderTree(tree)
//                    }
//                }
//            } finally {
//                dispose()
//            }
//        }
//    }
//
//    override fun reshape(drawable: GLAutoDrawable?, x: Int, y: Int, width: Int, height: Int) {
//
//    }
//
//    init {
//        coroutineScope.launch {
//            treeFlow.collect {
//                requestDisplay()
//            }
//        }
//    }
//
//}
//
//internal class DesktopOpusTexture(
//    var name: GLTextureName?,
//    override val width: Int,
//    override val height: Int
//) : OpusTexture
//
//internal data class DesktopOpusMesh(
//    val primitiveType: Int,
//    val vertexCount: Int,
//    val stride: Int,
//    val attributes: Map<Mesh.Attr.Tag, Mesh.Attr>,
//    val attributeBuffer: GLBufferName,
//    val elementBuffer: GLBufferName?
//) : OpusMesh
//
//internal val Mesh.Attr.Type.glType get() = when(this) {
//    Mesh.Attr.FLOAT -> GL_FLOAT
//    Mesh.Attr.INT -> GL_INT
//}
