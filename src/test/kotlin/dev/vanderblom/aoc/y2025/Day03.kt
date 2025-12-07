package dev.vanderblom.aoc.y2025

import dev.vanderblom.aoc.AbstractDay
import dev.vanderblom.aoc.showMe
import dev.vanderblom.aoc.toIntList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day03 : AbstractDay() {

    @Test
    @Order(1)
    fun `part one - example`() {
        assertThat(partOne(exampleInput))
            .isEqualTo(357)
    }

    @Test
    @Order(2)
    fun `part one - actual`() {
        assertThat(partOne(actualInput))
            .isEqualTo(16927)
    }

    private fun partOne(input: List<String>) = input
        .map { it.toIntList() }
        .sumOf { bank ->
            val firstHighIndex = bank
                .subList(0, bank.size - 1)
                .indexOfMax()

            val fromIndex = firstHighIndex + 1
            val secondHighIndex = bank
                .subList(fromIndex, bank.size)
                .indexOfMax()
                .let { it + fromIndex }

            "${bank[firstHighIndex]}${bank[secondHighIndex]}".toInt()
        }

    @Test
    @Order(3)
    fun `part two - example`() {
        assertThat(partTwo(exampleInput))
            .isEqualTo(3121910778619L)
    }

    @Test
    @Order(4)
    fun `part two - actual`() {
        assertThat(partTwo(actualInput))
            .isEqualTo(167384358365132L)
    }

    private fun partTwo(input: List<String>): Long {
        return input
            .map { it.toIntList() }
            .sumOf {
                it.getHighest(12)
                    .showMe()
                    .toLong()
            }

    }

    @Test
    fun `example 2`() {
        val highest = "811111111111119".toIntList().getHighest(2)
        println(highest)
        assertThat(highest).isEqualTo("89")
    }

    @Test
    fun `example 3`() {
        val highest = "234234234234278".toIntList().getHighest(2)
        println(highest)
        assertThat(highest).isEqualTo("78")
    }

    private fun List<Int>.getHighest(n: Int, acc: String = ""): String {
        if (n == 0) return acc
        if (n == 1) return acc + max()

        val indexOfMax = subList(0, size - (n - 1))
            .indexOfMax()

        return subList(indexOfMax + 1, size).getHighest(n - 1, acc + this[indexOfMax])
    }

    fun List<Int>.indexOfMax() = indexOf(max())
}