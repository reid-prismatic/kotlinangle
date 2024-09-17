package org.prismatic.kotlinangle.opuscopy

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.StateFlow
import org.prismatic.kotlinangle.getAngleWrapper
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer

@Composable
internal actual fun PlatformOpusView(
	treeFlow: StateFlow<OpusTree>,
	modifier: Modifier,
	windowHandle: Long,
	rendererFactory: () -> OpusRenderer,
) {
	val coroutineScope = rememberCoroutineScope()
	AngleView(modifier, treeFlow, windowHandle)

//	SwingPanel(
//		background = Color.Black,
//		modifier = modifier,
//		update = {},
//		factory = {
//			JPanel().apply {
//				layout = BorderLayout()
//				val glprofile = GLProfile.getGL4ES3()
//				val glcaps = GLCapabilities(glprofile)
//				val canvas = GLCanvas(glcaps)
//				canvas.addGLEventListener(OpusDesktopRenderer(coroutineScope, treeFlow, rendererFactory))
//
//
//
//				canvas.preferredSize = Dimension(200,100)
//				add(canvas, BorderLayout.CENTER)
//			}
//		}
//	)
}

@Composable
private fun AngleView(modifier: Modifier, treeFlow: StateFlow<OpusTree>, windowHandle: Long,) {
	//val windowHandle = LocalAngleViewHost.current.windowHandle
	val angle = remember { getAngleWrapper(windowHandle) }
	DisposableEffect(windowHandle) {
		//angle.init(windowHandle)
		onDispose {
			//angle.destroy()
		}
	}
	val treeState by treeFlow.collectAsState()
	val cl = Color.Red
	LaunchedEffect(treeState) {
		angle.setCurrent()
		//renderer.render(treeState)
		//angle.clearCurrent()
		angle.fillScreenRGB(cl.red, cl.green, cl.blue)
	}

	//Box(modifier.onGloballyPositioned { angle.setPosition(it.positionInRoot(), it.size) }) {
	Box(modifier) {

	}
}

private data class AngleViewHost( val windowHandle: Any )

private val LocalAngleViewHost = compositionLocalOf<AngleViewHost> { throw IllegalStateException() }