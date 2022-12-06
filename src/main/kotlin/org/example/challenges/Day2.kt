package org.example.challenges

import org.example.TwoPartChallenge
import java.io.File

object Day2 : TwoPartChallenge<Int> {

    override fun part1(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val scores = it.split(" ")
            val hand = getHand(scores[1].toCharArray()[0])
            val opponentHand = getHand(scores[0].toCharArray()[0])
            score += hand.score
            score += hand.getResultAgainst(opponentHand).score
        }
        return score
    }

    override fun part2(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val scores = it.split(" ")
            val opponentHand = getHand(scores[0].toCharArray()[0])
            val result = getResult(scores[1].toCharArray()[0])
            when (result.score) {
                Hand.ROCK.getResultAgainst(opponentHand).score -> score += Hand.ROCK.score
                Hand.PAPER.getResultAgainst(opponentHand).score -> score += Hand.PAPER.score
                Hand.SCISSOR.getResultAgainst(opponentHand).score -> score += Hand.SCISSOR.score
            }
            score += result.score
        }
        return score
    }

    private fun getHand(char: Char): Hand {
        return when (char) {
            'A', 'X' -> Hand.ROCK
            'B', 'Y' -> Hand.PAPER
            'C', 'Z' -> Hand.SCISSOR
            else -> throw IllegalStateException()
        }
    }

    private fun getResult(char: Char): Result {
        return when (char) {
            'X' -> Result.LOSE
            'Y' -> Result.DRAW
            'Z' -> Result.WIN
            else -> throw IllegalStateException()
        }
    }

    private enum class Hand(val score: Int) {
        ROCK(1), PAPER(2), SCISSOR(3);

        fun getResultAgainst(opponentHand: Hand): Result {
            return if (this == opponentHand) {
                Result.DRAW
            } else {
                if (this == ROCK) {
                    if (opponentHand == SCISSOR) Result.WIN else Result.LOSE
                } else if (this == PAPER) {
                    if (opponentHand == ROCK) Result.WIN else Result.LOSE
                } else if (this == SCISSOR) {
                    if (opponentHand == ROCK) Result.LOSE else Result.WIN
                } else throw IllegalStateException()
            }
        }

    }

    private enum class Result(val score: Int) {
        WIN(6), LOSE(0), DRAW(3)
    }

}