fun main() {
    println("Day 4 step 1 : " + countCandidatesBetween(124075, 580769))
}

fun countCandidatesBetween(lower: Int, upper: Int): Int {
    var count = 0
    for (n in lower..upper) {
        val numbs = asArray(n)
        if (isAscending(numbs) && hasDoubles(numbs)) {
            count++
        }
    }
    return count
}

fun asArray(n: Int): IntArray {
    return intArrayOf(n / 100000, (n / 10000) % 10, (n / 1000) % 10, (n / 100) % 10, (n / 10) % 10, n % 10)
}

fun isAscending(numbs: IntArray): Boolean {
    for (i in 0..numbs.size - 2) {
        if (numbs[i] > numbs[i + 1]) return false
    }
    return true;
}

fun hasDoubles(numbs: IntArray): Boolean {
    for (i in 1 until numbs.size) if (numbs[i] == numbs[i - 1]) return true
    return false
}

private fun asNumber(actual: IntArray): Int {
    var n = actual[0]
    for (i in 1 until actual.size) n = n * 10 + actual[i]
    return n
}