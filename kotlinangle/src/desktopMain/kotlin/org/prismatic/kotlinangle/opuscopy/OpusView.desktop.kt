package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer

@Composable
internal actual fun PlatformOpusView(
	treeFlow: StateFlow<OpusTree>,
	modifier: Modifier,
	rendererFactory: () -> OpusRenderer
) {
}