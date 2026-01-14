package org.example._2025._3

class Bank(val value: String) {
    val cells = value.chunked(1)
    val count = cells.size
    val maximumJoltage: Int

    init {
        val first = findLargest(cells.slice(0..<cells.size - 1))
        val indexOf = cells.indexOf(first.toString())
        val slice = cells.slice(indexOf + 1..<cells.size)
        val second = findLargest(slice)
        maximumJoltage = "$first$second".toInt()
    }

    override fun toString(): String {
        return "Bank(value=$value, cells=$cells, count=$count, maximumJoltage=$maximumJoltage)"
    }

    companion object {
        private fun findLargest(cells: List<String>): Int {
            val sorted = cells.map { it.toInt() }.sorted().reversed()
            return sorted.first()
        }
    }
}

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day3_test.txt")!!.readText()
    val lines = input.lines()

    var total = 0
    lines.forEach { line ->
        val bank = Bank(line)
        println(bank)
        total += bank.maximumJoltage
    }
    println(total)
}

// Answer (Part 1): 357
// Answer (Part 2): 3121910778619