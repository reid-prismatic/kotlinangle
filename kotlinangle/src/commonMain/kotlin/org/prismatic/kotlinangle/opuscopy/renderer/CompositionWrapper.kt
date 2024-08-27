package org.prismatic.kotlinangle.opuscopy.renderer

import androidx.compose.runtime.Composable
import org.prismatic.kotlinangle.opuscopy.ContainerContext
import org.prismatic.kotlinangle.opuscopy.composable.core.Camera

@Composable
internal fun ContainerContext.CompositionWrapper(content: @Composable ContainerContext.()->Unit) {
    Camera(priority = -1000)
    content()
}