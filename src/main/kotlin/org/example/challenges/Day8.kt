package org.example.challenges

import org.example.TwoPartChallenge
import org.example.util.parseAllLinesFromFile
import java.io.File

object Day8 : TwoPartChallenge<Int> {

    override fun part1(file: File): Int {
        var visibleCount = 0
        val heightGrid = getGrid(file)
        val visibilityGrid = Array(heightGrid.size) { BooleanArray(heightGrid[0].size) }
        for (i in heightGrid.indices) {
            heightGrid[i].forEachIndexed { j, currentTreeHeight ->
                if (i == 0 || i == heightGrid.size - 1 || j == 0 || j == heightGrid[i].size - 1) {
                    visibilityGrid[i][j] = true
                    ++visibleCount
                } else {
                    if(heightGrid[i].sliceArray(IntRange(0, j-1)).all { value -> value < currentTreeHeight }) {
                        visibilityGrid[i][j] = true
                        ++visibleCount
                    } else if (heightGrid[i].sliceArray(IntRange(j+1, heightGrid[i].size-1)).all { value -> value < currentTreeHeight }) {
                        visibilityGrid[i][j] = true
                        ++visibleCount
                    } else {
                        for (gridIndices in 0 until i) {
                            if (heightGrid[gridIndices][j] >= heightGrid[i][j]) {
                                break
                            }
                            if (gridIndices == i-1) {
                                visibilityGrid[i][j] = true
                                ++visibleCount
                            }
                        }
                        if (!visibilityGrid[i][j]) {
                            for (gridIndices in i+1 until heightGrid.size) {
                                if (heightGrid[gridIndices][j] >= heightGrid[i][j]) {
                                    break
                                }
                                if (gridIndices == heightGrid.size-1) {
                                    visibilityGrid[i][j] = true
                                    ++visibleCount
                                }
                            }
                        }
                    }
                }
            }
        }
        return visibleCount
    }

    override fun part2(file: File): Int {
        TODO("Not yet implemented")
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

}