package org.prismatic.angletest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.prismatic.opus.Opus
import org.prismatic.opus.OpusView
import org.prismatic.opus.visualrender.VisualRender

@Composable
@Preview
fun App() {
	MaterialTheme {
		Box(Modifier.padding(50.dp)) {
			val color = Color.Red
			val emptyOpus = remember {
				Opus {
					VisualRender {
						onRender {
							fillScreenRGB(color.red, color.green, color.blue)
						}
					}
				}
			}
			OpusView(emptyOpus, Modifier.size(300.dp))
		}
	}
}
