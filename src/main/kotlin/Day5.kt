import java.io.File
import java.util.Stack

object Day5 {

    fun part1(filename: String): String {
        val numberOfColumns = getNumberOfStacks(filename)
        val stacks = getInitializedStacks(numberOfColumns, filename)
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.startsWith("move")) {
                val lineParts = it.split(" ")
                stacks.processUsingCrateMover9000(
                    numberOfCrates = lineParts[1].toInt(),
                    fromStack = lineParts[3].toInt(),
                    toStack = lineParts[5].toInt()
                )
            }
        }
        return getResult(stacks)
    }

    fun part2(filename: String): String {
        val numberOfColumns = getNumberOfStacks(filename)
        val stacks = getInitializedStacks(numberOfColumns, filename)
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.startsWith("move")) {
                val lineParts = it.split(" ")
                stacks.processUsingCrateMover9001(
                    numberOfCrates = lineParts[1].toInt(),
                    fromStack = lineParts[3].toInt(),
                    toStack = lineParts[5].toInt())
            }
        }
        return getResult(stacks)
    }

    private fun getResult(stacks: List<Stack<Char>>): String {
        val stringBuilder: StringBuilder = StringBuilder()
        repeat(stacks.size) { i -> stringBuilder.append(stacks[i].pop()) }
        return stringBuilder.toString()
    }

    private fun List<Stack<Char>>.processUsingCrateMover9000(numberOfCrates: Int, fromStack: Int, toStack: Int) {
        move(numberOfCrates, toStack - 1, fromStack - 1)
    }

    private fun List<Stack<Char>>.processUsingCrateMover9001(numberOfCrates: Int, fromStack: Int, toStack: Int) {
        if (numberOfCrates == 1) {
            move(numberOfCrates, toStack - 1, fromStack - 1)
        } else {
            multiMove(numberOfCrates, fromStack - 1, toStack - 1)
        }
    }

    private fun List<Stack<Char>>.move(
        numberToMove: Int,
        toIndex: Int,
        fromIndex: Int
    ) {
        repeat(numberToMove) {
            this[toIndex].push(this[fromIndex].pop())
        }
    }

    private fun List<Stack<Char>>.multiMove(
        numToMove: Int,
        fromIndex: Int,
        toIndex: Int
    ) {
        val tempStack = Stack<Char>()
        repeat(numToMove) {
            tempStack.push(this[fromIndex].pop())
        }
        repeat(numToMove) {
            this[toIndex].push(tempStack.pop())
        }
    }

    private fun getInitializedStacks(numberOfColumns: Int, filename: String): List<Stack<Char>> {
        val stacks: MutableList<Stack<Char>> = mutableListOf()
        repeat(numberOfColumns) { stacks.add(Stack<Char>()) }
        val allLines: MutableList<String> = mutableListOf()
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (!it.startsWith("move") && it.trim().isNotEmpty()) {
                allLines.add(it)
            }
        }
        allLines.reverse()
        for (it in allLines) {
            if (!it.startsWith("move") && it.trim().isNotEmpty()) {
                stacks.forEachIndexed { index, _ ->
                    if (index == 0) {
                        if (it[1].isLetter()) {
                            stacks[0].push(it[1])
                        }
                    } else {
                        if (it[1 + index * 4].isLetter()) {
                            stacks[index].push(it[1 + index * 4])
                        }
                    }
                }
            }
        }
        return stacks.toList()
    }

    private fun getNumberOfStacks(filename: String): Int {
        var numberOfColumns = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            var isLastCharDigit = false
            if (!it.startsWith("move")) {
                for (char in it.toCharArray()) {
                    if (char.isDigit() && !isLastCharDigit) {
                        isLastCharDigit = true
                        numberOfColumns++
                    } else {
                        isLastCharDigit = false
                    }
                }
            }
        }
        return numberOfColumns
    }

}
