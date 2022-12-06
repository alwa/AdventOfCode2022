package org.example.challenges

import org.example.Challenge
import java.io.File

object Day3 : Challenge<Int>{

    override fun part1(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val part1 = it.substring(0, it.length / 2)
            val part2 = it.substring(it.length / 2)
            for (character in part1) {
                if (part2.contains(character)) {
                    val priority = getPriority(character)
                    score += priority
                    break
                }
            }
        }
        return score
    }

    override fun part2(filename: String): Int {
        var lineCounter = 0
        val lines = mutableListOf("", "", "")
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            lines[lineCounter] = it
            lineCounter++
            if (lineCounter == 3) {
                lineCounter = 0
                loop@ for (character in lines[0]) {
                    for (innerCharacter in lines[1]) {
                        for (innerInnerCharacter in lines[2]) {
                            if (character == innerCharacter && innerCharacter == innerInnerCharacter) {
                                score += getPriority(character)
                                break@loop
                            }
                        }
                    }
                }
            }
        }
        return score
    }

    private fun getPriority(char: Char): Int {
        return if (char.isLowerCase()) {
            char.code - 96
        } else {
            char.code - 38
        }
    }

}