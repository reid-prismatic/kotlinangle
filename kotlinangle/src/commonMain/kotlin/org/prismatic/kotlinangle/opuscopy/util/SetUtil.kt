package org.prismatic.kotlinangle.opuscopy.util

internal fun <T> Set<T>.intersects(other: Set<T>): Boolean =
    if (size < other.size) any { it in other } else other.any { it in this }
