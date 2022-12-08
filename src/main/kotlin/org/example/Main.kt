package org.example

import org.example.challenges.*
import java.io.File

fun main() {
    val twoPartChallenges: List<TwoPartChallenge<*>> = listOf(Day1, Day2, Day3, Day4, Day5, Day6, Day7)
    for (i in 1..twoPartChallenges.size) {
        println("=Day $i=")
        val file = File(ClassLoader.getSystemResource("day${i}/input.txt").file)
        println("Part 1: ${twoPartChallenges[i - 1].part1(file)}")
        println("Part 2: ${twoPartChallenges[i - 1].part2(file)}")
    }
}
