import java.io.File

object Day2 {

    fun day2Part1(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val scores = it.split(" ")
            score += getScore(scores[1].toCharArray()[0])
            score += getWinnerScore(getHand(scores[0].toCharArray()[0]), getHand(scores[1].toCharArray()[0]))
        }
        return score
    }

    fun day2Part2(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val scores = it.split(" ")
            when (val result = getResult(scores[1].toCharArray()[0]).score) {
                getWinnerScore(getHand(scores[0].toCharArray()[0]), Hand.ROCK) -> {
                    score += Hand.ROCK.score
                    score += result
                }
                getWinnerScore(getHand(scores[0].toCharArray()[0]), Hand.PAPER) -> {
                    score += Hand.PAPER.score
                    score += result
                }
                getWinnerScore(getHand(scores[0].toCharArray()[0]), Hand.SCISSOR) -> {
                    score += Hand.SCISSOR.score
                    score += result
                }
            }
        }
        return score
    }

    private fun getWinnerScore(opponentHand: Hand, hand: Hand): Int {
        return if (hand == opponentHand) {
            3
        } else {
            when (hand) {
                Hand.ROCK -> {
                    if (opponentHand == Hand.PAPER) {
                        0
                    } else {
                        6
                    }
                }
                Hand.PAPER -> {
                    if (opponentHand == Hand.ROCK) {
                        6
                    } else {
                        0
                    }
                }
                Hand.SCISSOR -> {
                    if (opponentHand == Hand.ROCK) {
                        0
                    } else {
                        6
                    }
                }
            }
        }
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
        ROCK(1), PAPER(2), SCISSOR(3)
    }

    private enum class Result(val score: Int) {
        WIN(6), LOSE(0), DRAW(3)
    }

}