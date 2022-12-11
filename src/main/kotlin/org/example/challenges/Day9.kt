package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import kotlin.math.abs

object Day9 : TwoPartChallenge<Int, Int> {

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
                head = getNewHead(head, parts)
                tail = followHead(head, tail)
                positionsHeadHasVisited.add(head)
                positionsTailHasVisited.add(tail)
            }
        }
        return positionsTailHasVisited.size
    }

    private fun followHead(
        head: Coordinate,
        tail: Coordinate
    ): Coordinate {
        var tail1 = tail
        if (head.x == tail1.x && abs(head.y - tail1.y) > 1) {
            // Y move
            val newY = tail1.y + (head.y - tail1.y) / abs(head.y - tail1.y)
            tail1 = Coordinate(x = tail1.x, y = newY)
        } else if (head.y == tail1.y && abs(head.x - tail1.x) > 1) {
            // X move
            val newX = tail1.x + (head.x - tail1.x) / abs(head.x - tail1.x)
            tail1 = Coordinate(x = newX, y = tail1.y)
        } else if (head.x != tail1.x && head.y != tail1.y && abs(head.y - tail1.y) > 1 ||
            head.x != tail1.x && head.y != tail1.y && abs(head.x - tail1.x) > 1
        ) {
            // Diagonal move
            val newX = tail1.x + (head.x - tail1.x) / abs(head.x - tail1.x)
            val newY = tail1.y + (head.y - tail1.y) / abs(head.y - tail1.y)
            tail1 = Coordinate(x = newX, y = newY)
        }
        return tail1
    }

    override fun part2(file: File): Int {
        var head = Coordinate(x = 0, y = 0)
        val tail = mutableListOf(
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0),
            Coordinate(x = 0, y = 0)
        )
        val positionsTailHasVisited = mutableSetOf<Coordinate>()
        val positionsHeadHasVisited = mutableSetOf<Coordinate>()
        file.forEachLine { line ->
            val parts = line.split(" ")
            val steps = parts[1].toInt()
            for (i in 0 until steps) {
                positionsHeadHasVisited.add(head)
                positionsTailHasVisited.add(tail[8])
                head = getNewHead(head, parts)
                tail[0] = followHead(head, tail[0])
                for (j in 0  until 8) {
                    tail[j+1] = followHead(head = tail[j], tail = tail[j+1])
                }
                positionsHeadHasVisited.add(head)
                positionsTailHasVisited.add(tail[8])
            }
        }
        return positionsTailHasVisited.size
    }

    private fun getNewHead(
        head: Coordinate,
        parts: List<String>
    ): Coordinate {
        var head1 = head
        head1 = if (parts[0] == "R") {
            Coordinate(head1.x + 1, head1.y)
        } else if (parts[0] == "L") {
            Coordinate(head1.x - 1, head1.y)
        } else if (parts[0] == "U") {
            Coordinate(head1.x, head1.y + 1)
        } else if (parts[0] == "D") {
            Coordinate(head1.x, head1.y - 1)
        } else {
            throw IllegalStateException()
        }
        return head1
    }

    private data class Coordinate(val x: Int, val y: Int)

}