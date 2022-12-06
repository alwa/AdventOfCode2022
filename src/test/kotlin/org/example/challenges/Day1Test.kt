package org.example.challenges

import org.example.challenges.Day1
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `Part 1`() {
        assertEquals(60, Day1.part1(File(ClassLoader.getSystemResource("day1/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(92, Day1.part2(File(ClassLoader.getSystemResource("day1/testinput.txt").file)))
    }

}
