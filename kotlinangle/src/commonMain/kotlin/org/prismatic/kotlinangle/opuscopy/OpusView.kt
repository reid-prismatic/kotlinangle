package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRenderer
import org.prismatic.kotlinangle.opuscopy.renderer.OpusRendererImpl

@Composable
fun OpusView(player: OpusPlayer, modifier: Modifier = Modifier) {
	if (player !is OpusPlayerImpl) return

	PlatformOpusView(
		treeFlow = player.treeFlow,
		modifier = modifier,
		rendererFactory = { OpusRendererImpl() },
	)
}

@Composable
internal expect fun PlatformOpusView(
	treeFlow: StateFlow<OpusTree>,
	modifier: Modifier = Modifier,
	rendererFactory: () -> OpusRenderer,
)

