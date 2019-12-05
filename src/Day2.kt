fun main() {
    Timer.measure {
        val memory = Inputs.readCsv("./Day2.input")
        println("Day2 Step1: " + execute(poke(12, 2, memory.toMutableList())))
        println("Day2 Step2: " + search(memory))
        println("Day2 Step2.5: " + execute(poke(50, 64, memory.toMutableList())))
    }
}

fun search(memory: List<Int>): Int {
    var counter = 0
    while (true) {
        val noun = counter % 100
        val verb = counter / 100
        val result = execute(poke(noun, verb, memory.toMutableList()))
        if (result == 19690720) {
            println("$result $noun $verb")
            return 100 * noun + verb
        }
        counter ++
    }
}

fun poke(noun: Int, verb: Int, codes: MutableList<Int>): MutableList<Int> {
    codes[1] = noun
    codes[2] = verb
    return codes
}

fun execute(codes: MutableList<Int>): Int {
    var ip = 0
    while (true) {
        when (codes[ip]) {
            1 -> {
                val p1 = codes[ip + 1]
                val p2 = codes[ip + 2]
                val r = codes[ip + 3]
                codes[r] = codes[p1] + codes[p2]
                ip += 4
            }
            2 -> {
                val p1 = codes[ip + 1]
                val p2 = codes[ip + 2]
                val r = codes[ip + 3]
                codes[r] = codes[p1] * codes[p2]
                ip += 4
            }
            99 -> return codes[0]
            else -> return -1
        }

    }
}

