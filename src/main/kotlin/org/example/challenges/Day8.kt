package org.example.challenges

import org.example.TwoPartChallenge
import org.example.util.parseAllLinesFromFile
import java.io.File

object Day8 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        var visibleCount = 0
        val grid = getGrid(file)
        val visibilityGrid = Array(grid.size) { BooleanArray(grid[0].size) }

        for (i in grid.indices) {
            grid[i].forEachIndexed { j, currentTreeHeight ->
                if (i == 0 || i == grid.size - 1) {
                    visibilityGrid[i][j] = true
                    ++visibleCount
                } else {
                    if (j == 0 || j == grid[i].size - 1 || grid[i][j - 1] < currentTreeHeight || grid[i][j + 1] < currentTreeHeight) {
                        visibilityGrid[i][j] = true
                        ++visibleCount
                    }
                }
            }
        }
        return visibleCount
    }

    private fun getGrid(file: File): Array<IntArray> {
        val allLines = file.parseAllLinesFromFile()
        val grid = Array(allLines.size) { IntArray(allLines[0].length) }
        allLines.forEachIndexed { index, line ->
            line.toCharArray().forEachIndexed { innerIndex, char ->
                grid[index][innerIndex] = char.toString().toInt()
            }
        }
        return grid
    }

    override fun part2(file: File): Int {
        TODO("Not yet implemented")
    }

}