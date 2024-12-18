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

sealed class Direction : WordSearchTraverser {
    data object DiagonalUpLeft : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex - 1,
            charIndex - 1,
            currentState,
            direction,
        )
    }

    data object Up : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex - 1,
            charIndex,
            currentState,
            direction,
        )
    }

    data object DiagonalUpRight : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex - 1,
            charIndex + 1,
            currentState,
            direction,
        )
    }

    data object Left : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex,
            charIndex - 1,
            currentState,
            direction,
        )
    }

    data object Right : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex,
            charIndex + 1,
            currentState,
            direction,
        )
    }

    data object DiagonalDownLeft : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex + 1,
            charIndex - 1,
            currentState,
            direction,
        )
    }

    data object Down : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
            validDirections,
            lines,
            lineIndex + 1,
            charIndex,
            currentState,
            direction,
        )
    }

    data object DiagonalDownRight : Direction() {
        override fun traverse(
            validDirections: Set<Direction>,
            lines: List<String>,
            lineIndex: Int,
            charIndex: Int,
            currentState: XMASState,
            direction: Direction
        ): Int = dfs(
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
    val validDirections = setOf(
        Direction.DiagonalUpLeft,
        Direction.Up,
        Direction.DiagonalUpRight,
        Direction.Left,
        Direction.Right,
        Direction.DiagonalDownLeft,
        Direction.Down,
        Direction.DiagonalDownRight

    )
    var xmasCounts = 0
    for (lineIndex in lines.indices) {
        for (charIndex in lines[lineIndex].indices) {
            xmasCounts += dfs(validDirections, lines, lineIndex, charIndex)
        }
    }

    return xmasCounts
}


fun dfs(
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
            validDirections.map {
                it.traverse(validDirections, lines, lineIndex, charIndex, XMASState.XFound, it)
            }.sum()
        }

        currChar == 'M' && currentState == XMASState.XFound -> {
            direction!!.traverse(validDirections, lines, lineIndex, charIndex, XMASState.MFound, direction!!)
        }

        currChar == 'A' && currentState == XMASState.MFound -> {
            direction!!.traverse(validDirections, lines, lineIndex, charIndex, XMASState.AFound, direction!!)
        }

        currChar == 'S' && currentState == XMASState.AFound -> {
            1
        }

        else -> 0
    }
}

fun main() {
    val puzzleInput = puzzleInputForDay(4).readLines()
    println("XMAS found ${xmasCountDay1(puzzleInput)}")
}
