package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day10 : TwoPartChallenge<Int, String> {

    private const val CRT_WIDTH = 40

    override fun part1(file: File): Int {
        var sum = 0
        val indicesToCount = setOf(20, 60, 100, 140, 180, 220)
        val cycles = getCycles(file)
        cycles.forEachIndexed { index, i ->
            if ((index + 1) in indicesToCount) {
                sum += (index + 1) * cycles.subList(0, index + 1).sum()
            }
        }
        return sum
    }

    override fun part2(file: File): String {
        var sum = 0
        val cycles = getCycles(file)
        val resultBuilder: StringBuilder = StringBuilder()
        var spritePosition: SpritePosition
        var crt = 0
        cycles.subList(0, cycles.size - 1).forEachIndexed { index, i ->
            if (crt % CRT_WIDTH == 0) {
                crt = 0
            }
            val x = cycles.subList(0, index + 1).sum()
            spritePosition = SpritePosition(x - 1, x, x + 1)
            if (crt >= spritePosition.left && crt <= spritePosition.right) {
                resultBuilder.append("#")
            } else {
                resultBuilder.append(".")
            }
            sum += (index + 1) * x
            if ((index + 1) % CRT_WIDTH == 0) {
                spritePosition = SpritePosition(0, 1, 2)
                resultBuilder.append("\n")
            } else {
                spritePosition = SpritePosition(spritePosition.middle, spritePosition.right, spritePosition.right + 1)
            }
            crt++
        }
        return resultBuilder.toString()
    }

    private fun getCycles(file: File): List<Int> {
        val cycles = mutableListOf(1)
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
        return cycles
    }

    private data class SpritePosition(val left: Int, val middle: Int, val right: Int)

}