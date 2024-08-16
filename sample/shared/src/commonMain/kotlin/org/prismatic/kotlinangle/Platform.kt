package org.prismatic.kotlinangle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform