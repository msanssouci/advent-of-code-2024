package com.sans.souci.adventofcode2024.day3

import com.sans.souci.adventofcode2024.utils.puzzleInputForDay
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class MainKtTest : FunSpec({
    test("Mult Regex") {
        mulRegex.find("mul(1,2)").shouldNotBeNull().should {
            it.value shouldBe "mul(1,2)"
            it.groups[1].shouldNotBeNull().value shouldBe "1"
            it.groups[2].shouldNotBeNull().value shouldBe "2"
        }
        mulRegex.find("mul(589,854)").shouldNotBeNull().should {
            it.value shouldBe "mul(589,854)"
            it.groups[1].shouldNotBeNull().value shouldBe "589"
            it.groups[2].shouldNotBeNull().value shouldBe "854"
        }
        mulRegex.find("mul[1,2)").shouldBeNull()
    }

    test("Can calculate day 1") {
        sumOfMultiplesPart1(puzzleInputForDay(3).readLines()) shouldBe 174103751
    }

    test("Can calculate day 2") {
        sumOfMultiplesPart2(puzzleInputForDay(3).readLines()) shouldBe 100411201
    }

    test("Do present") {
        DO_TOKEN.tokenPresent("", 0).shouldBeFalse()
        DO_TOKEN.tokenPresent("do[)", 0).shouldBeFalse()
        DO_TOKEN.tokenPresent("do(]", 0).shouldBeFalse()
        DO_TOKEN.tokenPresent("do(", 0).shouldBeFalse()
        DO_TOKEN.tokenPresent("do(", 1).shouldBeFalse()
        DO_TOKEN.tokenPresent("do(", 5).shouldBeFalse()
        DO_TOKEN.tokenPresent("do()", 0).shouldBeTrue()
        DO_TOKEN.tokenPresent("93204239832049do()dsajlkjdslkdhsalsad", 14).shouldBeTrue()
    }

    test("Don't present") {
        DONT_TOKEN.tokenPresent("", 0).shouldBeFalse()
        DONT_TOKEN.tokenPresent("don't[)", 0).shouldBeFalse()
        DONT_TOKEN.tokenPresent("don't(]", 0).shouldBeFalse()
        DONT_TOKEN.tokenPresent("don't(", 0).shouldBeFalse()
        DONT_TOKEN.tokenPresent("don't(", 1).shouldBeFalse()
        DONT_TOKEN.tokenPresent("don't(", 7).shouldBeFalse()
        DONT_TOKEN.tokenPresent("93204239832049don't()dsajlkjdslkdhsalsad", 14).shouldBeTrue()
    }
})
