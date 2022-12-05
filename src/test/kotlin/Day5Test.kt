import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun `Part 1`() {
        assertEquals("CMZ", Day5.part1("day5/testinput.txt"))
    }

    @Test
    fun `Part 2`() {
        assertEquals("MCD", Day5.part2("day5/testinput.txt"))
    }

}