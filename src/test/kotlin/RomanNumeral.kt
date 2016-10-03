import org.junit.Assert.assertEquals
import org.junit.Test

fun rn(value: String) = RomanNumeral.parse(value).value

class RomanNumeralTest {
    @Test fun illegalValuesShouldThrowException() {
        val ex = try { rn("foo") } catch(e: IllegalArgumentException) { e }
        if (ex is IllegalArgumentException) assertEquals("foo is not a valid Roman numeral: f is not a valid base", ex.message)
    }
    @Test fun lowerAndUpperCaseShouldReturnSameValue() {
        assertEquals(rn("i"), rn("I"))
        assertEquals(rn("v"), rn("V"))
        assertEquals(rn("x"), rn("X"))
        assertEquals(rn("l"), rn("L"))
        assertEquals(rn("c"), rn("C"))
        assertEquals(rn("d"), rn("d"))
        assertEquals(rn("m"), rn("M"))
    }
    @Test fun value_I_ShouldReturn_1() {
        assertEquals(1, rn("I"))
    }
    @Test fun value_II_ShouldReturn_2() {
        assertEquals(2, rn("II"))
    }
    @Test fun value_III_ShouldReturn_3() {
        assertEquals(3, rn("III"))
    }
    @Test fun value_IV_ShouldReturn_4() {
        assertEquals(4, rn("iv"))
    }
    @Test fun value_V_ShouldReturn_5() {
        assertEquals(5, rn("V"))
    }
    @Test fun value_VI_ShouldReturn_6() {
        assertEquals(6, rn("vi"))
    }
    @Test fun value_VII_ShouldReturn_7() {
        assertEquals(7, rn("vii"))
    }
    @Test fun value_VIII_ShouldReturn_8() {
        assertEquals(8, rn("viii"))
    }
    @Test fun value_IX_ShouldReturn_9() {
        assertEquals(9, rn("ix"))
    }
    @Test fun value_X_ShouldReturn_10() {
        assertEquals(10, rn("X"))
    }
    @Test fun value_L_ShouldReturn_50() {
        assertEquals(50, rn("L"))
    }
    @Test fun value_C_ShouldReturn_100() {
        assertEquals(100, rn("C"))
    }
    @Test fun value_D_ShouldReturn_500() {
        assertEquals(500, rn("D"))
    }
    @Test fun value_M_ShouldReturn_1000() {
        assertEquals(1000, rn("M"))
    }
    @Test fun value_MCMXCII_ShouldReturn_1992() {
        assertEquals(1992, rn("MCMXCII"))
    }
    @Test fun value_MCMLXXXVII_ShouldReturn_1987() {
        assertEquals(1987, rn("MCMLXXXVII"))
    }
    @Test fun toStringShouldCreateValidRomanNumers() {
        time {
            for (i in 1..3000) {
                val s = RomanNumeral(i).toString()
                //  println(s)
                //assertEquals(i, rn(s))
            }
        }
    }

    fun time( f: () -> Unit ) {
        val s = System.currentTimeMillis()
        f()
        val e = System.currentTimeMillis()
        println("time: ${e-s}ms")
    }
}