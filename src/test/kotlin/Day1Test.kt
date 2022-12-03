import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun `Part 1`() {
        assertEquals(60, Day1.part1("day1/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals(92, Day1.part2("day1/testinput.txt"))
    }

}
