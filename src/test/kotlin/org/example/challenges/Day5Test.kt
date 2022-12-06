package org.example.challenges

import org.example.challenges.Day5
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun `Part 1`() {
        assertEquals("CMZ", Day5.part1(File(ClassLoader.getSystemResource("day5/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals("MCD",  Day5.part2(File(ClassLoader.getSystemResource("day5/testinput.txt").file)))
    }

}