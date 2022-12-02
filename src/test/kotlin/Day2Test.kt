import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Day2Test {

    @Test
    fun `Part 1`() {
        assertEquals(15, Day2.day2Part1("day2/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals(12, Day2.day2Part2("day2/testinput.txt"))
    }

}