import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    @Test
    fun `Part 1`() {
        assertEquals(15, Day2.part1("day2/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals(12, Day2.part2("day2/testinput.txt"))
    }

}