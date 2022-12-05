import java.io.File
import java.util.*
import java.util.regex.Pattern

object Day5 {

    fun part1(filename: String): String {
        val lines = parseAllLines(filename)
        val stacks = getParsedStacks(lines)
        lines.filter { line -> line.isMoveAction() }.forEach { line ->
            val moveData = MoveParser().parse(line)
            stacks.process(moveData = moveData, strategy = CrateMover9000MoveStrategy())
        }
        return getResult(stacks)
    }

    fun part2(filename: String): String {
        val lines = parseAllLines(filename)
        val stacks = getParsedStacks(lines)
        lines.filter { line -> line.isMoveAction() }.forEach { line ->
            val moveData = MoveParser().parse(line)
            stacks.process(moveData = moveData, strategy = CrateMover9001MoveStrategy())
        }
        return getResult(stacks)
    }

    private fun getResult(stacks: List<Stack<Char>>): String {
        val stringBuilder: StringBuilder = StringBuilder()
        repeat(stacks.size) { i -> stringBuilder.append(stacks[i].pop()) }
        return stringBuilder.toString()
    }

    private fun List<Stack<Char>>.move(numberToMove: Int, toIndex: Int, fromIndex: Int) {
        repeat(numberToMove) {
            this[toIndex].push(this[fromIndex].pop())
        }
    }

    private fun List<Stack<Char>>.multiMove(numToMove: Int, fromIndex: Int, toIndex: Int) {
        val tempStack = Stack<Char>()
        repeat(numToMove) {
            tempStack.push(this[fromIndex].pop())
        }
        repeat(numToMove) {
            this[toIndex].push(tempStack.pop())
        }
    }

    private fun getParsedStacks(lines: List<String>): List<Stack<Char>> {
        val numberOfStacks = getParsedNumberOfStacks(lines)
        val stacks: MutableList<Stack<Char>> = mutableListOf()
        repeat(numberOfStacks) { stacks.add(Stack<Char>()) }
        val stackDefinitionLines: List<String> = lines.filter { line -> line.isStackDefinitionLine() }.reversed()
        for (line in stackDefinitionLines) {
            stacks.forEachIndexed { index, _ ->
                if (line[1 + index * 4].isLetter()) {
                    stacks[index].push(line[1 + index * 4])
                }
            }
        }
        return stacks.toList()
    }

    private fun parseAllLines(filename: String): List<String> {
        val result: MutableList<String> = mutableListOf()
        File(ClassLoader.getSystemResource(filename).file).forEachLine { line ->
            result.add(line)
        }
        return result
    }

    private fun String.isStackDefinitionLine() = !this.isMoveAction() && this.trim().isNotEmpty()

    private fun String.isMoveAction() = this.startsWith("move")

    private fun getParsedNumberOfStacks(lines: List<String>): Int {
        var count = 0
        lines.filterNot { line -> line.isMoveAction() }.forEach { line ->
            count += countStacks(line)
        }
        return count
    }

    private fun countStacks(line: String): Int {
        var count = 0
        val matcher = Pattern.compile("[\\d+]").matcher(line)
        while (matcher.find()) {
            count++
        }
        return count
    }

    private class CrateMover9000MoveStrategy : MoveStrategy<Char> {
        override fun move(stacks: List<Stack<Char>>, numberOfCrates: Int, fromStack: Int, toStack: Int) {
            stacks.move(numberToMove = numberOfCrates, toIndex = toStack - 1, fromIndex = fromStack - 1)
        }

    }

    private class CrateMover9001MoveStrategy : MoveStrategy<Char> {
        override fun move(stacks: List<Stack<Char>>, numberOfCrates: Int, fromStack: Int, toStack: Int) {
            if (numberOfCrates == 1) {
                stacks.move(numberToMove = numberOfCrates, toIndex = toStack - 1, fromIndex = fromStack - 1)
            } else {
                stacks.multiMove(numToMove = numberOfCrates, fromIndex = fromStack - 1, toIndex = toStack - 1)
            }
        }

    }

    interface MoveStrategy<T> {

        fun move(stacks: List<Stack<T>>, numberOfCrates: Int, fromStack: Int, toStack: Int)

    }

}

private class MoveParser {
    fun parse(line: String): MoveData {
        val lineParts = line.split(" ")
        return MoveData(
            numberOfCrates = lineParts[1].toInt(), fromStack = lineParts[3].toInt(),
            toStack = lineParts[5].toInt()
        )
    }

}

private data class MoveData(val numberOfCrates: Int, val fromStack: Int, val toStack: Int)

private fun List<Stack<Char>>.process(
    moveData: MoveData,
    strategy: Day5.MoveStrategy<Char>
) {
    strategy.move(
        stacks = this@process,
        numberOfCrates = moveData.numberOfCrates,
        fromStack = moveData.fromStack,
        toStack = moveData.toStack
    )
}
