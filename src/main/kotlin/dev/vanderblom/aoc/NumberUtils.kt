package dev.vanderblom.aoc

fun Int.toString(length: Int, padChar: Char = ' ') = this.toString().padStart(length,padChar)
fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 == 1
