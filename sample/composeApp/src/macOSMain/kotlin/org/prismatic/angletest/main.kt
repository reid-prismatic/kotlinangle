@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package org.prismatic.angletest

import kotlinx.cinterop.*
import platform.AppKit.*
import platform.Foundation.*
import platform.darwin.NSObject


@OptIn(ExperimentalForeignApi::class)
fun main() {
    val app = NSApplication.sharedApplication()
    app.setActivationPolicy(NSApplicationActivationPolicy.NSApplicationActivationPolicyRegular)

    memScoped {
        val delegate = object : NSObject(), NSApplicationDelegateProtocol {
            override fun applicationDidFinishLaunching(notification: NSNotification) {
                val window = createWindow()
                window.makeKeyAndOrderFront(null)
                app.activateIgnoringOtherApps(true)
            }
        }
        app.delegate = delegate
        app.run()
    }
}

@OptIn(ExperimentalForeignApi::class)
fun createWindow(): NSWindow {
    val window = NSWindow(
        contentRect = NSMakeRect(0.0, 0.0, 800.0, 600.0),
        styleMask = NSWindowStyleMaskTitled or NSWindowStyleMaskClosable or NSWindowStyleMaskResizable,
        backing = NSBackingStoreBuffered,
        defer = false
    )
}

//fun main() = application {
//    Window(
//        onCloseRequest = ::exitApplication,
//        title = "KotlinAngle",
//    ) {
//        //val name = System.getProperty("os.name")
//        //println(name)
//        App(window.windowHandle)
//    }
//}

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