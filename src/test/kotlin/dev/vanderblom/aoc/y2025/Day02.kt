package dev.vanderblom.aoc.y2025

import dev.vanderblom.aoc.AbstractDay
import dev.vanderblom.aoc.isEqual
import dev.vanderblom.aoc.isEven
import dev.vanderblom.aoc.splitInHalf
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
            .isEqualTo(1L)
    }

    @Test
    @Order(3)
    fun `part two - example`() {
        assertThat(partTwo(exampleInput))
            .isEqualTo(1)
    }

    @Test
    @Order(4)
    fun `part two - actual`() {
        assertThat(partTwo(actualInput))
            .isEqualTo(1)
    }

    private fun partOne(input: List<String>): Long {
        return parseInput(input)
            .map { it.toString() }
            .filter { it.length.isEven() }
            .filter { it.splitInHalf().isEqual() }
            .sumOf { it.toLong() }
    }

    private fun parseInput(input: List<String>): List<Long> {
        return input[0]
            .split(",")
            .map { it.split("-").map(String::toLong) }
            .flatMap { (from, to)  -> (from..to).toList()}
    }

    private fun partTwo(input: List<String>): Int {
        return input.size
    }
}