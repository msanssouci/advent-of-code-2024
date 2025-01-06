package com.sans.souci.adventofcode2024.day4

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MainKtTest : FunSpec({

    test("Can solve day 1 example ") {
        val sampleInput =
            """
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

        xmasCountDay1(sampleInput.split("\n")) shouldBe 18
    }

    test("Can solve day 2 example ") {
        val sampleInput =
            """
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

         xmasCountDay2(sampleInput.split("\n")) shouldBe 9
    }

    test("Can solve small") {
        val smallSampleInput =
            """
            XMAS
            MMAA
            ASAA
            SMAS
            """.trimIndent()

        xmasCountDay1(smallSampleInput.split("\n")) shouldBe 3
    }

    test("Can solve day 1 full") {
        xmasCountDay1(puzzleInputForDay(4).readLines()) shouldBe 2685
    }
})
