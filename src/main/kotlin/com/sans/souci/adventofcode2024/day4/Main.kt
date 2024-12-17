package com.sans.souci.adventofcode2024.day4

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay

sealed interface XMASState {
    data object Start : XMASState

    data object XFound : XMASState

    data object MFound : XMASState

    data object AFound : XMASState
}

sealed interface Direction {
    data object Up : Direction

    data object Down : Direction

    data object Left : Direction

    data object Right : Direction

    data object DiagonalUpLeft : Direction

    data object DiagonalDownLeft : Direction

    data object DiagonalUpRight : Direction

    data object DiagonalDownRight : Direction
}

fun xmasCountDay1(lines: List<String>): Int {
    var xmasCounts = 0
    for (lineIndex in lines.indices) {
        for (charIndex in lines[lineIndex].indices) {
            xmasCounts += dfs(lines, lineIndex, charIndex)
        }
    }

    return xmasCounts
}

typealias DFS = (
    lines: List<String>,
    lineIndex: Int,
    charIndex: Int,
    currentState: XMASState,
    direction: Direction,
) -> Int

val validTraversals =
    mutableMapOf<Direction, DFS>(
        Direction.DiagonalUpLeft to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex - 1,
                charIndex - 1,
                currentState,
                direction,
            )
        },
        Direction.Up to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex - 1,
                charIndex,
                currentState,
                direction,
            )
        },
        Direction.DiagonalUpRight to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex - 1,
                charIndex + 1,
                currentState,
                direction,
            )
        },
        Direction.Left to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex,
                charIndex - 1,
                currentState,
                direction,
            )
        },
        Direction.Right to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex,
                charIndex + 1,
                currentState,
                direction,
            )
        },
        Direction.DiagonalDownLeft to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex + 1,
                charIndex - 1,
                currentState,
                direction,
            )
        },
        Direction.Down to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex + 1,
                charIndex,
                currentState,
                direction,
            )
        },
        Direction.DiagonalDownRight to { lines, lineIndex, charIndex, currentState, direction ->
            dfs(
                lines,
                lineIndex + 1,
                charIndex + 1,
                currentState,
                direction,
            )
        },
    )

fun dfs(
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
            validTraversals.entries.map {
                it.value(lines, lineIndex, charIndex, XMASState.XFound, it.key)
            }.sum()
        }

        currChar == 'M' && currentState == XMASState.XFound -> {
            validTraversals[direction]!!.invoke(lines, lineIndex, charIndex, XMASState.MFound, direction!!)
        }

        currChar == 'A' && currentState == XMASState.MFound -> {
            validTraversals[direction]!!.invoke(lines, lineIndex, charIndex, XMASState.AFound, direction!!)
        }

        currChar == 'S' && currentState == XMASState.AFound -> {
            1
        }

        else -> 0
    }

    // for char in line
    // if out of bounds return
    // if char is X
    // Set state to X found
    // DFS 8 directions with bounds checking
    // if char is M and State is X found
    // Set state to M found
    // DFS 8 directions with bounds checking
    // if char is A and State is M found
    // Set state to A found
    // DFS 8 directions with bounds checking
    // if char is S and State is A found
    // Set state to S found
    // return
    // else return
}

fun main() {
    val puzzleInput = puzzleInputForDay(4).readLines()
    println("XMAS found ${xmasCountDay1(puzzleInput)}")
}
