package org.example.challenges

import org.example.challenges.Day3
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun `Part 1`() {
        assertEquals(157, Day3.part1("day3/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals(70, Day3.part2("day3/testinput.txt"))
    }

}