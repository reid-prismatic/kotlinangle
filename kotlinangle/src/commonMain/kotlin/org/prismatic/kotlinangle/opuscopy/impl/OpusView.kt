//package org.prismatic.kotlinangle.opuscopy.impl
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.awt.SwingPanel
//import androidx.compose.ui.graphics.Color
//import com.jogamp.opengl.GLCapabilities
//import com.jogamp.opengl.GLProfile
//import com.jogamp.opengl.awt.GLCanvas
//import kotlinx.coroutines.flow.StateFlow
//import org.prismatic.kotlinangle.opuscopy.impl.OpusDesktopRenderer
//import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer
//import java.awt.BorderLayout
//import java.awt.Dimension
//import javax.swing.JPanel
//
//@Composable
//internal actual fun PlatformOpusView(
//    treeFlow: StateFlow<OpusTree>,
//    modifier: Modifier,
//    rendererFactory: () -> OpusRenderer,
//) {
//    val coroutineScope = rememberCoroutineScope()
//    SwingPanel(
//        background = Color.Black,
//        modifier = modifier,
//        update = {},
//        factory = {
//            JPanel().apply {
//                layout = BorderLayout()
//                val glprofile = GLProfile.getGL4ES3()
//                val glcaps = GLCapabilities(glprofile)
//                val canvas = GLCanvas(glcaps)
//                canvas.addGLEventListener(OpusDesktopRenderer(coroutineScope, treeFlow, rendererFactory))
//                canvas.preferredSize = Dimension(200,100)
//                add(canvas, BorderLayout.CENTER)
//            }
//        }
//    )
//}
//
