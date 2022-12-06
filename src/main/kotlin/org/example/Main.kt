package org.example

import org.example.challenges.*

fun main() {
    val challenges: List<Challenge<*>> = listOf(Day1, Day2, Day3, Day4, Day5, Day6)
    for (i in 1..6) {
        println("=Day $i=")
        val filename = "day${i}/input.txt"
        println("Part 1: ${challenges[i - 1].part1(filename)}")
        println("Part 2: ${challenges[i - 1].part2(filename)}")
    }
}
