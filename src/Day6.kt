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
    val actual = distBetween("YOU", "SAN", system.second)
    if (expectedDistance != actual) println(actual)
    return expectedDistance == actual
}

fun part2(input: List<String>): Int {
    return distBetween("YOU", "SAN", buildSystem(input).second)
}

fun distBetween(from: String, to: String, childParent: Map<String, String>): Int {
    return countStepsToCrossing(from, to, childParent) + countStepsToCrossing(to, from, childParent)
}

private fun countStepsToCrossing(from: String, to: String, childParent: Map<String, String>): Int {
    var count = -1
    val path = pathOf(from, childParent)
    var n = to
    while (true) {
        if (path.contains(n)) return count
        count++
        n = childParent[n]!!
    }
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
    val parent2Children = mutableMapOf<String, MutableSet<String>>()
    val child2Parent = mutableMapOf<String, String>()
    input.map { line -> line.split(')') }.forEach {
        parent2Children.getOrPut(it[0], { mutableSetOf() }).add(it[1])
        child2Parent[it[1]] = it[0]
    }
    return Pair(parent2Children, child2Parent)
}
