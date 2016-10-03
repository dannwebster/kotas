import org.funktionale.utils.constant
import org.funktionale.utils.identity
import java.util.*

class BowlingGame {
    val rolls = ArrayList<Int>()

    fun roll(score: Int) {
        rolls += score
    }

    fun getRoll(index: Int): Int {
        return rolls.getOrElse(index, constant(0))
    }

    fun score(): Int {
        var frame = 0
        var isFirst = true
        return rolls.mapIndexed { i, s ->
            if (frame < 10) {
                if (isStrike(i)) {
                    frame++
                    strikeScore(i)
                } else if (isSpare(i)) {
                    frame++
                    spareScore(i)
                } else {
                    if (!isFirst)
                        frame++
                    isFirst = !isFirst
                    s
                }
            } else {
                0
            }
        }.sum()
    }

    private fun isStrike(i: Int): Boolean = getRoll(i) == 10
    private fun isSpare(i: Int): Boolean = getRoll(i) + getRoll(i - 1) == 10
    private fun strikeScore(i: Int): Int = getRoll(i) + getRoll(i + 1) + getRoll(i + 2)
    private fun spareScore(i: Int): Int = getRoll(i) + getRoll(i + 1)
}