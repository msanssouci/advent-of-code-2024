package com.sans.souci.adventofcode2024.day2

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.BufferedReader

class MainKtTest : FunSpec({

    test("countSafeReports") {
        val reports = """
      7 6 4 2 1
      1 2 7 8 9
      9 7 6 2 1
      1 3 2 4 5
      8 6 4 4 1
      1 3 6 7 9
     """.trimIndent()

        BufferedReader(reports.reader()).use { reader ->
            countSafeReports(reader) shouldBe 2
        }

    }
})
