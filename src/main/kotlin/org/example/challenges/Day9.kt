package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.abs

object Day9 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        var head = Coordinate(x = 0, y = 0)
        var tail = Coordinate(x = 0, y = 0)
        val positionsTailHasVisited = mutableSetOf<Coordinate>()
        val positionsHeadHasVisited = mutableSetOf<Coordinate>()
        file.forEachLine { line ->
            val parts = line.split(" ")
            val steps = parts[1].toInt()
            for (i in 0 until steps) {
                positionsHeadHasVisited.add(head)
                positionsTailHasVisited.add(tail)
                head = if (parts[0] == "R") {
                    Coordinate(head.x + 1, head.y)
                } else if (parts[0] == "L") {
                    Coordinate(head.x - 1, head.y)
                } else if (parts[0] == "U") {
                    Coordinate(head.x, head.y + 1)
                } else {
                    Coordinate(head.x, head.y - 1)
                }
                if (head.x == tail.x && abs(head.y - tail.y) > 1) {
                    // Y move
                    val newY = tail.y + (head.y - tail.y) / abs(head.y - tail.y)
                    tail = Coordinate(x = tail.x, y = newY)
                } else if (head.y == tail.y && abs(head.x - tail.x) > 1) {
                    // X move
                    val newX = tail.x + (head.x - tail.x) / abs(head.x - tail.x)
                    tail = Coordinate(x = newX, y = tail.y)
                } else if (head.x != tail.x && head.y != tail.y && abs(head.y - tail.y) > 1 ||
                    head.x != tail.x && head.y != tail.y && abs(head.x - tail.x) > 1 ) {
                    // Diagonal move
                    val newX = tail.x + (head.x - tail.x) / abs(head.x - tail.x)
                    val newY = tail.y + (head.y - tail.y) / abs(head.y - tail.y)
                    tail = Coordinate(x = newX, y = newY)
                }
            }
        }
        return positionsTailHasVisited.size
    }

    override fun part2(file: File): Int {
        TODO("Not yet implemented")
    }

    private data class Coordinate(val x: Int, val y: Int)

}