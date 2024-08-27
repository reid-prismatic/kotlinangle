package org.prismatic.kotlinangle.opuscopy

import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.Applier
import androidx.compose.ui.util.fastAny
import org.prismatic.kotlinangle.opuscopy.util.fastAnyInRange

internal interface OpusNodeApplier : Applier<OpusNode>

internal class OpusNodeApplierImpl(root: RootNode, val owner: OpusNodeApplierOwner) : OpusNodeApplier, AbstractApplier<OpusNode>(root) {

    override fun insertBottomUp(index: Int, instance: OpusNode) {}

    override fun insertTopDown(index: Int, instance: OpusNode) {
        opusDebugTrace("Applier") { "insertTopDown" }
        current.addChild(index, instance)
        if( instance.isTimedNode ) current.invalidateTiming()
    }

    override fun move(from: Int, to: Int, count: Int) {
        opusDebugTrace("Applier") { "move" }
        current.moveChildren(from, to, count)
        if( current.isTimedNode ) current.invalidateTiming()
    }

    override fun onClear() {
        opusDebugTrace("Applier") { "onClear" }
        root.removeAllChildren()
        root.invalidateTiming()
    }

    override fun remove(index: Int, count: Int) {
        opusDebugTrace("Applier") { "remove" }
        if(current.children.fastAnyInRange(index,count) { it.isTimedNode })
            current.invalidateTiming()
        current.removeChildren(index, count)
    }

    override fun onBeginChanges() {
        opusDebugTrace("Applier") { "onBeginChanges" }
        super<AbstractApplier>.onBeginChanges()
        owner.onBeginApplyChanges()
    }

    override fun onEndChanges() {
        opusDebugTrace("Applier") { "onEndChanges" }
        super<AbstractApplier>.onEndChanges()
        owner.onEndApplyChanges()
    }

}

internal interface OpusNodeApplierOwner {
    fun onBeginApplyChanges()
    fun onEndApplyChanges()
}
