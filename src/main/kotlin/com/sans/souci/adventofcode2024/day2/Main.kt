package com.sans.souci.adventofcode2024.day2

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay
import java.io.BufferedReader

val increasingDifferenceRange = 1..3
val decreasingValidDifferenceRange = -1 downTo -3

fun determineValidDifferenceRange(difference: Int) = if (difference > 0) increasingDifferenceRange else decreasingValidDifferenceRange

fun countSafeReports(
    reportsBufferedReader: BufferedReader,
    tolerate: Boolean = false,
): Int {
    val validityOfReports =
        reportsBufferedReader.readLines().map { line ->
            val levels = line.split(" ").map(String::toInt)
            var reportValid = isReportValidArray(levels)
            if (!isReportValidArray(levels)) {
                if (tolerate) {
                    // Part two brute force...
                    for (i: Int in levels.indices) {
                        val levelsMinusOne = levels.toMutableList().apply { removeAt(i) }
                        if (isReportValidArray(levelsMinusOne)) {
                            reportValid = true
                        }
                    }
                }
            }

            reportValid
        }

    return validityOfReports.count { it }
}

fun isReportValidArray(levels: List<Int>): Boolean {
    val diffs = levels.zipWithNext().map { (a, b) -> b - a }

    val validDifferenceRange = determineValidDifferenceRange(diffs.first())

    return diffs.all { it in validDifferenceRange }
}

fun main() {
    val numberOfUnsafeReportsPartOne = puzzleInputForDay(2).use { countSafeReports(it) }

    val numberOfUnsafeReportsPartTwo = puzzleInputForDay(2).use { countSafeReports(it, tolerate = true) }

    println("Number of safe reports part one: $numberOfUnsafeReportsPartOne should be 282")
    println("Number of safe reports part two: $numberOfUnsafeReportsPartTwo should be 349")
}
