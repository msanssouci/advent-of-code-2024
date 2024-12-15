package com.sans.souci.adventofcode2024.day2

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MainKtTest : FunSpec({

    test("countSafeReportsPartOne") {
        object {}.javaClass.getResource("/day2/puzzle-input.txt")!!
            .openStream().bufferedReader().use { reader ->
                countSafeReports(reader) shouldBe 282
            }
    }

    test("countSafeReportsPartTwo") {
        object {}.javaClass.getResource("/day2/puzzle-input.txt")!!
            .openStream().bufferedReader().use { reader ->
                countSafeReports(reader, tolerate = true) shouldBe 349
            }
    }
})
