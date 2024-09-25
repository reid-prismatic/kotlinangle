package org.prismatic.angletest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.prismatic.kotlinangle.opuscopy.OpusView
import org.prismatic.kotlinangle.opuscopy.composable.core.VisualRender
import org.prismatic.kotlinangle.opuscopy.rememberOpusPlayer

import org.prismatic.kotlinangle.AngleWrapper
import org.prismatic.kotlinangle.getAngleWrapper
//import org.prismatic.opus.visualrender.VisualRender

//import org.prismatic.opus.Opus
//import org.prismatic.opus.OpusView
//import org.prismatic.opus.visualrender.VisualRender

@Composable
@Preview
fun App(windowHandle: Long = 0) {
	MaterialTheme {
		Column {
//			Box(
//				Modifier
//					.padding(50.dp)
//					.height(500.dp)
//			) {
//				val color = Color.Blue
//				val angle = remember { getAngleWrapper() }
//
//				val opus = remember {
//					org.prismatic.opus.Opus {
//						VisualRender {
//							onRender {
//								angle.fillScreenRGB(color.red, color.green, color.blue)
//							}
//						}
//					}
//				}
//				org.prismatic.opus.OpusView(opus, Modifier.fillMaxWidth().height(400.dp))
//			}


			Box(Modifier
				.padding(50.dp)
				.height(500.dp)
			) {
				val color = Color.Red
				//val angle = remember { getAngleWrapper() }

				val opus = remember {
					org.prismatic.kotlinangle.opuscopy.Opus {
						VisualRender {
							onRender {
								fillScreenRGB(color.red, color.green, color.blue)
							}
						}
					}
				}
				val opusPlayer = rememberOpusPlayer(opus)
				OpusView(opusPlayer, Modifier.fillMaxWidth().height(400.dp), windowHandle)
			}
		}
	}
}

