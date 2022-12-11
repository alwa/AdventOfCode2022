package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `Part 1`() {
        assertEquals(10605, Day11.part1(File(ClassLoader.getSystemResource("day11/testinput.txt").file)))
    }

}
