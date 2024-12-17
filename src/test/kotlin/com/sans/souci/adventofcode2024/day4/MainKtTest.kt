package com.sans.souci.adventofcode2024.day4

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MainKtTest : FunSpec({
    val sampleInput = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

    val smallSampleInput = """
        XMAS
        MAAA
        AXSA
        SMAS
    """.trimIndent()

    test("Can solve day 1") {
        xmasCountDay1(sampleInput.split("\n")) shouldBe 18
    }

    test("Can solve small") {
        xmasCountDay1(smallSampleInput.split("\n")) shouldBe 2
    }
})