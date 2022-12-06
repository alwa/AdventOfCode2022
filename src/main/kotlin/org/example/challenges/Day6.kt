package org.example.challenges

import java.io.File

object Day6 {

    fun part1(filename: String): Int {
        var result = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine { line ->
            for (i in 0..line.length) {
                val first = line[i]
                val second = line[i + 1]
                val third = line[i + 2]
                val fourth = line[i + 3]
                if (setOf(first, second, third, fourth).size == 4) {
                    result = i + 4
                    break
                }
            }
        }
        return result
    }

}
