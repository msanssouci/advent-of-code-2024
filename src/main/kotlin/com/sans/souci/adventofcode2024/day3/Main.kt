package com.sans.souci.adventofcode2024.day3

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay

val mulRegex = Regex("""mul\(([0-9]+),([0-9]+)\)""")

fun sumOfMultiples(input: String): Int {
    return mulRegex.findAll(input).toList().sumOf {
        it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt()
    }
}

fun main() {
    val sumOfMultiples = puzzleInputForDay(3).readLines().map(::sumOfMultiples).sum()

    println("Sum of multiples: $sumOfMultiples")
}