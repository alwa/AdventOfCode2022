package org.example.challenges

import org.example.challenges.Day2
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun `Part 1`() {
        assertEquals(15, Day2.part1(File(ClassLoader.getSystemResource("day2/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(12,  Day2.part2(File(ClassLoader.getSystemResource("day2/testinput.txt").file)))
    }

}