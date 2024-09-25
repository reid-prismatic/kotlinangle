package org.prismatic.kotlinangle.opuscopy

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import kotlinx.cinterop.*
import org.prismatic.kotlinangle.getAngleWrapper
import platform.AppKit.*
import platform.CoreGraphics.*
import platform.Foundation.*

@Composable
internal actual fun PlatformOpusView(
	treeFlow: StateFlow<OpusTree>,
	modifier: Modifier,
	windowHandle: Long,
	rendererFactory: () -> OpusRenderer
) {
	val nsView = remember { createNSView() }
	val angle = remember { getAngleWrapper(windowHandle) }

	DisposableEffect(Unit) {
		// Attach the NSView to your window
		attachNSViewToCompose(nsView)
		onDispose {
			// Cleanup if necessary
		}
	}

	Box {
		Text("MacOS Main")
	}

}

@OptIn(ExperimentalForeignApi::class)
fun createNSView(): NSView {
	return NSView().apply {
		setFrame(CGRectMake(0.0, 0.0, 300.0, 300.0))
		wantsLayer = true
		layer?.backgroundColor = CGColorCreateGenericRGB(0.5, 0.7, 0.9, 1.0)
	}
}

fun attachNSViewToCompose(nsView: NSView) {
	val nsWindow = NSApplication.sharedApplication.mainWindow
	nsWindow?.contentView?.addSubview(nsView)
}