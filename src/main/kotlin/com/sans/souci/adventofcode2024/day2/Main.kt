package com.sans.souci.adventofcode2024.day2

import java.io.BufferedReader

fun countSafeReports(reportsBufferedReader: BufferedReader): Int {
    val validityOfReports = reportsBufferedReader.readLines().map { line ->
        var valid = false
        try {
            val differences = line.split(" ").zipWithNext()
                .map { (a, b) ->
                    // Compute the difference between the two levels
                    val difference = b.toInt() - a.toInt()

                    if (difference !in 1..3 && difference !in -1 downTo -3) {
                        // Short circuit if the difference is out of range
                        throw Exception("Difference: $difference out of range")
                    }

                    difference
                }

            val increasing = differences.first() > 0
            valid = !differences.any {
                when {
                    increasing -> it < 0
                    else -> it > 0
                }
            }

        } catch (e: Exception) {
            println(e.message)
        }

        valid
    }

    return validityOfReports.count { it }
}

fun main() {
    // Read the file
    val numberOfUnsafeReports = object {}.javaClass.getResource("/day2/puzzle-input.txt")!!
        .openStream().bufferedReader().use { countSafeReports(it) }


    println("Number of safe reports: $numberOfUnsafeReports")
}