package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun `Part 1`() {
        assertEquals(13, Day9.part1(File(ClassLoader.getSystemResource("day9/testinput.txt").file)))
    }

}
