import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.*

class BowlingGameTest {
    val g: BowlingGame = BowlingGame()

    fun rollMany(n: Int, pins: Int) {
        for (i in 1..n)
            g.roll(pins)
    }

    fun rollSpare() {
        g.roll(5)
        g.roll(5)
    }

    @Test fun testGutterGame() {
        rollMany(20, 0)
        assertEquals(0, g.score())
    }

    @Test fun testAllOnes() {
        rollMany(20,1)
        assertEquals(20, g.score())
    }

    @Test fun testOneSpare() {
        rollSpare()
        g.roll(3)
        rollMany(17,0)
        assertEquals(16,g.score())
      }

    @Test fun testOneStrike() {
        g.roll(10) // strike
        g.roll(3)
        g.roll(4)
        rollMany(16, 0)
        assertEquals(24, g.score())
    }

    @Test fun testPerfectGame() {
        rollMany(12,10);
        assertEquals(300, g.score());
    }

}