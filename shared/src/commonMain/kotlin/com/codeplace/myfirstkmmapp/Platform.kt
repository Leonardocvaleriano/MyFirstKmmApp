package com.codeplace.myfirstkmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform