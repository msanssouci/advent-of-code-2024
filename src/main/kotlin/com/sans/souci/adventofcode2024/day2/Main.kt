package com.sans.souci.adventofcode2024.day2

import java.io.BufferedReader

class ShortCircuitException(message: String) : Exception(message)

val increasingDifferenceRange = 1..3
val decreasingValidDifferenceRange = -1 downTo -3

data class Report(
    val levels: String,
    val levelDifferences: List<Difference>
)

data class Difference(val a: Int, val b: Int, val difference: Int)

fun countSafeReportsPartOne(reportsBufferedReader: BufferedReader): Int {
    val validityOfReports = reportsBufferedReader.readLines().map { line ->
        var valid = false
        try {
            val differences = line.split(" ").zipWithNext()
                .map { (a, b) ->
                    // Compute the difference between the two levels
                    val difference = b.toInt() - a.toInt()

                    if (difference !in 1..3 && difference !in -1 downTo -3) {
                        // Short circuit if the difference is out of range
                        throw ShortCircuitException("Difference: $difference out of range")
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

        } catch (e: ShortCircuitException) {
            // Do nothing
        }

        valid
    }

    return validityOfReports.count { it }
}

fun countSafeReportsPartTwo(reportsBufferedReader: BufferedReader): Int {
    val validityOfReports = reportsBufferedReader.readLines().mapIndexed { index, line ->
        isValidReport(buildReport(line))
    }

    return validityOfReports.count { it }
}

fun buildReport(line: String) = Report(
    levels = line,
    levelDifferences = line.split(" ").zipWithNext().map { (a, b) ->
        // Compute the difference between the two levels
        val aInt = a.toInt()
        val bInt = b.toInt()
        Difference(a = aInt, b = bInt, difference = bInt - aInt)
    }
)

fun isValidReport(report: Report): Boolean {
    var i = 0
    var levelSkipped = false
    var validDifferenceRange: IntProgression = if (report.levelDifferences.first().difference > 0) increasingDifferenceRange else decreasingValidDifferenceRange
    while (i in report.levelDifferences.indices) {
        val isFirstElement = i == 0
        val isLastElement = (i + 1) == report.levelDifferences.size
        val currDifference = report.levelDifferences[i]
        val nextDifference = if (!isLastElement) report.levelDifferences[i + 1] else null

        if (currDifference.difference !in validDifferenceRange) {
            if (levelSkipped) {
                printReportFailure("Level already skipped. Index: $i", report, validDifferenceRange)
                return false
            }

            if (isFirstElement) {
                validDifferenceRange = if (nextDifference!!.difference > 0) increasingDifferenceRange else decreasingValidDifferenceRange
            }

            if (!isFirstElement && !isLastElement) {
                val newDifference = nextDifference!!.b - currDifference.a
                if (newDifference !in validDifferenceRange) {
                    printReportFailure(
                        "Removing element didn't resolve range issue.  Index: $i",
                        report,
                        validDifferenceRange
                    )
                    return false
                }

                i++
            }

            levelSkipped = true
        }

        i++
    }

    return true
}

fun printReportFailure(failureReason: String, report: Report, validDifferenceRange: IntProgression) {
    val containsMultipleDupes = report.levels.split(" ").groupBy { it }.any { it.value.size > 1 }
    val containsMultipleOutOfRange = report.levelDifferences.filter { it.difference !in validDifferenceRange }.size > 1
    if (!containsMultipleDupes && !containsMultipleOutOfRange) {
        println(
            "$failureReason Length ${report.levelDifferences.size} Levels: ${report.levels} Differences: ${
                report.levelDifferences.map { it.difference }.joinToString(" ")
            }"
        )
    }
}

fun main() {
    // Read the file
    val numberOfUnsafeReportsPartOne = object {}.javaClass.getResource("/day2/puzzle-input.txt")!!
        .openStream().bufferedReader().use { countSafeReportsPartOne(it) }

    val numberOfUnsafeReportsPartTwo = object {}.javaClass.getResource("/day2/puzzle-input.txt")!!
        .openStream().bufferedReader().use { countSafeReportsPartTwo(it) }

    println("Number of safe reports part one: $numberOfUnsafeReportsPartOne")
    println("Number of safe reports part two: $numberOfUnsafeReportsPartTwo")
}