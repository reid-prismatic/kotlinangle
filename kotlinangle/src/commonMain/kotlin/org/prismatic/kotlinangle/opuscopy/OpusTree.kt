package org.prismatic.kotlinangle.opuscopy

internal data class OpusTree(
    val currentTime: MediaTime = MediaTime.Unspecified,
    val applyingChanges: Boolean = false,
    val changeSerial: Long = 0L,
    val config: OpusConfig = OpusConfig(),
    val root: OpusNode = RootNode()
)

 internal class RootNode : OpusNode() {
     override val isTimedNode: Boolean = true
     override fun getPreferredTiming() = PreferredNodeTiming(
         startTime = MediaTime.ZERO,
         duration = latestChildEndTime
     )
     override fun EntityTimingContext.calculateChildStartTime(): MediaTime =
         with(EntityTimingRule.Concurrent) {calculateEntityStart()}
 }

private fun OpusNode.forEachNode(time: MediaTime, block:(OpusNode)->Unit) {
    if (isTimedNode && time !in globalTiming) return
    block(this)
    children.forEach {
        it.forEachNode(time,block)
    }
}

private fun OpusNode.forEachNode(block:(OpusNode)->Unit) {
    block(this)
    children.forEach {
        it.forEachNode(block)
    }
}

internal fun OpusTree.forEachNode(block:(OpusNode)->Unit) {
    root.forEachNode(block)
}

internal inline fun <reified T:OpusNode> OpusTree.forEachNodeOfType(crossinline block:(T)->Unit) {
    forEachNode { if(it is T) block(it) }
}

internal fun OpusTree.forEachNode(time: MediaTime, block:(OpusNode)->Unit) {
    root.forEachNode(time,block)
}

internal inline fun <reified T:OpusNode> OpusTree.forEachNodeOfType(time: MediaTime, crossinline block:(T)->Unit) {
    forEachNode(time) { if(it is T) block(it) }
}