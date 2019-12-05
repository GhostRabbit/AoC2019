fun main() {
    Timer.measure {
        val bootloader = Inputs.readCsv("Day5.input")
        println("Day 5 step 1 : " + part1(bootloader))
        println("Day 5 step 2 : " + part2(bootloader))
    }
}

fun part1(bootloader: List<Int>): Int {
    val computer = Computer(bootloader)
    computer.input = 1 // ID for the ship's air conditioner unit
    computer.execute()
    return computer.output
}


fun part2(bootloader: List<Int>): Int {
    val computer = Computer(bootloader)
    computer.input = 5 // ID for the ship's thermal radiation controller
    computer.execute()
    return computer.output
}
