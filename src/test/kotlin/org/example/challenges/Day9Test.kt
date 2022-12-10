package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun `Part 1`() {
        assertEquals(13, Day9.part1(File(ClassLoader.getSystemResource("day9/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(1, Day9.part2(File(ClassLoader.getSystemResource("day9/testinput.txt").file)))
        assertEquals(36, Day9.part2(File(ClassLoader.getSystemResource("day9/testinput2.txt").file)))
    }

}
