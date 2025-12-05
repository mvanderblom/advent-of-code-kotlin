package dev.vanderblom.aoc.y2025

import dev.vanderblom.aoc.AbstractDay
import dev.vanderblom.aoc.isEqual
import dev.vanderblom.aoc.isEven
import dev.vanderblom.aoc.splitInHalf
import dev.vanderblom.aoc.toRange
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day02 : AbstractDay() {

    @Test
    @Order(1)
    fun `part one - example`() {
        assertThat(partOne(exampleInput))
            .isEqualTo(1227775554L)
    }

    @Test
    @Order(2)
    fun `part one - actual`() {
        assertThat(partOne(actualInput))
            .isEqualTo(28846518423L)
    }

    @Test
    @Order(3)
    fun `part two - example`() {
        assertThat(partTwo(exampleInput))
            .isEqualTo(4174379265)
    }

    @Test
    @Order(4)
    fun `part two - actual`() {
        assertThat(partTwo(actualInput))
            .isEqualTo(1)
    }

    private fun partOne(input: List<String>) = parseInput(input[0])
        .map { it.toString() }
        .filter { it.length.isEven() }
        .filter { it.splitInHalf().isEqual() }
        .sumOf { it.toLong() }


    private fun partTwo(input: List<String>) = parseInput(input[0])
        .map { it.toString() }
        .filter { it.isInvalidId() }
        .sumOf { it.toLong() }

    private fun parseInput(input: String) = input
        .split(",")
        .map { it.toRange("-") }
        .flatMap { it.toList() }

    @Test
    fun `1 is invalid`() {
        assertThat("1".isInvalidId()).isFalse
    }

    @Test
    fun `12341234 is invalid`() {
        assertThat("12341234".isInvalidId()).isTrue
    }

    @Test
    fun `123123123 is invalid`() {
        assertThat("123123123".isInvalidId()).isTrue
    }

    @Test
    fun `1212121212 is invalid`() {
        assertThat("1212121212".isInvalidId()).isTrue
    }

    @Test
    fun `1111111 is invalid`() {
        assertThat("1111111".isInvalidId()).isTrue
    }

    fun String.isInvalidId(): Boolean {
        if (length > 1 && this.groupBy { it }.size == 1) {
            println(this)
            return true
        }

        repeat(length / 2) { i ->
            if (chunked(i + 1)
                    .groupBy { it }
                    .size == 1
            ) {
                println(this)
                return true
            }
        }

        return false
    }
}