package com.sans.souci.adventofcode2024.day2

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.io.BufferedReader

class MainKtTest : FunSpec({

    val reports = """
      7 6 4 2 1
      1 2 7 8 9
      9 7 6 2 1
      1 3 2 4 5
      8 6 4 4 1
      1 3 6 7 9
     """.trimIndent()

    test("countSafeReportsPartOne") {
        BufferedReader(reports.reader()).use { reader ->
            countSafeReportsPartOne(reader) shouldBe 2
        }
    }

    test("countSafeReportsPartTwo") {
        BufferedReader(reports.reader()).use { reader ->
            countSafeReportsPartTwo(reader) shouldBe 4
        }
    }

    test("Invalid report") {
        isValidReport(buildReport("60 62 61 63 67")).shouldBeFalse()
        isValidReport(buildReport("78 81 84 87 90 94 95 99")).shouldBeFalse()
        isValidReport(buildReport("47 46 48 51 54 57 54 58")).shouldBeFalse()
        isValidReport(buildReport("5 3 5 8 9 12 12 13")).shouldBeFalse()
        isValidReport(buildReport("27 26 28 29 29 30 34")).shouldBeFalse()
    }

    test("Valid report") {
        isValidReport(buildReport("15 13 11 10 7 4")).shouldBeTrue()
        isValidReport(buildReport("74 75 76 78 81 82 85 86")).shouldBeTrue()
        isValidReport(buildReport("60 62 61 63")).shouldBeTrue()
        isValidReport(buildReport("68 69 69 70 71 74 75")).shouldBeTrue()
//        isValidReport(buildReport("5 8 4 3 2 1")).shouldBeTrue()
//        isValidReport(buildReport("2 5 4 3 2")).shouldBeTrue()
    }
})
