import java.util.*
import kotlin.comparisons.compareByDescending

class RomanNumeral(val value: Int) {

    companion object {
        fun <T> o(tag: Int, t: T): T {
            //println("$tag: $t")
            return t
        }

        fun <K, V : Comparable<*>> Map<K, V>.invert(): Map<V, K> = this
                .mapTo(ArrayList<Pair<V, K>>()) { e -> Pair(e.value, e.key) }
                .toMap()
                .toSortedMap(compareByDescending { it -> it })


        val CHAR_TO_BASE = mapOf(
                'I' to 1,
                'V' to 5,
                'X' to 10,
                'L' to 50,
                'C' to 100,
                'D' to 500,
                'M' to 1000
        )
        val BASE_TO_CHAR = listOf(
                1000 to "M",
                900 to "CM",
                500 to "D",
                400 to "CD",
                100 to "C",
                90 to "XC",
                50 to "L",
                40 to "XL",
                10 to "X",
                9 to "IX",
                5 to "V",
                4 to "IV",
                1 to "I"
        )

        fun toBase(char: Char?): Int? = if (char == null) 0 else CHAR_TO_BASE[char.toUpperCase()]
        fun toBaseOrThrow(value: String, char: Char?): Int =
                toBase(char) ?: throw IllegalArgumentException("$value is not a valid Roman numeral: $char is not a valid base")

        fun parse(value: String): RomanNumeral {
            val i = value
                    .mapIndexed { i, b -> o(1, Pair(b, value.getOrNull(i + 1))) }
                    .map { p -> o(2, Pair(toBaseOrThrow(value, p.first), toBaseOrThrow(value, p.second))) }
                    .map { p -> o(3, if (p.first >= p.second) p.first else -1 * p.first) }
                    .sum()
            return RomanNumeral(i)
        }

        fun toStringR(i: Int, index: Int): String {
            val pair = RomanNumeral.BASE_TO_CHAR[index]
            return if (i >= pair.first)
                pair.second + toStringR(i - pair.first, index)
            else if (i > 0)
                toStringR(i, index + 1)
            else
                ""
        }
    }


    override fun toString(): String {
        return toStringR(this.value, 0)
    }
}
