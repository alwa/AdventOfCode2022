import java.io.File

object Day4 {

    fun part1(filename: String): Int {
        var count = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            val rawAssignments = it.split(",")
            val firstPair = flatten(rawAssignments[0])
            val secondPair = flatten(rawAssignments[1])
            if ((firstPair.first >= secondPair.first && firstPair.second <= secondPair.second) ||
                (secondPair.first >= firstPair.first && secondPair.second <= firstPair.second)  ) {
                count++
            }
        }
        return count
    }

    private fun flatten(assignment: String): Pair<Int, Int> {
        val startAndEnd = assignment.split("-")
        return Pair(startAndEnd[0].toInt(), startAndEnd[1].toInt())
    }

}