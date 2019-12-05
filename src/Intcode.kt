import java.lang.IllegalArgumentException

class Computer(bootloader: List<Int>) {
    var memory = bootloader.toMutableList()
    var input: Int = 0
    var output: Int = 0

    fun poke(ip: Int, value: Int) {
        memory[ip] = value
    }

    fun read(ip: Int, mode: Int): Int {
        if (mode == 0) // position mode
            return memory[memory[ip]]
        return memory[ip] // immediate mode
    }

    fun execute(): Int {
        var ip = 0
        while (true) {
            val opcode = memory[ip] % 100
            val mode1 = memory[ip] % 1000 / 100
            val mode2 = memory[ip] % 10000 / 1000
            val mode3 = memory[ip] % 100000 / 10000

            when (opcode) {
                1 -> { // Add two numbers at
                    val p1 = read(ip + 1, mode1)
                    val p2 = read(ip + 2, mode2)
                    val r = memory[ip + 3]
                    memory[r] = p1 + p2
                    ip += 4
                }
                2 -> { // Multiply two numbers at
                    val p1 = read(ip + 1, mode1)
                    val p2 = read(ip + 2, mode2)
                    val r = memory[ip + 3]
                    memory[r] = p1 * p2
                    ip += 4
                }
                3 -> { // Store input at
                    val r = memory[ip + 1]
                    memory[r] = input
                    ip += 2
                }
                4 -> { // Write output from
                    output = read(ip + 1, mode1)
                    ip += 2
                }
                5 -> { // jump-if-true
                    val p1 = read(ip + 1, mode1)
                    val p2 = read(ip + 2, mode2)
                    ip = if (p1 != 0) p2 else ip + 3
                }
                6 -> { // jump-if-false
                    val p1 = read(ip + 1, mode1)
                    val p2 = read(ip + 2, mode2)
                    ip = if (p1 == 0) p2 else ip + 3
                }
                7 -> { // less then
                    val p1 = read(ip + 1, mode1)
                    val p2 = read(ip + 2, mode2)
                    val r = memory[ip + 3]
                    memory[r] = if (p1 < p2) 1 else 0
                    ip += 4
                }
                8 -> { // equals
                    val p1 = read(ip + 1, mode1)
                    val p2 = read(ip + 2, mode2)
                    val r = memory[ip + 3]
                    memory [r] = if (p1 == p2) 1 else 0
                    ip += 4
                }
                99 -> return memory[0]
                else -> throw IllegalArgumentException("Unknown opcode: $opcode")
            }
        }
    }
}