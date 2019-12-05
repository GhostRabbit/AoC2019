fun main() {
    Timer.measure {
        val bootloader = Inputs.readCsv("./Day2.input")
        println("Day2 Step1: " + executeWith12And2(bootloader))
        println("Day2 Step2: " + searchFor19690720(bootloader))
    }
}

fun executeWith12And2(bootloader: List<Int>): Int {
    val computer = Computer(bootloader)
    computer.poke(1, 12)
    computer.poke(2, 2)
    return computer.execute()
}

fun searchFor19690720(bootloader: List<Int>): Int {
    var counter = 0
    while (true) {
        val noun = counter % 100
        val verb = counter / 100
        counter++

        val computer = Computer(bootloader)
        computer.poke(1, noun)
        computer.poke(2, verb)
        val result = computer.execute()
        if (result == 19690720) {
//            println("$result $noun $verb")
            return 100 * noun + verb
        }
    }
}
