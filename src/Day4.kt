fun main() {
    println("Day 4 step 1 : " + countCandidatesBetween(124075, 580769))
}

fun countCandidatesBetween(lower: Int, upper: Int): Int {
    var count = 0
    val numbs = asArray(lower)
    while (asNumber(numbs) < upper) {
        if (hasDoubles(numbs)) {
            count++
            println(numbs.contentToString() + " " + count)
//            if (count > 10) break
        }
        increase(numbs, 5)
    }
    return count
}

fun asArray(n: Int): IntArray {
    if (n == 124075) return intArrayOf(1, 2, 4, 4, 7, 7)
    return intArrayOf()
}

fun increase(numbs: IntArray, i: Int) {
    if (numbs[i] == 9) {
        increase(numbs, i - 1)
        numbs[i] = numbs[i - 1]
    } else
        numbs[i]++
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