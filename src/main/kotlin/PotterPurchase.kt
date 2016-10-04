import java.math.BigDecimal
import java.math.MathContext
import java.util.*


fun List<Int>.product(): Int = this.fold(1, { i, j -> i * j})

fun bd(n: Number) = when (n) {
    is Int -> BigDecimal(n)
    is Double -> BigDecimal(n, MathContext(2))
    else -> throw IllegalArgumentException()
}

operator fun BigDecimal.times(i: Int) = this.multiply(bd(i))
fun List<BigDecimal>.sum() = this.fold(bd(0)) { i, j -> i + j}
fun <E> List<E>.removeEach(r: Iterable<E>): List<E> {
    val v = this.toMutableList()
    r.forEach {  v -= it }
    return v
}

data class PairAlias<A, B>(val pair: Pair<A, B>, val a: String, val b: String) {
    operator fun get(key: String) = when (key) {
        a -> pair.first
        b -> pair.second
        else -> null
    }
}
fun <A, B> Pair<A, B>.alias(a: String, b: String): PairAlias<A, B> = PairAlias(this, a, b)

fun price(books: List<Int>):BigDecimal {
    return findBookSets(listOf(), books)
            .map { s -> Pair(getDiscount(s), s)}
            .map { p -> p.first * p.second.size * 8 }
            .sum()
}

fun <E> List<E>.append(e: E): List<E> = this.toMutableList() + e

tailrec fun findBookSets(sets: List<Set<Int>>, books: List<Int>): List<Set<Int>> {
    println(sets)
    return if (books.isEmpty()) {
        sets
    } else {
        val set = books.toSet()
        findBookSets(sets.append(set), books.removeEach(set))
    }
}

private fun getDiscount(l: Set<Int>): BigDecimal {
    val discount: BigDecimal = bd(when (l.size) {
        1 -> 1
        2 -> 0.95
        3 -> 0.90
        4 -> 0.80
        5 -> 0.75
        else -> 0
    })
    return discount
}

/*
import java.util.List;
public class PotterDiscountCalculator {
double[] discountRates = { 1, 0.95, 0.90, 0.80, 0.75 };
int[] discounts = new int[5];
public Double calculatePrice(List<Integer> order) {
if (order == null || order.isEmpty()) return 0.0;
int[] booksInOrder = calculateBooksInOrder(order);
calculateDiscounts(booksInOrder);
optimizeDiscounts();
double price = 0.0;
for (int i = 0; i < discounts.length; i++) price += (8 * (i + 1) * discounts[i]) * discountRates[i];
return price;
}
protected void optimizeDiscounts() {
while (discounts[2] > 0 && discounts[4] > 0) {discounts[2]--;discounts[4]--;discounts[3] += 2;}
}
protected void calculateDiscounts(int[] booksInOrder) {
if (booksInOrder == null) return;

int differentFromZero = 0;
for (int i = 0; i < booksInOrder.length; i++) {
if (booksInOrder[i] > 0) {differentFromZero++; booksInOrder[i]--;}
}
if (differentFromZero > 0) {discounts[differentFromZero - 1] += 1;calculateDiscounts(booksInOrder);}
}
private int[] calculateBooksInOrder(List<Integer> order) {
int[] result = new int[5];
for (int book : order) result[book - 1]++;
return result;
}
}*/