package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal interface ComposedOpus {
    val treeState: StateFlow<OpusTree>
    fun dispose()
}

internal class ComposedOpusImpl(
    val opus: OpusImpl,
    val wrapper: @Composable (comp: ComposedOpus, content: @Composable () -> Unit) -> Unit,
    parent: CompositionContext,
) : ComposedOpus, OpusNodeApplierOwner {

    private val applier = OpusNodeApplierImpl(RootNode(), this)
    val composition = Composition(
        applier = applier,
        parent = parent
    )

    fun setContent(content: @Composable ()->Unit) {
        composition.setContent { wrapper(this) { content() } }
    }

    private val _treeState = MutableStateFlow(OpusTree())
    override val treeState = _treeState.asStateFlow()

    override fun dispose() {
        composition.dispose()
        opus.endComposition(this)
    }

    override fun onBeginApplyChanges() {
        _treeState.update { it.copy(applyingChanges = true) }
    }

    override fun onEndApplyChanges() {
        // TODO: Do we need to make a copy of applier.root subtree???
        applier.root.updateTiming()
        _treeState.update { it.copy(
            applyingChanges = false,
            changeSerial = it.changeSerial + 1,
            root = applier.root,
            config = opus.activeConfig
        ) }
    }
}
