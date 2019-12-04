fun main() {
    val codes = Inputs.readCsv("./Day2.input")
    println("Day2 Step1: " + decode(applyFix(12, 2, codes.toMutableList())))
    println("Day2 Step2: " + iterate(codes))
    println("Day2 Step2.5: " + decode(applyFix(50, 64, codes.toMutableList())))
}

fun iterate(codes: List<Int>): Int {
    var noun = 0
    var verb = 0

    while (true) {
        val result = decode(applyFix(noun, verb, codes.toMutableList()))
        if (result == 19690720) {
            println("$result $noun $verb")
            break
        }
        noun += 1
        if (noun == 100) {
            noun = 1
            verb += 1
        }
    }
    return 100 * noun + verb
}


fun applyFix(noun: Int, verb: Int, codes: MutableList<Int>): MutableList<Int> {
    codes[1] = noun
    codes[2] = verb
    return codes
}

fun decode(codes: MutableList<Int>): Int {
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

