package org.example._2025._5

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day5_input.txt")!!.readText()
    val lines = input.lines()

    val ranges = mutableListOf<LongRange>()
    val fresh = mutableListOf<Long>()
    val spoiled = mutableListOf<Long>()

    for (line in lines) {
        if (line.isBlank()) continue
        val isRange = line.contains("-")
        if (isRange) {
            val parts = line.split("-")
            val start = parts[0].toLong()
            val end = parts[1].toLong()
            ranges.add(LongRange(start, end))
        } else {
            val ingredient = line.toLong()
            if (ranges.any { it.contains(ingredient) }) {
                fresh.add(ingredient)
            } else {
                spoiled.add(ingredient)
            }
        }
    }

    println("Fresh: ${fresh.size}, Spoiled: ${spoiled.size}")
}