package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun `Part 1`() {
        assertEquals(7,Day6.part1(File(ClassLoader.getSystemResource("day6/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(19,  Day6.part2(File(ClassLoader.getSystemResource("day6/testinput.txt").file)))
    }

}
