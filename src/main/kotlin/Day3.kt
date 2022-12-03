import java.io.File

object Day3 {

    fun part1(filename: String): Int {
        var score = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val part1 = it.substring(0, it.length / 2)
            val part2 = it.substring(it.length / 2)
            for (character in part1) {
                if (part2.contains(character)) {
                    val priority = getPriority(character)
                    score += priority
                    break
                }
            }
        }
        return score
    }

    fun part2(filename: String): Int {
        TODO()
    }

    private fun getPriority(char: Char): Int {
        return if (char.isLowerCase()) {
            char.code - 96
        } else {
            char.code - 38
        }
    }

}