class Computer(bootloader: List<Int>) {
    var memory = bootloader.toMutableList()

    fun poke(ip: Int, value: Int) {
        memory[ip] = value
    }

    fun execute(): Int {
        var ip = 0
        while (true) {
            when (memory[ip]) {
                1 -> {
                    val p1 = memory[ip + 1]
                    val p2 = memory[ip + 2]
                    val r = memory[ip + 3]
                    memory[r] = memory[p1] + memory[p2]
                    ip += 4
                }
                2 -> {
                    val p1 = memory[ip + 1]
                    val p2 = memory[ip + 2]
                    val r = memory[ip + 3]
                    memory[r] = memory[p1] * memory[p2]
                    ip += 4
                }
                99 -> return memory[0]
                else -> return -1
            }
        }
    }
}