import java.io.File
import java.util.*

object Day5 {

    fun part1(filename: String): String {
        val numberOfColumns = getNumberOfStacks(filename)
        val stacks = getInitializedStacks(numberOfColumns, filename)
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.isMoveAction()) {
                val lineParts = it.split(" ")
                stacks.process(
                    numberOfCrates = lineParts[1].toInt(),
                    fromStack = lineParts[3].toInt(),
                    toStack = lineParts[5].toInt(),
                    strategy = CrateMover9000Strategy()
                )
            }
        }
        return getResult(stacks)
    }

    fun part2(filename: String): String {
        val numberOfColumns = getNumberOfStacks(filename)
        val stacks = getInitializedStacks(numberOfColumns, filename)
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.isMoveAction()) {
                val lineParts = it.split(" ")
                stacks.process(
                    numberOfCrates = lineParts[1].toInt(),
                    fromStack = lineParts[3].toInt(),
                    toStack = lineParts[5].toInt(),
                    strategy = CrateMover9001Strategy()
                )
            }
        }
        return getResult(stacks)
    }

    private fun getResult(stacks: List<Stack<Char>>): String {
        val stringBuilder: StringBuilder = StringBuilder()
        repeat(stacks.size) { i -> stringBuilder.append(stacks[i].pop()) }
        return stringBuilder.toString()
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
        val allFilteredLines: List<String> = getAllStackDefinitionLinesInReverseOrder(filename)
        for (it in allFilteredLines) {
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
        return stacks.toList()
    }

    private fun getAllStackDefinitionLinesInReverseOrder(filename: String): List<String> {
        val allLines: MutableList<String> = mutableListOf()
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (!it.isMoveAction() && it.trim().isNotEmpty()) {
                allLines.add(it)
            }
        }
        allLines.reverse()
        return allLines.toList()
    }

    private fun String.isMoveAction() = this.startsWith("move")

    private fun getNumberOfStacks(filename: String): Int {
        var numberOfColumns = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            var isLastCharDigit = false
            if (!it.isMoveAction()) {
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

    private class CrateMover9000Strategy : MoveStrategy {
        override fun move(stacks: List<Stack<Char>>, numberOfCrates: Int, fromStack: Int, toStack: Int) {
            stacks.move(numberToMove = numberOfCrates, toIndex = toStack - 1, fromIndex = fromStack - 1)
        }

    }

    private class CrateMover9001Strategy : MoveStrategy {
        override fun move(stacks: List<Stack<Char>>, numberOfCrates: Int, fromStack: Int, toStack: Int) {
            if (numberOfCrates == 1) {
                stacks.move(numberToMove = numberOfCrates, toIndex = toStack - 1, fromIndex = fromStack - 1)
            } else {
                stacks.multiMove(numToMove = numberOfCrates, fromIndex = fromStack - 1, toIndex = toStack - 1)
            }
        }

    }

    interface MoveStrategy {

        fun move(stacks: List<Stack<Char>>, numberOfCrates: Int, fromStack: Int, toStack: Int)

    }

}

private fun List<Stack<Char>>.process(numberOfCrates: Int, fromStack: Int, toStack: Int, strategy: Day5.MoveStrategy) {
    strategy.move(stacks = this@process, numberOfCrates = numberOfCrates, fromStack = fromStack, toStack = toStack)
}
