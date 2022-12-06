package org.example.challenges

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `Part 1`() {
        assertEquals(7, Day6.part1("day6/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals(19, Day6.part2("day6/testinput.txt"))
    }

}
