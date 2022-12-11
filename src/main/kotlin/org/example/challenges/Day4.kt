package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day4 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var count = 0
        file.forEachLine {
            val rawAssignments = it.split(",")
            val firstPair = rawAssignments[0].toRangePair()
            val secondPair = rawAssignments[1].toRangePair()
            if ((firstPair.first >= secondPair.first && firstPair.second <= secondPair.second) ||
                (secondPair.first >= firstPair.first && secondPair.second <= firstPair.second)
            ) {
                count++
            }
        }
        return count
    }

    override fun part2(file: File): Int {
        var count = 0
        file.forEachLine {
            val rawAssignments = it.split(",")
            val firstPair = rawAssignments[0].toRangePair()
            val secondPair = rawAssignments[1].toRangePair()
            for (int in secondPair.toIntRange()) {
                if (firstPair.toIntRange().contains(int)) {
                    count++
                    break
                }
            }
        }
        return count
    }

    private fun String.toRangePair(): Pair<Int, Int> {
        val startAndEnd = split("-")
        return Pair(startAndEnd[0].toInt(), startAndEnd[1].toInt())
    }

    private fun Pair<Int, Int>.toIntRange(): IntRange = first.rangeTo(second)

}