package org.prismatic.kotlinangle.opuscopy.util

import androidx.compose.ui.util.fastForEach
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T> List<T>.fastForEachInRange(index: Int, count: Int, action: (T) -> Unit) {
    contract { callsInPlace(action) }
    for (i in index..<(index+count)) {
        val item = get(i)
        action(item)
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <T> List<T>.fastAnyInRange(index: Int, count: Int, predicate: (T) -> Boolean): Boolean {
    contract { callsInPlace(predicate) }
    fastForEachInRange(index,count) { if (predicate(it)) return true }
    return false
}
