package org.prismatic.kotlinangle.opuscopy.composable.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.prismatic.kotlinangle.opuscopy.ContainerContext
import org.prismatic.kotlinangle.opuscopy.EntityContext
import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.Time
import org.prismatic.kotlinangle.opuscopy.composable.core.Entity
import org.prismatic.kotlinangle.opuscopy.composable.core.VisualRender
import org.prismatic.kotlinangle.opuscopy.seconds

@Composable
fun ContainerContext.SolidColorClip(
    color: Color,
    startTime: Time = MediaTime.Unspecified,
    duration: Time = 1.seconds
) {
    Entity(startTime, duration) {
        VisualRender {
            onRender {
                fillScreenRGB(color.red, color.green, color.blue)
            }
        }
    }
}
