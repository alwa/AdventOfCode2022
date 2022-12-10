package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day10 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        var sum = 0
        val cycles = mutableListOf(1)
        val indicesToCount = setOf(20, 60, 100, 140, 180, 220)
        file.forEachLine { line ->
            if (line.startsWith("noop")) {
                cycles.add(0)
            } else {
                val parts = line.split(" ")
                check(parts[0] == "addx")
                cycles.add(0)
                cycles.add(parts[1].toInt())
            }
        }
        cycles.forEachIndexed { index, i ->
            if ((index+1) in indicesToCount) {
                sum += (index+1) * cycles.subList(0, index+1).sum()
            }
        }
        return sum
    }

    override fun part2(file: File): Int {
        TODO("Not yet implemented")
    }

}