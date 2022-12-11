package org.example.challenges

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun `Part 1`() {
        assertEquals(13140, Day10.part1(File(ClassLoader.getSystemResource("day10/testinput.txt").file)))
    }

    @Test
    fun `Part 2`() {
        assertEquals(
            "##..##..##..##..##..##..##..##..##..##..\n" +
                    "###...###...###...###...###...###...###.\n" +
                    "####....####....####....####....####....\n" +
                    "#####.....#####.....#####.....#####.....\n" +
                    "######......######......######......####\n" +
                    "#######.......#######.......#######.....\n",
            Day10.part2(File(ClassLoader.getSystemResource("day10/testinput.txt").file))
        )
    }

}
