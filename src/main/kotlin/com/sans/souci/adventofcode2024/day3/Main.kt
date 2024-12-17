package com.sans.souci.adventofcode2024.day3

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay

val mulRegex = Regex("""mul\(([0-9]+),([0-9]+)\)""")

fun sumOfMultiplesPart1(inputLines: List<String>): Int = inputLines.map(::applyMulRegexToInput).sum()

fun applyMulRegexToInput(input: String) =
    mulRegex.findAll(input).toList().sumOf {
        it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt()
    }

fun sumOfMultiplesPart2(inputLines: List<String>): Int {
    // Begin enabled
    var multEnabled = true
    var sum = 0
    // while lines to iterate
    //   store start index
    //   while more characters in line
    //     if curr character is d
    //          if don't() is found, set multEnabled to false
    //              call sumOfMultPart with substring(start index, start of don't)
    //              update sum
    //          else if do() is found, set multEnabled to true
    //              update start index to end of do()
    //          fi
    //     fi
    //     character++
    //   end while
    // end while
    for (line: String in inputLines) {
        var charIndex = 0
        var multEnabledStartIndex = 0
        while (charIndex < line.length) {
            if (line[charIndex] == 'd') {
                if (DONT_TOKEN.tokenPresent(line, charIndex) && multEnabled) {
                    multEnabled = false
                    val mulString = line.substring(multEnabledStartIndex, charIndex)
                    sum += applyMulRegexToInput(mulString)
                } else if (DO_TOKEN.tokenPresent(line, charIndex) && !multEnabled) {
                    multEnabledStartIndex = charIndex + 1
                    multEnabled = true
                }
            }

            charIndex++

            if (charIndex == line.length && multEnabled) {
                // Calculate remaining up to the end of the line
                val mulString = line.substring(multEnabledStartIndex, charIndex)
                sum += applyMulRegexToInput(mulString)
            }
        }
    }

    return sum
}

const val DO_TOKEN = "do()"
const val DONT_TOKEN = "don't()"

fun String.tokenPresent(
    line: String,
    startIndex: Int,
): Boolean {
    val endIndex = startIndex + this.length
    if (endIndex <= line.length) {
        return line.substring(startIndex, endIndex) == this
    }

    return false
}

fun main() {
    val sumOfMultiplesPart1 = sumOfMultiplesPart1(puzzleInputForDay(3).readLines())
    val sumOfMultiplesPart2 = sumOfMultiplesPart2(puzzleInputForDay(3).readLines())

    println("Sum of multiples part 1: $sumOfMultiplesPart1")

    // First attempt(your answer is too high) 127980538
    // Second attempt Right answer...
    println("Sum of multiples part 2: $sumOfMultiplesPart2")
}
