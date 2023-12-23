package com.codeplace.myfirstkmmapp

import kotlin.random.Random

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet():List<String> = buildList{
        add (if(Random.nextBoolean()) "Hi" else "Hello")
        add( "guess what this is! > ${platform.name.reversed()} ")
        add(daysPhrase())
    }
}