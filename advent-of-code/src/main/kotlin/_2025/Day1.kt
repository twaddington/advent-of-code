package org.example._2025

const val testInput = """
L68
L30
R48
L5
R60
L55
L1
L99
R14
L82
"""

fun solve(lines: List<String>): Int {
    var result = 0
    var position = 50

    for (line in lines) {
        if (line.isEmpty()) continue
        val sign = when {
            line.startsWith("L") -> -1
            line.startsWith("R") -> 1
            else -> throw RuntimeException("Invalid line: $line")
        }
        val rotation = line.substring(1).toInt() % 100
        position += rotation * sign

        if (position < 0) {
            position += 100
        }
        if (position > 99) {
            position -= 100
        }
        if (position == 0) {
            result += 1
        }
//        println("rotation=$rotation, position=$position, result=$result")
    }
    return result
}


fun main() {
    val input = object {}.javaClass.getResource("/2025/Day1_input.txt")!!.readText()
    val result = solve(input.lines())
    println(result)
}
