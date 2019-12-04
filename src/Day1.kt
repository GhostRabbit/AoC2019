fun main() {
    val masses = Inputs.readLines("./Day1.input")
    println("Step1: " + masses.map { calc(it) }.sum())
    println("Step2: " + masses.map { recCalc(it) }.sum())
}

fun calc(mass: Int): Int {
    return (mass / 3) - 2
}

fun recCalc(mass: Int): Int {
    val fuel = calc(mass)
    return when {
        fuel < 1 -> 0
        else -> fuel + recCalc(fuel)
    }
}
