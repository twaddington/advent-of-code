package org.example._2025._4

data class Cell(val row: Int, val col: Int, val isEmpty: Boolean) {
    val isNotEmpty = !isEmpty
}

fun isAccessible(cell: Cell, matrix: List<List<Cell>>): Boolean {
    var neighbors = 0
    for (mt in -1..1) {
        for (nt in -1..1) {
            val m = cell.row + mt
            val n = cell.col + nt
            if (m == cell.row && n == cell.col) continue
            try {
                val neighbor = matrix[m][n]
                if (neighbor.isNotEmpty) {
                    neighbors += 1
                }
            } catch (_: IndexOutOfBoundsException) {
                continue
            }
        }
    }
    return neighbors < 4
}

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day4_input.txt")!!.readText()
    val lines = input.lines()

    // Build matrix of printing floor cells
    val matrix = mutableListOf<List<Cell>>()
    for (m in 0..<lines.size) {
        val line = lines[m]
        val cells = line.chunked(1)
        val row = cells.mapIndexed { n, cell -> Cell(m, n, cell != "@") }
        matrix.add(row)
    }

    // Determine which cells are accessible
    val accessible = matrix.flatMap { m ->
        m.filterNot { it.isEmpty }.filter { n -> isAccessible(n, matrix) }
    }
    println(accessible.size)
}