import org.example.challenges.*

private const val inputFileName = "input.txt"

fun main() {
    val part1s = listOf(
        Day1.part1("day1/$inputFileName"),
        Day2.part1("day2/$inputFileName"),
        Day3.part1("day3/$inputFileName"),
        Day4.part1("day4/$inputFileName"),
        Day5.part1("day5/$inputFileName"),
        Day6.part1("day6/$inputFileName")
    )
    val part2s = listOf(
        Day1.part2("day1/$inputFileName"),
        Day2.part2("day2/$inputFileName"),
        Day3.part2("day3/$inputFileName"),
        Day4.part2("day4/$inputFileName"),
        Day5.part2("day5/$inputFileName"),
        Day6.part2("day6/$inputFileName")
    )
    for (i in 1..6) {
        println("=Day $i=")
        println("Part 1: ${part1s[i - 1]}")
        println("Part 2: ${part2s[i - 1]}")
    }
}
