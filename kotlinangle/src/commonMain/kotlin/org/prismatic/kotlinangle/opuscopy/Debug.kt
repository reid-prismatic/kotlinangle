package org.prismatic.kotlinangle.opuscopy

const val PRINT_DEBUG_TRACE = true
inline fun Any.opusDebugTrace(source: String, sourceObject: Any = this, message:()->String) {
    if(PRINT_DEBUG_TRACE) {
        println("[OPUS-TRACE $source(${sourceObject.debugId})] ${message()}")
    }
}

inline fun opusDebugTrace(message:()->String) {
    if(PRINT_DEBUG_TRACE) {
        println("[OPUS-TRACE] ${message()}")
    }
}

@OptIn(ExperimentalStdlibApi::class)
inline val Any.debugId : String get() = hashCode().toHexString().take(4)
