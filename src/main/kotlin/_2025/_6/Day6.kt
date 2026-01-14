package org.example._2025._6

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day6_input.txt")!!.readText()
    val lines = input.lines()

    val problems = mutableListOf<MutableList<Long>>()
    var grandTotal: Long = 0

    for (line in lines) {
        val shrunk = line.replace(Regex("\\s+"), " ").trim()
        val items = shrunk.split(" ")
//        println(items.toString())

        for ((index, item) in items.withIndex()) {
            if (item == "+" || item == "*") {
                // Calculate
                val problem = problems[index]
                val total = problem.reduce { acc, i ->
                    when (item) {
                        "+" -> acc + i
                        "*" -> acc * i
                        else -> throw Error("Oops!")
                    }
                }
                println(problem.joinToString(" $item ") + " = " + total)
                grandTotal += total
            } else {
                // Build
                val problem = problems.getOrNull(index)
                if (problem == null) {
                    problems.add(mutableListOf(item.toLong()))
                } else {
                    problem.add(item.toLong())
                }
            }
        }
    }

    println("Grand Total: $grandTotal")
}