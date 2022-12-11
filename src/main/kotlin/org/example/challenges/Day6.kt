package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day6 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        var result = -1
        file.forEachLine { line ->
            result = getStartOfMessageMarker(input = line, numberOfCharacters = 4)
        }
        return result
    }

    override fun part2(file: File): Int {
        var result = -1
        file.forEachLine { line ->
            result = getStartOfMessageMarker(input = line, numberOfCharacters = 14)
        }
        return result
    }

    private fun getStartOfMessageMarker(input: String, numberOfCharacters: Int): Int {
        var result = -1
        for (i in 0..input.length) {
            val set: MutableSet<Char> = mutableSetOf()
            repeat(numberOfCharacters) { count -> set.add(input[i + count]) }
            if (set.size == numberOfCharacters) {
                result = i + numberOfCharacters
                break
            }
        }
        return result
    }

}
