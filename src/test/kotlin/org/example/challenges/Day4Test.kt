package org.example.challenges

import org.example.challenges.Day4
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day4Test {

    @Test
    fun `Part 1`() {
        assertEquals(2, Day4.part1(File(ClassLoader.getSystemResource("day4/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(4,  Day4.part2(File(ClassLoader.getSystemResource("day4/testinput.txt").file)))
    }

}