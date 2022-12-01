import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `Part 1`() {
        assertEquals(60, Day1.day1Part1("day1/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals(92, Day1.day1Part2("day1/testinput.txt"))
    }

}
