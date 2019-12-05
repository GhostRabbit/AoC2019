import kotlin.math.abs

fun main() {
    Timer.measure {
        val lines = Inputs.readEachLine("Day3.input")
        println("Day3 Step1: " + selectNearest(lines))
        println("Day3 Step2: " + selectShortest(lines))
    }
}

fun selectShortest(lines: List<List<String>>): Int? {
    val paths = lines.map { asPath(it) }
    val intersects = findIntersect(paths)
    val circuits = intersects.map { paths[0][it.key]!!.plus(paths[1][it.key]!!) }
    return circuits.min()
}

fun selectNearest(lines: List<List<String>>): Int? {
    val paths = lines.map { asPath(it) }
    val intersects = findIntersect(paths)
    return intersects.map { abs(it.key.first) + abs(it.key.second) }.min()
}

fun findIntersect(paths: List<MutableMap<Pair<Int, Int>, Int>>): Map<Pair<Int, Int>, Int> {
    return paths[0].filter { paths[1].containsKey(it.key) }
}

fun asPath(commands: List<String>): MutableMap<Pair<Int, Int>, Int> {
    val result: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
    var x = 0
    var y = 0
    var step = 0

    commands.forEach {
        val steps = it.substring(1).toInt()
        when (it[0]) {
            'L' -> for (i in 1..steps) {
                ++step
                result.getOrPut(Pair(--x, y), { step })
            }
            'R' -> for (i in 1..steps) {
                ++step
                result.getOrPut(Pair(++x, y), { step })
            }
            'U' -> for (i in 1..steps) {
                ++step
                result.getOrPut(Pair(x, --y), { step })
            }
            'D' -> for (i in 1..steps) {
                ++step
                result.getOrPut(Pair(x, ++y), { step })
            }
        }
    }
    return result
}