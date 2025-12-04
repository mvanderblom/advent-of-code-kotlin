package dev.vanderblom.aoc

fun String.splitInHalf(): Pair<String, String> {
    require(length.isEven()) { "length must be even" }
    return this.substring(0, length/2) to this.substring(length/2)
}