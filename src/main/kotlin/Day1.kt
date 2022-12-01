import java.io.File

object Day1 {

    fun day1Part1(filename: String) : Int  {
        var maxSumCalories = 0
        var tempSumCalories = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.isNotEmpty()) {
                tempSumCalories += it.toInt()
            } else {
                if (tempSumCalories > maxSumCalories) {
                    maxSumCalories = tempSumCalories
                }
                tempSumCalories = 0
            }
        }
        if (tempSumCalories > maxSumCalories) {
            maxSumCalories = tempSumCalories
        }
        return maxSumCalories
    }

    fun day1Part2(filename: String) : Int {
        val topThreeSumCalories = mutableListOf(0, 0, 0)
        var tempSumCalories = 0
        File(ClassLoader.getSystemResource(filename).file).forEachLine {
            if (it.isNotEmpty()) {
                tempSumCalories += it.toInt()
            } else {
                if (tempSumCalories > topThreeSumCalories.min()) {
                    topThreeSumCalories[topThreeSumCalories.indexOf(topThreeSumCalories.min())] = tempSumCalories
                }
                tempSumCalories = 0
            }
        }
        if (tempSumCalories > topThreeSumCalories.min()) {
            topThreeSumCalories[topThreeSumCalories.indexOf(topThreeSumCalories.min())] = tempSumCalories
        }
        return topThreeSumCalories.sum()
    }

}
