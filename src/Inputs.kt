import java.io.File

object Inputs {
    fun readLines(fileName: String): List<Int> = File(fileName).useLines { it.toList() }.toList().map { it.toInt() }

    fun readCsv(fileName: String): List<Int> =
        File(fileName).useLines { it.toList() }.toList().flatMap { it.split(',') }.map { it.toInt() }

    fun readEachLine(fileName: String) = File(fileName).useLines { it.toList() }.map { it -> it.split(',') }
}