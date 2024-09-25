package org.prismatic.angletest

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
//import org.prismatic.angletest.App
//import platfrom.
//import platform.AppKit.NSWindow

//import org.jetbrains.skiko.HardwareLayer
import org.jetbrains.skiko.SkiaLayer

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinAngle",
    ) {
        App(window.windowHandle)
    }
}

//// Platform-specific wrapper for NSView
//@Composable
//fun ComposeNSViewWrapper() {
//    val nativeView = remember { createMyNSView() }
//
//    // Access the native window via ComposeWindow
//    LaunchedEffect(Unit) {
//        val composeWindow = LocalAppWindow.current.window
//        composeWindow.contentView?.addSubview(nativeView)
//    }
//}
//
//// Create your NSView (native macOS)
//fun createMyNSView(): NSView {
//    val nsView = NSView()
//    // Configure NSView properties
//    nsView.setFrame(CGRectMake(0.0, 0.0, 200.0, 200.0)) // Example frame
//    return nsView
//}