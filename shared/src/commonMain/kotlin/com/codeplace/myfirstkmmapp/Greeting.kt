package com.codeplace.myfirstkmmapp

import kotlin.random.Random

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        val firstWord = if(Random.nextBoolean()) "Hi" else "Hello"
        return "$firstWord guess what this is! > ${platform.name.reversed()} "
    }
}