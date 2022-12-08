package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun `Part 1`() {
        assertEquals(21, Day8.part1(File(ClassLoader.getSystemResource("day8/testinput.txt").file)))
    }

}
