package com.sans.souci.adventofcode2024.utils

fun puzzleInputForDay(day: Int) = object {}.javaClass.getResource("/day$day/puzzle-input.txt")?.openStream()?.bufferedReader()
    ?: throw IllegalArgumentException("No input file found for day $day")
