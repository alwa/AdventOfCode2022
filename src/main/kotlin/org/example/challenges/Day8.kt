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
                    if (heightGrid[i].sliceArray(IntRange(0, j - 1)).all { value -> value < currentTreeHeight }) {
                        visibilityGrid[i][j] = true
                        ++visibleCount
                    } else if (heightGrid[i].sliceArray(IntRange(j + 1, heightGrid[i].size - 1))
                            .all { value -> value < currentTreeHeight }
                    ) {
                        visibilityGrid[i][j] = true
                        ++visibleCount
                    } else {
                        for (gridIndices in 0 until i) {
                            if (heightGrid[gridIndices][j] >= heightGrid[i][j]) {
                                break
                            }
                            if (gridIndices == i - 1) {
                                visibilityGrid[i][j] = true
                                ++visibleCount
                            }
                        }
                        if (!visibilityGrid[i][j]) {
                            for (gridIndices in i + 1 until heightGrid.size) {
                                if (heightGrid[gridIndices][j] >= heightGrid[i][j]) {
                                    break
                                }
                                if (gridIndices == heightGrid.size - 1) {
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
        val heightGrid = getGrid(file)
        val scenicScoreGrid = Array(heightGrid.size) { IntArray(heightGrid[0].size) }
        val visibilityGrid = Array(heightGrid.size) { BooleanArray(heightGrid[0].size) }
        for (i in heightGrid.indices) {
            heightGrid[i].forEachIndexed { j, currentTreeHeight ->
                val treesLeft = heightGrid[i].sliceArray(IntRange(0, j - 1))
                treesLeft.reverse()
                var treesLeftBelowTree = 0
                for (i in treesLeft) {
                    if (i < currentTreeHeight) {
                        treesLeftBelowTree++
                    } else {
                        treesLeftBelowTree++
                        break
                    }
                }
                if (treesLeftBelowTree > 0) {
                    visibilityGrid[i][j] = true
                }
                val treesRight = heightGrid[i].sliceArray(IntRange(j + 1, heightGrid[i].size - 1))
                var treesRightBelowTree = 0
                for (i in treesRight) {
                    if (i < currentTreeHeight) {
                        treesRightBelowTree++
                    } else {
                        treesRightBelowTree++
                        break
                    }
                }
                if (treesRightBelowTree > 0) {
                    visibilityGrid[i][j] = true
                }
                var treesUpBelowTree = 0
                if (i > 0) {
                    for (gridIndices in i-1 downTo  0) {
                        if (heightGrid[gridIndices][j] < currentTreeHeight) {
                            treesUpBelowTree++
                        } else {
                            treesUpBelowTree++
                            break
                        }
                    }
                }
                if (treesUpBelowTree > 0) {
                    visibilityGrid[i][j] = true
                }
                var treesDownBelowTree = 0
                for (gridIndices in i + 1 until heightGrid.indices.count()) {
                    if (heightGrid[gridIndices][j] < currentTreeHeight) {
                        treesDownBelowTree++
                    } else {
                        treesDownBelowTree++
                        break
                    }
                }
                if (treesDownBelowTree > 0) {
                    visibilityGrid[i][j] = true
                }
                scenicScoreGrid[i][j] = treesUpBelowTree * treesDownBelowTree * treesRightBelowTree * treesLeftBelowTree
            }
        }
        var maxScenicScore = 0
        for (i in scenicScoreGrid.indices) {
            scenicScoreGrid[i].forEachIndexed { j, score ->
                if (score > maxScenicScore) {
                    maxScenicScore = score
                }
            }
        }
        return maxScenicScore
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