package com.sans.souci.adventofcode2024.day4

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay

sealed interface XMASState {
    data object Start : XMASState
    data object XFound : XMASState
    data object MFound : XMASState
    data object AFound : XMASState
    data object SFound : XMASState
}

// It's a graph traversal....
fun xmasCountDay1(lines: List<String>): Int {
    var xmasCounts = 0
    // For i in lines.indices
    // line = lines[i]
    // for j in line.indices
    // recurse
    for (lineIndex in lines.indices) {
        for (charIndex in lines[lineIndex].indices) {
            xmasCounts += dfs(lines, lineIndex, charIndex)
        }
    }

    return xmasCounts
}

fun dfs(lines: List<String>, lineIndex: Int, charIndex: Int, currentState: XMASState = XMASState.Start): Int {
    val bounds = lines.size
    if (lineIndex < 0 || lineIndex >= bounds || charIndex < 0 || charIndex >= bounds) {
        return 0
    }

    println("State $currentState LineI : $lineIndex CharI: $charIndex")
    val currChar = lines[lineIndex][charIndex]
    return when {
        currChar == 'X' && currentState == XMASState.Start -> {
            // Left
            dfs(lines, lineIndex, charIndex - 1, XMASState.XFound) +
                    // Right
                    dfs(lines, lineIndex, charIndex + 1, XMASState.XFound) +
                    // Top-Left
                    dfs(lines, lineIndex - 1, charIndex - 1, XMASState.XFound) +
                    // Above
                    dfs(lines, lineIndex - 1, charIndex, XMASState.XFound) +
                    // Top-Right
                    dfs(lines, lineIndex - 1, charIndex + 1, XMASState.XFound) +
                    // Bottom left
                    dfs(lines, lineIndex + 1 , charIndex - 1, XMASState.XFound) +
                    // Below
                    dfs(lines, lineIndex + 1, charIndex, XMASState.XFound) +
                    // Bottom-Right
                    dfs(lines, lineIndex + 1, charIndex + 1, XMASState.XFound)
        }

        currChar == 'M' && currentState == XMASState.XFound -> {
            dfs(lines, lineIndex, charIndex - 1, XMASState.MFound) +
                    dfs(lines, lineIndex, charIndex + 1, XMASState.MFound) +
                    dfs(lines, lineIndex - 1, charIndex - 1, XMASState.MFound) +
                    dfs(lines, lineIndex - 1, charIndex, XMASState.MFound) +
                    dfs(lines, lineIndex - 1, charIndex + 1, XMASState.MFound) +
                    dfs(lines, lineIndex + 1 , charIndex - 1, XMASState.MFound) +
                    dfs(lines, lineIndex + 1, charIndex, XMASState.MFound) +
                    dfs(lines, lineIndex + 1, charIndex + 1, XMASState.MFound)
        }

        currChar == 'A' && currentState == XMASState.MFound -> {
            dfs(lines, lineIndex, charIndex - 1, XMASState.AFound) +
                    dfs(lines, lineIndex, charIndex + 1, XMASState.AFound) +
                    dfs(lines, lineIndex - 1, charIndex - 1, XMASState.AFound) +
                    dfs(lines, lineIndex - 1, charIndex, XMASState.AFound) +
                    dfs(lines, lineIndex - 1, charIndex + 1, XMASState.AFound) +
                    dfs(lines, lineIndex + 1 , charIndex - 1, XMASState.AFound) +
                    dfs(lines, lineIndex + 1, charIndex, XMASState.AFound) +
                    dfs(lines, lineIndex + 1, charIndex + 1, XMASState.AFound)
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
