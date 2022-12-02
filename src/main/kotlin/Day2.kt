import java.io.File

object Day2 {

    fun day2Part1(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val scores = it.split(" ")
            score += getScore(scores[1].toCharArray()[0])
            score += getHand(scores[1].toCharArray()[0]).getResultAgainst(getHand(scores[0].toCharArray()[0])).score
        }
        return score
    }

    fun day2Part2(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val scores = it.split(" ")
            val opponentHand = getHand(scores[0].toCharArray()[0])
            val result = getResult(scores[1].toCharArray()[0])
            when (result.score) {
                Hand.ROCK.getResultAgainst(opponentHand).score -> {
                    score += Hand.ROCK.score
                }
                Hand.PAPER.getResultAgainst(opponentHand).score -> {
                    score += Hand.PAPER.score
                }
                Hand.SCISSOR.getResultAgainst(opponentHand).score -> {
                    score += Hand.SCISSOR.score
                }
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

    private fun getScore(char: Char): Int {
        return when (char) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
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