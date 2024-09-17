package org.prismatic.angletest

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.prismatic.angletest.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinAngle",
    ) {
        App(window.windowHandle)
    }
}