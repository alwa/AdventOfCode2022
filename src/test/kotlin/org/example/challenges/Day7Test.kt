package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun `Part 1`() {
        assertEquals(95437,Day7.part1(File(ClassLoader.getSystemResource("day7/testinput.txt").file)))
    }

}
