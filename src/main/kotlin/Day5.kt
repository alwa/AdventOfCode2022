import java.io.File
import java.util.Stack

object Day5 {

    fun part1(filename: String): String {
        val numberOfColumns = getNumberOfStacks(filename)
        val stacks = getInitializedStacks(numberOfColumns, filename)
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.startsWith("move")) {
                stacks.processCrateMover9000(line = it)
            }
        }
        val stringBuilder: StringBuilder = StringBuilder()
        repeat(stacks.size) { i -> stringBuilder.append(stacks[i].pop()) }
        return stringBuilder.toString()
    }

    fun part2(filename: String): String {
        val numberOfColumns = getNumberOfStacks(filename)
        val stacks = getInitializedStacks(numberOfColumns, filename)
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.startsWith("move")) {
                stacks.processCrateMover9001(line = it)
            }
        }
        val stringBuilder: StringBuilder = StringBuilder()
        repeat(stacks.size) { i -> stringBuilder.append(stacks[i].pop()) }
        return stringBuilder.toString()
    }

    private fun List<Stack<Char>>.processCrateMover9000(line: String) {
        val parts = line.split(" ")
        val numberToMove = parts[1].toInt()
        val fromStack = parts[3].toInt()
        val toStack = parts[5].toInt()
        move(numberToMove, toStack, fromStack)
    }

    private fun List<Stack<Char>>.processCrateMover9001(line: String) {
        val parts = line.split(" ")
        val numberToMove = parts[1].toInt()
        val fromStack = parts[3].toInt()
        val toStack = parts[5].toInt()
        multiMove(numberToMove, fromStack, toStack)
    }

    private fun List<Stack<Char>>.move(
        numberToMove: Int,
        toStack: Int,
        fromStack: Int
    ) {
        repeat(numberToMove) {
            this[toStack - 1].push(this[fromStack - 1].pop())
        }
    }

    private fun List<Stack<Char>>.multiMove(
        numberToMove: Int,
        fromStack: Int,
        toStack: Int
    ) {
        val tempStack = Stack<Char>()
        repeat(numberToMove) {
            tempStack.push(this[fromStack - 1].pop())
        }
        repeat(numberToMove) {
            this[toStack - 1].push(tempStack.pop())
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
