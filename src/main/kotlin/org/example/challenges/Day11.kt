package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File
import java.util.*

object Day11 : TwoPartChallenge<Int, Int> {

    override fun part1(file: File): Int {
        val monkeys = mutableListOf<Monkey>()
        var startingItems: Stack<Int> = Stack()
        var operation: (Int) -> Int = { it }
        var test = 0
        var ifTrueMonkey = 0
        var ifFalseMonkey: Int
        file.forEachLine { line ->
            if (line.trimStart().startsWith("Starting items:")) {
                val startingItemsRaw = line.substring(line.indexOf(":") + 1).trimStart().split(",")
                for (startingItem in startingItemsRaw) {
                    startingItems.push(startingItem.trimStart().trimEnd().toInt())
                }
            }
            if (line.trimStart().startsWith("Operation:")) {
                val operationRaw = line.substring(line.indexOf("=") + 1).trimStart()
                if (operationRaw.contains("*")) {
                    val secondOperator = operationRaw.substring(operationRaw.indexOf("*") + 1).trimStart().trimEnd()
                    operation = if (secondOperator == "old") {
                        { i: Int -> i * i }
                    } else {
                        { i: Int -> i * secondOperator.toInt() }
                    }
                } else if (operationRaw.contains("+")) {
                    val secondOperator = operationRaw.substring(operationRaw.indexOf("+") + 1).trimStart().trimEnd()
                    operation = if (secondOperator == "old") {
                        { i: Int -> i + i }
                    } else {
                        { i: Int -> i + secondOperator.toInt() }
                    }
                } else {
                    throw IllegalStateException()
                }
            }
            if (line.trimStart().startsWith("Test: divisible by ")) {
                test = line.substring(line.indexOf("by") + 3).trimStart().trimEnd().toInt()
            }
            if (line.trimStart().startsWith("If true: throw to monkey ")) {
                ifTrueMonkey = line.substring(line.length - 1).toInt()
            }
            if (line.trimStart().startsWith("If false: throw to monkey ")) {
                ifFalseMonkey = line.substring(line.length - 1).toInt()
                monkeys.add(
                    Monkey(
                        test = test,
                        ifTrue = ifTrueMonkey,
                        ifFalse = ifFalseMonkey,
                        operation = operation,
                        startingItems = startingItems
                    )
                )
                startingItems = Stack<Int>()
            }
        }
        for (i: Int in 0 until (20)) {
            monkeys.forEachIndexed { index, monkey ->
                while (monkey.startingItems.isNotEmpty()) {
                    val inspectedItem: Int = monkey.startingItems.pop()
                    monkey.numberOfInspections++
                    val worryAfterInspection: Int = monkey.operation.invoke(inspectedItem)
                    val worryAfterBored: Int = worryAfterInspection / 3
                    if (worryAfterBored % monkey.test == 0) {
                        monkeys[monkey.ifTrue].startingItems.add(0, worryAfterBored)
                    } else {
                        monkeys[monkey.ifFalse].startingItems.add(0, worryAfterBored)
                    }
                }
            }
        }
        val sortedInspections: SortedSet<Int> = sortedSetOf()
        monkeys.forEach { monkey -> sortedInspections.add(monkey.numberOfInspections) }
        return sortedInspections.toList().subList(sortedInspections.size - 2, sortedInspections.size).product()
    }

    override fun part2(file: File): Int {
        TODO("Not yet implemented")
    }

    private fun Iterable<Int>.product(): Int {
        var product = 1
        for (element in this) {
            product *= element
        }
        return product
    }

    private data class Monkey(
        val test: Int, val ifTrue: Int, val ifFalse: Int, val operation: (Int) -> Int,
        val startingItems: Stack<Int>, var numberOfInspections: Int = 0
    )

}