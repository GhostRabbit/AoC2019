fun main() {
    Timer.measure {
        val input = Inputs.readLines("Day6.input")
        if (test1()) {
            println("Day6 step1 : " + part1(input))
        }
        if (test2()) {
            println("Day6 step2 : " + part2(input))
        }
    }
}

fun test1(): Boolean {
    val input = Inputs.readLines("Day6.test1.input")
    val expectedTotalOrbits = 42
    val system = buildSystem(input)
    val actual = sumOfDepths("COM", system.first, 0)
    if (expectedTotalOrbits != actual) println(actual)
    return expectedTotalOrbits == actual
}

fun test2(): Boolean {
    val input = Inputs.readLines("Day6.test2.input")
    val system = buildSystem(input)
    val expectedDistance = 4
    val actual = distBetween("YOU", "SAN", system)
    if (expectedDistance != actual) println(actual)
    return expectedDistance == actual
}

fun part2(input: List<String>): Int {
    return distBetween("YOU", "SAN", buildSystem(input))
}

fun distBetween(s1: String, s2: String, system: Pair<Map<String, Set<String>>, Map<String, String>>): Int {
    val childParent = system.second
    val pathS1 = pathOf(s1, childParent)
    var n = s2
    var count = 0
    println(pathS1)
    while (!pathS1.contains(n)) {
        count++
        n = childParent[n]!!
    }
    count--
    val pathS2 = pathOf(s2, childParent)
    n = s1
    while (!pathS2.contains(n)) {
        count++
        n = childParent[n]!!
    }
    count--
    println(pathS2)
    return count
}

private fun pathOf(s1: String, childParent: Map<String, String>): List<String> {
    val path = mutableListOf<String>()
    var n: String? = s1
    while (n != null) {
        path.add(n)
        n = childParent[n]
    }
    return path
}

fun part1(input: List<String>): Int {
    val system = buildSystem(input)
    return sumOfDepths("COM", system.first, 0)
}

fun sumOfDepths(body: String, system: Map<String, Set<String>>, depth: Int): Int {
    return depth + system[body].orEmpty().map { sumOfDepths(it, system, depth + 1) }.sum()
}

fun buildSystem(input: List<String>): Pair<Map<String, Set<String>>, Map<String, String>> {
    val parentChild = mutableMapOf<String, MutableSet<String>>()
    val childParent = mutableMapOf<String, String>()
    input.map { line -> line.split(')') }.forEach {
        parentChild.getOrPut(it[0], { mutableSetOf<String>() }).add(it[1])
        childParent[it[1]] = it[0]
    }
    return Pair(parentChild, childParent)
}
