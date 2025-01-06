package com.sans.souci.adventofcode2024.day4

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay

interface WordSearchTraverser {
    fun traverse(
        validDirections: Set<Direction>,
        lines: List<String>,
        lineIndex: Int,
        charIndex: Int,
        currentState: XMASState,
        direction: Direction,
    ): Int
}

sealed interface XMASState {
    data object Start : XMASState
    data object XFound : XMASState
    data object MFound : XMASState
    data object AFound : XMASState
}

typealias XMASSearch = (
    validDirections: Set<Direction>,
    lines: List<String>,
    lineIndex: Int,
    charIndex: Int,
    currentState: XMASState,
    direction: Direction
) -> Int

sealed class Direction(open val XMASSearch: XMASSearch) : WordSearchTraverser {
    class DiagonalUpLeft(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex - 1,
            charIndex - 1,
            currentState,
            direction,
        )
    }

    class Up(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex - 1,
            charIndex,
            currentState,
            direction,
        )
    }

    class DiagonalUpRight(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex - 1,
            charIndex + 1,
            currentState,
            direction,
        )
    }

    class Left(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex,
            charIndex - 1,
            currentState,
            direction,
        )
    }

    class Right(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex,
            charIndex + 1,
            currentState,
            direction,
        )
    }

    class DiagonalDownLeft(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex + 1,
            charIndex - 1,
            currentState,
            direction,
        )
    }

    class Down(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex + 1,
            charIndex,
            currentState,
            direction,
        )
    }

    class DiagonalDownRight(XMASSearch: XMASSearch) : Direction(XMASSearch) {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = XMASSearch(
            validDirections,
            lines,
            lineIndex + 1,
            charIndex + 1,
            currentState,
            direction,
        )
    }
}

fun xmasCountDay1(lines: List<String>): Int {
    fun day1Dfs(
        validDirections: Set<Direction>,
        lines: List<String>,
        lineIndex: Int,
        charIndex: Int,
        currentState: XMASState = XMASState.Start,
        direction: Direction? = null,
    ): Int {
        val bounds = lines.size
        if (lineIndex < 0 || lineIndex >= bounds || charIndex < 0 || charIndex >= bounds) {
            return 0
        }

        val currChar = lines[lineIndex][charIndex]
        return when {
            currChar == 'X' && currentState == XMASState.Start -> {
                validDirections.sumOf {
                    it.traverse(validDirections, lines, lineIndex, charIndex, XMASState.XFound, it)
                }
            }

            currChar == 'M' && currentState == XMASState.XFound && direction != null -> {
                direction.traverse(validDirections, lines, lineIndex, charIndex, XMASState.MFound, direction!!)
            }

            currChar == 'A' && currentState == XMASState.MFound && direction != null -> {
                direction.traverse(validDirections, lines, lineIndex, charIndex, XMASState.AFound, direction!!)
            }

            currChar == 'S' && currentState == XMASState.AFound -> {
                1
            }

            else -> 0
        }
    }

    val validDirections = setOf(
        Direction.DiagonalUpLeft(::day1Dfs),
        Direction.Up(::day1Dfs),
        Direction.DiagonalUpRight(::day1Dfs),
        Direction.Left(::day1Dfs),
        Direction.Right(::day1Dfs),
        Direction.DiagonalDownLeft(::day1Dfs),
        Direction.Down(::day1Dfs),
        Direction.DiagonalDownRight(::day1Dfs),
    )

    var xmasCounts = 0
    for (lineIndex in lines.indices) {
        for (charIndex in lines[lineIndex].indices) {
            xmasCounts += day1Dfs(validDirections, lines, lineIndex, charIndex)
        }
    }

    return xmasCounts
}

fun xmasCountDay2(lines: List<String>): Int {
    fun charOrNull(
        lines: List<String>,
        lineIndex: Int,
        charIndex: Int
    ) {

    }

    fun day2Dfs(
        validDirections: Set<Direction>,
        lines: List<String>,
        lineIndex: Int,
        charIndex: Int,
        currentState: XMASState = XMASState.Start,
        direction: Direction? = null,
    ): Int {
        val bounds = lines.size
        if (lineIndex < 0 || lineIndex >= bounds || charIndex < 0 || charIndex >= bounds) {
            return 0
        }

        if (lines[lineIndex][charIndex] == 'A') {
            val topLeft = lines[lineIndex - 1][charIndex - 1]
            val bottomRight = lines[lineIndex + 1][charIndex + 1]
            val set1 = setOf(topLeft, bottomRight)

            val topRight = lines[lineIndex - 1][charIndex + 1]
            val bottomLeft = lines[lineIndex + 1][charIndex - 1]
            val set2 = setOf(topRight, bottomLeft)

            if ((set1.size == 2 && set1.contains('S') && set1.contains('M')) &&
                (set2.size == 2 && set2.contains('S') && set2.contains('M'))
            ) {
                return 1
            }
        }

        return 0
    }

    val validDirections = setOf<Direction>()
    var xmasCounts = 0
    // We need at least a boundary of 1 character
    for (lineIndex in 1..<lines.size - 1) {
        for (charIndex in 1..<lines[lineIndex].length - 1) {
            xmasCounts += day2Dfs(validDirections, lines, lineIndex, charIndex)
        }
    }

    return xmasCounts
}

fun main() {
    val puzzleInput = puzzleInputForDay(4).readLines()
    println("XMAS found ${xmasCountDay1(puzzleInput)}")
    println("XMAS found ${xmasCountDay2(puzzleInput)}")
}
