package com.sans.souci.adventofcode2024.day1

import java.util.PriorityQueue
import kotlin.math.abs

val minHeap1 = PriorityQueue<Int>()
val minHeap2 = PriorityQueue<Int>()

/**
 * https://adventofcode.com/2024/day/1
 */
fun main() {
    // Iterate over lines in file puzzle-input.txt
    object {}.javaClass.getResource("/puzzle-input.txt")!!.readText().lines().forEach {
        val locationIds = it.split("   ")
        minHeap1.add(locationIds[0].toInt())
        minHeap2.add(locationIds[1].toInt())
    }

    var totalDistance = 0
    for (i in 0 until minHeap1.size) {
        val min1 = minHeap1.poll()
        val min2 = minHeap2.poll()
        totalDistance += abs(min2 - min1)
    }

    println("Total Distance: $totalDistance")
}
