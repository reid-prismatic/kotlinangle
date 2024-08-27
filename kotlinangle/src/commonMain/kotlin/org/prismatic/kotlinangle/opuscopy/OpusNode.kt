package org.prismatic.kotlinangle.opuscopy

import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

private var nextNodeDebugId = 100L
internal abstract class OpusNode {

    private val debugId = nextNodeDebugId++
    private var _children :ImmutableList<OpusNode> = persistentListOf<OpusNode>()
    val children : List<OpusNode> get() = _children
    var parent : OpusNode? = null
        private set

    fun addChild(index: Int, child: OpusNode) {
        require (!child.isTimedNode || this.isTimedNode) { "Timed node cannot be child of non-timed node" }
        _children = _children.toPersistentList().add(index,child)
        child.parent = this
    }

    fun removeChildren(index: Int, count: Int) {
        _children = _children.toPersistentList().mutate { mutableChildren ->
            for (i in index..<index + count)
                mutableChildren[i].parent = null
            mutableChildren.remove(index, count)
        }
    }

    fun moveChildren(from: Int, to: Int, count: Int) {
        _children = _children.toPersistentList().mutate { mutableChildren ->
            mutableChildren.move(from, to, count)
        }
    }

    fun removeAllChildren() {
        _children.forEach { it.parent = null }
        _children = persistentListOf()
    }

    abstract val isTimedNode : Boolean

    private var _localEntityTimingCache : MutableEntityTiming? = null
    private val _localEntityTiming : MutableEntityTiming get() {
        check(isTimedNode)
        return _localEntityTimingCache
            ?: (MutableEntityTiming().also { _localEntityTimingCache = it })
    }
    val localTiming : EntityTiming get() = _localEntityTimingCache ?: EntityTiming.Unspecified

    private var _globalEntityTimingCache : MutableEntityTiming? = null
    private val _globalEntityTiming : MutableEntityTiming get() {
        check(isTimedNode)
        return _globalEntityTimingCache
            ?: (MutableEntityTiming().also { _globalEntityTimingCache = it })
    }
    val globalTiming : EntityTiming get() = _globalEntityTimingCache ?: EntityTiming.Unspecified

    private var localTimingValid : Boolean = false
    fun invalidateTiming() {
        if(isTimedNode)
            localTimingValid = false
        parent?.invalidateTiming()
    }

    private fun invalidateSubtreeTiming() {
        if(isTimedNode) {
            localTimingValid = false
            children.forEach { it.invalidateSubtreeTiming() }
        }
    }

    fun updateTiming() {

        // We always start from the root to update timing, so if this isn't the root, pass
        // the call up the chain.
        parent?.let {
            it.updateTiming()
            return
        }
        check(parent==null)

        // If we got here, this is the root node, so we can start from here to update timing.

        // Timing invalidation is passed up the chain, so if the root local timing is valid, the
        // timing of the entire tree is implicitly valid and there's nothing to do.
        if( localTimingValid ) return
        check(isTimedNode) // The root node MUST be a timed node

        // Update the local timing for all of the child nodes.
        updateChildTiming()

        //
        val preferredTiming = getPreferredTiming()

        // updateChildTiming() handles timing updates for everything except the root node's timing
        // itself, so we do that here.
        val resolvedStartTime = preferredTiming.startTime.takeOrElse { MediaTime.ZERO }
        val resolvedDuration = preferredTiming.duration.takeOrElse { MediaTime.ZERO }
        _localEntityTiming.apply {
            startTime = resolvedStartTime
            endTime =
                if (resolvedDuration.isUnbounded) {
                    MediaTime.Unbounded
                } else {
                    resolvedStartTime + resolvedDuration
                }
            prerollTime = preferredTiming.prerollDuration.map { resolvedStartTime - it }
        }
        localTimingValid = true

        // Now that each node's local timing is valid, we recurse the entire tree and update the
        // global timing for each node. Unlike local timing, which is calculated bottom-up, global
        // timing works top-down, as each node is offset by the start time of its parent nodes.
        updateGlobalTiming()
    }

    private fun updateGlobalTiming() {
        if( !isTimedNode ) return
        val parentStart = parent?.globalTiming?.startTime ?: MediaTime.ZERO
        val parentEnd = parent?.globalTiming?.endTime
            ?: if(localTiming.endTime.isBounded) {
                localTiming.endTime
            } else {
                latestChildEndTime
            }
        _globalEntityTiming.apply {
            startTime = (parentStart + localTiming.startTime.takeOrElse { MediaTime.ZERO }).coerceAtLeast(parentStart)
            endTime = (if(localTiming.endTime.isBounded) parentStart + localTiming.endTime else parentEnd).coerceAtMost(parentEnd)
            prerollTime = localTiming.prerollTime.map { parentStart + it }
        }
        children.fastForEach { it.updateGlobalTiming() }
    }

