fun main() {
    Timer.measure {
        if (test1()) {
            val input = Inputs.readLines("Day6.input")
            println("Day6 step1 : " + part1(input))
            println("Day6 step2 : ")
        }
    }
}

fun test1(): Boolean {
    val input = Inputs.readLines("Day6.test1.input")
    val expectedTotalOrbits = 42
    val bodies = buildSystem(input)
    val actual = sumOfDepths("COM", bodies, 0)
    if (expectedTotalOrbits != actual) println(actual)
    return expectedTotalOrbits == actual
}

fun part1(input: List<String>): Int {
    val system = buildSystem(input)
    return sumOfDepths("COM", system, 0)
}

fun sumOfDepths(body: String, system: Map<String, Set<String>>, depth: Int): Int {
    return depth + system[body].orEmpty().map { sumOfDepths(it, system, depth + 1) }.sum()
}

fun buildSystem(input: List<String>): Map<String, Set<String>> {
    val lookupMap = mutableMapOf<String, MutableSet<String>>()
    input.map { line -> line.split(')') }.forEach {
        lookupMap.getOrPut(it[0], { mutableSetOf<String>() }).add(it[1])
    }
    return lookupMap
}
