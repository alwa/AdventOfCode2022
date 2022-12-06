package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day1 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        var maxSumCalories = 0
        var tempSumCalories = 0
        file.forEachLine {
            if (it.isNotEmpty()) {
                tempSumCalories += it.toInt()
            } else {
                if (tempSumCalories > maxSumCalories) {
                    maxSumCalories = tempSumCalories
                }
                tempSumCalories = 0
            }
        }
        if (tempSumCalories > maxSumCalories) {
            maxSumCalories = tempSumCalories
        }
        return maxSumCalories
    }

    override fun part2(file: File): Int {
        val topThreeSumCalories = mutableListOf(0, 0, 0)
        var tempSumCalories = 0
        file.forEachLine {
            if (it.isNotEmpty()) {
                tempSumCalories += it.toInt()
            } else {
                topThreeSumCalories.replaceIfBiggerThanSmallestInList(tempSumCalories)
                tempSumCalories = 0
            }
        }
        return topThreeSumCalories.replaceIfBiggerThanSmallestInList(tempSumCalories).sum()
    }

    private fun MutableList<Int>.replaceIfBiggerThanSmallestInList(value: Int): MutableList<Int> {
        val minValueInList = this.min()
        if (value > minValueInList) {
            this[this.indexOf(minValueInList)] = value
        }
        return this
    }

}