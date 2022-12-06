package org.example.challenges

import org.example.challenges.Day3
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun `Part 1`() {
        assertEquals(157, Day3.part1(File(ClassLoader.getSystemResource("day3/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(70,  Day3.part2(File(ClassLoader.getSystemResource("day3/testinput.txt").file)))
    }

}