fun main() {
    println("Day 4 step 1 : " + countCandidatesBetween(124075, 580769))
    println("Day 4 step 2 : " + countCandidatesBetweenWithUniqueDoubles(124075, 580769))
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

fun countCandidatesBetweenWithUniqueDoubles(lower: Int, upper: Int): Int {
    var count = 0
    for (n in lower..upper) {
        val numbs = asArray(n)
        if (isAscending(numbs) && hasUniqueDoubles(numbs)) {
            count++
        }
    }
    return count
}

fun asArray(n: Int): IntArray {
    return intArrayOf(n / 100000, (n / 10000) % 10, (n / 1000) % 10, (n / 100) % 10, (n / 10) % 10, n % 10)
}

fun isAscending(numbs: IntArray): Boolean {
    for (i in 0..numbs.size - 2) if (numbs[i] > numbs[i + 1]) return false
    return true;
}

fun hasDoubles(numbs: IntArray): Boolean {
    for (i in 1 until numbs.size) if (numbs[i] == numbs[i - 1]) return true
    return false
}

fun hasUniqueDoubles(numbs: IntArray): Boolean {
    if (numbs[0] == numbs[1] && numbs[0] != numbs[2]) return true // Start
    if (numbs[5] == numbs[4] && numbs[5] != numbs[3]) return true // End
    for (i in 2 until numbs.size - 1) // Middles
        if (numbs[i] != numbs[i - 2] && numbs[i] == numbs[i - 1] && numbs[i] != numbs[i + 1]) return true
    return false
}
