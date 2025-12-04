package org.example._2025._4

data class Cell(val row: Int, val col: Int, var isEmpty: Boolean) {
    val isNotEmpty: Boolean
        get() = !isEmpty
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

/** Returns the count of accessible cells that were removed. */
fun removeAccessible(matrix: List<List<Cell>>): Int {
    val accessible = matrix.flatMap { m ->
        m.filterNot { it.isEmpty }.filter { n -> isAccessible(n, matrix) }
    }
    accessible.forEach { it.isEmpty = true }
    return accessible.size
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
        matrix.add(row.toMutableList())
    }

    // Empty out any accessible cells
    var totalRemoved = 0
    var removedCount: Int

    do {
        removedCount = removeAccessible(matrix)
        totalRemoved += removedCount
    } while (removedCount > 0)

    println(totalRemoved)
}