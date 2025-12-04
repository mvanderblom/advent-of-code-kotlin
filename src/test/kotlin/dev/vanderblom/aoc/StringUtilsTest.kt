package dev.vanderblom.aoc

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StringUtilsTest {

    @Test
    fun name() {
        assertThat("AapAap".splitInHalf()).isEqualTo("Aap" to "Aap")
    }
}