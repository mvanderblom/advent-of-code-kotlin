package dev.vanderblom.aoc.y2025

import dev.vanderblom.aoc.AbstractDay
import dev.vanderblom.aoc.showMe
import dev.vanderblom.aoc.toString
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import kotlin.math.abs

class Day01 : AbstractDay() {

    @Test
    @Order(1)
    fun `part one - example`() {
        assertThat(partOne(exampleInput))
            .isEqualTo(3)
    }

    @Test
    @Order(2)
    fun `part one - actual`() {
        assertThat(partOne(actualInput))
            .isEqualTo(1154)
    }

    @Test
    @Order(3)
    fun `part two - example`() {
        assertThat(partTwo(exampleInput))
            .isEqualTo(6)
    }

    @Test
    @Order(4)
    fun `part two - actual`() {
        assertThat(partTwo(actualInput))
            .isEqualTo(6819)
        // 7596
        // 7404
        // 5823
        // 7058
        // 6798
    }

    private fun partOne(input: List<String>, initialDialValue: Int = 50): Int {
        val operations = parseInput(input)

        var dial = initialDialValue
        var zeroCount = 0
        operations.forEach {
            dial = (dial + it + 100) % 100
            if (dial == 0) zeroCount++
        }
        return zeroCount

    }

    @Test
    fun `0-1=0`() {
        assertThat(partTwo(listOf("L1"), 0))
            .isEqualTo(0)
    }

    @Test
    fun `0-100=1`() {
        assertThat(partTwo(listOf("L100"), 0))
            .isEqualTo(1)
    }

    @Test
    fun `0-200=2`() {
        assertThat(partTwo(listOf("L200"), 0))
            .isEqualTo(2)
    }

    @Test
    fun `0+1=0`() {
        assertThat(partTwo(listOf("R1"), 0))
            .isEqualTo(0)
    }

    @Test
    fun `0+100=1`() {
        assertThat(partTwo(listOf("R100"), 0))
            .isEqualTo(1)
    }

    @Test
    fun `0+200=2`() {
        assertThat(partTwo(listOf("R200"), 0))
            .isEqualTo(2)
    }

    @Test
    fun `99+1=1`() {
        assertThat(partTwo(listOf("R1"), 99))
            .isEqualTo(1)
    }

    @Test
    fun `99+100=1`() {
        assertThat(partTwo(listOf("R100"), 99))
            .isEqualTo(1)
    }

    @Test
    fun `99+101=2`() {
        assertThat(partTwo(listOf("R101"), 99))
            .isEqualTo(2)
    }

    @Test
    fun `left on boundary`() {
        assertThat(partTwo(listOf("L9"), 9))
            .isEqualTo(1)
    }

    @Test
    fun `left on boundary twice`() {
        assertThat(partTwo(listOf("L109"), 9))
            .isEqualTo(2)
    }

    @Test
    fun `left over boundary`() {
        assertThat(partTwo(listOf("L10"), 9))
            .isEqualTo(1)
    }

    @Test
    fun `left over boundary twice`() {
        assertThat(partTwo(listOf("L110"), 9))
            .isEqualTo(2)
    }

    @Test
    fun `right on boundary`() {
        assertThat(partTwo(listOf("R90"), 9))
            .isEqualTo(0)
    }

    @Test
    fun `right over boundary`() {
        assertThat(partTwo(listOf("R91"), 9))
            .isEqualTo(1)
    }

    @Test
    fun `start at 0`() {
        assertThat(partTwo(listOf("L1"), 0))
            .isEqualTo(0)
        assertThat(partTwo(listOf("R1"), 0))
            .isEqualTo(0)
    }

    @Test
    fun `start at 0+200`() {
        assertThat(partTwo(listOf("R200"), 0))
            .isEqualTo(2)
    }

    @Test
    fun `dafuq`() {
        assertThat(partTwo(listOf("R48", "L5"), 52))
            .isEqualTo(1)
    }

    private fun partTwo(input: List<String>, initialDialPos: Int = 50): Int {
        val operations = parseInput(input)
        println("The dial starts by pointing at $initialDialPos.")
        var dialPos = initialDialPos
        var zeroCount = 0

        operations.forEachIndexed { index, operation ->
            val i = if (operation < 0) -1 else 1

            var zeroCountDelta = 0

            repeat(abs(operation)) {
                dialPos += 1 * i
                if (dialPos == -1) {
                    dialPos = 99
                }
                if (dialPos == 100) {
                    dialPos = 0
                }
                if (dialPos == 0)
                    zeroCountDelta++
            }
            zeroCount += zeroCountDelta

            print(
                "The dial is rotated $operation " +
                        "to point at $dialPos; "
            )

            if (zeroCountDelta > 0)
                print("zeroCountDelta: $zeroCountDelta, zeroCount: $zeroCount")
            println()
        }
        return zeroCount
    }

    private fun parseInput(input: List<String>): List<Int> {
        val operations = input
            .map {
                val amount = it.substring(1).toInt()
                if (it[0] == 'L') {
                    amount * -1
                } else {
                    amount
                }
            }
        return operations
    }
}