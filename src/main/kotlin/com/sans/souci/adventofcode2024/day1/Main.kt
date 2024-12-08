package com.sans.souci.adventofcode2024.day1

import java.util.PriorityQueue
import kotlin.math.abs


/**
 * Approximate max storage size is 3N where N is the number of lines in the input file
 */
data class PuzzleStorage(
    val minHeapLeft: PriorityQueue<Int>,
    val minHeapRight: PriorityQueue<Int>,
    val elementToCountMapLeft: Map<Int, Int>,
)

/**
 * Runtime complexity is O(N*(logN + logN)) where N is the number of lines in the input file.
 */
fun createPuzzleStorage(inputLines: List<String>): PuzzleStorage {
    val minHeapLeft = PriorityQueue<Int>()
    val minHeapRight = PriorityQueue<Int>()
    val elementToCountMapLeft = mutableMapOf<Int, Int>()

    inputLines.forEach {
        val locationIds = it.split("   ")
        val leftInt = locationIds[0].toInt()
        val rightInt = locationIds[1].toInt()
        minHeapLeft.add(leftInt)
        minHeapRight.add(rightInt)

        elementToCountMapLeft[rightInt] = elementToCountMapLeft.getOrDefault(rightInt, 0) + 1
    }

    return PuzzleStorage(minHeapLeft, minHeapRight, elementToCountMapLeft.toMap())
}

/**
 * Runtime complexity is O(N) where N is the number of lines in the input file.
 */
fun calculateTotalDistance(puzzleStorage: PuzzleStorage): Int {
    var totalDistance = 0
    for (i in 0 until puzzleStorage.minHeapLeft.size) {
        val min1 = puzzleStorage.minHeapLeft.poll()
        val min2 = puzzleStorage.minHeapRight.poll()
        totalDistance += abs(min2 - min1)
    }

    return totalDistance
}

/**
 * Runtime complexity is O(N) where N is the number of lines in the input file.
 */
fun calculateSimilarityScore(puzzleStorage: PuzzleStorage): Int {
    var similarityScore = 0

    for (i in 0 until puzzleStorage.minHeapLeft.size) {
        val left = puzzleStorage.minHeapLeft.poll()

        similarityScore += puzzleStorage.elementToCountMapLeft.getOrDefault(left, 0) * left
    }

    return similarityScore
}

fun main() {
    // Iterate over lines in file puzzle-input.txt
    val inputLines = object {}.javaClass.getResource("/day1/puzzle-input.txt")!!.readText().lines()
    println("Total Distance: ${calculateTotalDistance(createPuzzleStorage(inputLines))}")
    println("Similarity Score: ${calculateSimilarityScore(createPuzzleStorage(inputLines))}")
}
