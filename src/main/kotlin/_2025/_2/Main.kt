package org.example._2025._2

fun rangeFromString(value: String): LongRange {
    val parts = value.split("-")
    val start = parts[0].toLong()
    val end = parts[1].toLong()
    return start..end
}

fun isInvalid(value: Long): Boolean {
    val productId = value.toString()
    val isEven = productId.length % 2 == 0
    if (!isEven) return false
    val parts = productId.chunked(productId.length / 2)
    return parts[0] == parts[1]
}

fun main() {
    val input = object {}.javaClass.getResource("/2025/Day2_input.txt")!!.readText()
    val ranges = input.split(",")
    val all = ranges.flatMap(::rangeFromString)
    val invalid = all.filter(::isInvalid)
    println(invalid.sum())
}
