package org.prismatic.kotlinangle.opuscopy

internal data class PreferredNodeTiming(

    /**
     * The preferred start time for a node, relative to the start of its parent.
     *
     * If this is [Unspecified][MediaTime.Unspecified], the start time will be determined by the
     * parent by calling [calculateChildStartTime][OpusNode.calculateChildStartTime] (for example,
     * this is how a [GroupNode] applies its timing rules to its children.
     */
    val startTime: MediaTime = MediaTime.Unspecified,

    /**
     * The preferred duration for this node.
     *
     * If this is [Unspecified][MediaTime.Unspecified], it will default to zero.
     *
     * If this is [Unbounded][MediaTime.Unbounded], it will extend to the end of the parent node,
     * and will not participate in determining the parent duration. In this case, [startTime] must
     * be specified.
     *
     * If [startTime] is  [Unspecified][MediaTime.Unspecified], this can be negative. Negative
     * values still result in a positive duration when the node timing is fully resolved, but will
     * cause the sibling nodes to overlap.
     */
    val duration: MediaTime = MediaTime.Unspecified,

    /**
     * The amount of preroll time required for this node. If this is unspecified, the node does not
     * need preroll and [onPreroll][OpusNode.onPreroll] will not be called.
     */
    val prerollDuration: MediaTime = MediaTime.Unspecified,
) {
    companion object {
        val Unspecified = PreferredNodeTiming(
            startTime = MediaTime.Unspecified,
            duration = MediaTime.Unspecified,
            prerollDuration = MediaTime.Unspecified
        )
    }
}