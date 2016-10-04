import org.junit.Assert.assertEquals
import org.junit.Test

class PotterPurchaseTest {
    @Test fun basicPricingShouldBe8() {
        assertEquals(0, price(listOf()).intValueExact())
        assertEquals(8, price(listOf(0)).intValueExact())
        assertEquals(8, price(listOf(1)).intValueExact())
        assertEquals(8, price(listOf(2)).intValueExact())
        assertEquals(8, price(listOf(3)).intValueExact())
        assertEquals(8, price(listOf(4)).intValueExact())
        assertEquals(8 * 2, price(listOf(0, 0)).intValueExact())
        assertEquals(8 * 3, price(listOf(1, 1, 1)).intValueExact())
    }

    @Test fun testSimpleDiscounts() {
        assertEquals(8 * 2 * 0.95, price(listOf(0, 1)).toDouble(), 0.01)
        assertEquals(8 * 3 * 0.9, price(listOf(0, 2, 4)).toDouble(), 0.01)
        assertEquals(8 * 4 * 0.8, price(listOf(0, 1, 2, 4)).toDouble(), 0.01)
        assertEquals(8 * 5 * 0.75, price(listOf(0, 1, 2, 3, 4)).toDouble(), 0.01)
    }

    @Test fun testSeveralDiscounts() {
        assertEquals(8 + (8 * 2 * 0.95), price(listOf(0, 0, 1)).toDouble(), 0.01)
        assertEquals(2 * (8 * 2 * 0.95), price(listOf(0, 0, 1, 1)).toDouble(), 0.01)
        assertEquals((8 * 4 * 0.8) + (8 * 2 * 0.95), price(listOf(0, 0, 1, 2, 2, 3)).toDouble(), 0.01)
        assertEquals(8 + (8 * 5 * 0.75), price(listOf(0, 1, 1, 2, 3, 4)).toDouble(), 0.01)
    }

    @Test fun testEdgeCases() {
        assertEquals(2 * (8 * 4 * 0.8), price(listOf(0, 0, 1, 1, 2, 2, 3, 4)).toDouble(), 0.01)
        assertEquals(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),
                price(listOf(0, 0, 0, 0, 0,
        1, 1, 1, 1, 1,
        2, 2, 2, 2,
        3, 3, 3, 3, 3,
        4, 4, 4, 4)).toDouble(), 0.01)
    }

}