    protected var latestChildEndTime : MediaTime = MediaTime.Unspecified
        private set

    open fun getPreferredTiming() : PreferredNodeTiming = PreferredNodeTiming.Unspecified
    open fun EntityTimingContext.calculateChildStartTime() : MediaTime = MediaTime.Unspecified

    protected open fun onEnter() {}
    protected open fun onExit() {}
    protected open suspend fun onPreroll() {}

    private fun updateChildTiming() {
        var entityTimingContext: MutableEntityTimingContext? = null
        latestChildEndTime = MediaTime.ZERO

        children.fastForEachIndexed { index, child ->
            if (!child.isTimedNode) {
                // If a node is not timed, it's local timing doesn't apply and is therefore implicitly valid
                child.localTimingValid = true
            } else {

                // Timing invalidation is passed up the chain, so if this child has valid local timing,
                // so do it's own children implicitly.
                if (!child.localTimingValid)
                    child.updateChildTiming()

                // Some nodes base their preferred timing on the latest child node, so we have to wait until
                // after the child's children's timing has been calculated before calculating the child's
                // own preferred timing.
                val preferredTiming = child.getPreferredTiming()

                // Even if the child's local timing was valid, that's in respect to its own children
                // (local timing is processed bottom-up) but it may still be invalid in relation to
                // its own siblings. If we got here, it means THIS node's timing was invalidated,
                // which could mean either the node itself of one of its immediate children. Since this
                // is going to be either that immediate child or a sibling thereof, we recalculate.
                val childDuration = preferredTiming.duration
                    .takeOrElse { MediaTime.ZERO }
                    .let { abs(it) }
                val overlapDuration =
                    if (preferredTiming.startTime.isSpecified) {
                        MediaTime.ZERO
                    } else if( preferredTiming.duration.isUnbounded ) {
                        MediaTime.ZERO
                    } else {
                        preferredTiming.duration
                            .takeOrElse { MediaTime.ZERO }
                            .coerceAtMost(MediaTime.ZERO)
                            .let { abs(it) }
                    }
                val childStartTime = preferredTiming.startTime
                    .takeOrElse {
                        val context = entityTimingContext
                            ?: MutableEntityTimingContext().also {
                                it.parentStart = MediaTime.ZERO
                                it.offset = MediaTime.ZERO
                                it.precedingSiblingStart = MediaTime.ZERO
                                it.precedingSiblingEnd = MediaTime.ZERO
                                entityTimingContext = it
                            }
                        context.precedingSiblingEnd = (context.precedingSiblingEnd-overlapDuration)
                            .coerceAtLeast(MediaTime.ZERO)
                        context.calculateChildStartTime()
                            .also {
                                context.precedingSiblingStart = it
                                context.precedingSiblingEnd = (it + (if(childDuration.isUnbounded) MediaTime.ZERO else childDuration) - overlapDuration)
                                    .coerceAtLeast(MediaTime.ZERO)
                            }
                    }.takeOrElse { MediaTime.ZERO }
                val childEndTime = if(childDuration.isUnbounded) MediaTime.Unbounded else (childStartTime + childDuration)
                val childPrerollTime =
                    childStartTime - preferredTiming.prerollDuration.takeOrElse { MediaTime.ZERO }
                child._localEntityTiming.let {
                    it.startTime = childStartTime
                    it.endTime = childEndTime
                    it.prerollTime = childPrerollTime
                }

                if( childEndTime.isBounded )
                    latestChildEndTime = latestChildEndTime.coerceAtLeast(childEndTime)
                child.localTimingValid = true
            }
        }

    }

    companion object {

        private fun <T> MutableList<T>.move(from: Int, to: Int, count: Int) {
            val dest = if (from > to) to else to - count
            if (count == 1) {
                if (from == to + 1 || from == to - 1) {
                    // Adjacent elements, perform swap to avoid backing array manipulations.
                    val fromEl = get(from)
                    val toEl = set(to, fromEl)
                    set(from, toEl)
                } else {
                    val fromEl = removeAt(from)
                    add(dest, fromEl)
                }
            } else {
                val subView = subList(from, from + count)
                val subCopy = subView.toMutableList()
                subView.clear()
                addAll(dest, subCopy)
            }
        }

        private fun <T> MutableList<T>.remove(index: Int, count: Int) {
            if (count == 1) {
                removeAt(index)
            } else {
                subList(index, index + count).clear()
            }
        }

    }
}

