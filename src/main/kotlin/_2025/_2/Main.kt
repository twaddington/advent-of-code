package org.example._2025._2

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

    val chars = stringValue.toCharArray()
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

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day2_input.txt")!!.readText()
    val ranges = input.split(",")
    val all = ranges.flatMap(::rangeFromString)
    val invalid = all.filter(::isInvalid)
    println(invalid.sum())
}