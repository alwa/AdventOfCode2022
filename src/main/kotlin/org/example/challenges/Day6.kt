package org.example.challenges

import java.io.File

object Day6 {

    fun part1(filename: String): Int {
        var result = -1
        File(ClassLoader.getSystemResource(filename).file).forEachLine { line ->
            result = getStartOfMessageMarker(input = line, numberOfCharacters = 4)
        }
        return result
    }

    fun part2(filename: String): Int {
        var result = -1
        File(ClassLoader.getSystemResource(filename).file).forEachLine { line ->
            result = getStartOfMessageMarker(input = line, numberOfCharacters = 14)
        }
        return result
    }

    private fun getStartOfMessageMarker(input: String, numberOfCharacters: Int): Int {
        var result = 0
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
