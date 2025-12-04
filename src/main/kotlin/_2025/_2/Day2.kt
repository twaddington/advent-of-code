package org.example._2025._2

object Day2 {
    fun rangeFromString(value: String): LongRange {
        val parts = value.split("-")
        val start = parts[0].toLong()
        val end = parts[1].toLong()
        return start..end
    }

    fun isInvalid(value: Long): Boolean {
        val stringValue = value.toString()
        val length = stringValue.length
        if (length == 1) {
            return false
        }

        val chars = stringValue.chunked(1)
        if (chars.distinct().size == 1) {
            return true
        }
        if (chars.size < 4) {
            return false
        }

        val divisors = (2..<length)
            .filter { length % it == 0 }

        for (n in divisors) {
            val chunks = stringValue.chunked(n)
            if (chunks.distinct().size == 1) {
                return true
            }
        }
        return false
    }
}

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day2_input.txt")!!.readText()
    val ranges = input.split(",")
    val all = ranges.flatMap(Day2::rangeFromString)
    val invalid = all.filter(Day2::isInvalid)
    println(invalid.sum())
}