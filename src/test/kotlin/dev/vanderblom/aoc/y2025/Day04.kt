package dev.vanderblom.aoc.y2025

import dev.vanderblom.aoc.AbstractDay
import dev.vanderblom.aoc.DataGrid
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day04 : AbstractDay() {

    @Test
    @Order(1)
    fun `part one - example`() {
        assertThat(partOne(exampleInput))
            .isEqualTo(13)
    }

    @Test
    @Order(2)
    fun `part one - actual`() {
        assertThat(partOne(actualInput))
            .isEqualTo(1344)
    }

    @Test
    @Order(3)
    fun `part two - example`() {
        assertThat(partTwo(exampleInput))
            .isEqualTo(2)
    }

    @Test
    @Order(4)
    fun `part two - actual`() {
        assertThat(partTwo(actualInput))
            .isEqualTo(1)
    }

    private fun partOne(input: List<String>): Int {
        val grid = DataGrid(input)
        val positions = grid
            .toList()
            .filter { (coord, char) ->
                char == '@' && coord
                    .getSurrounding(grid, includeSelf = false)
                    .all()
                    .count { c -> c == "@" } < 4
            }
            .map { it.first }

        grid.withCharsAtReplacedBy(positions, 'X').showMe()

        return positions.size
    }

    private fun partTwo(input: List<String>): Int {
        return input.size
    }
}