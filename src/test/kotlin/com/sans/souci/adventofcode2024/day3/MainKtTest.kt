package com.sans.souci.adventofcode2024.day3

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.sequences.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class MainKtTest : FunSpec({
    test("Regex") {
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
        mulRegex.findAll(
            """]@;why()]&where()@select()mul(589,854)how()^#mul(517,928)^(%@#who()@'mul(82,659):don't()mul(670,226)when(626,911)from()&%{%where())-mul(244,869)<]mul(582,125)<mul(219,47):'!mul(95,365)select(){how()how()select()mul(273,775)[!],?)@mul(955,698)what()where()/;mul(79,369)&?*do()from()@mul(994,313)what()mul(603,3)'~@]'@@mul(729,217)#-/mul(561,454)!mul(588,577)^%'{mul(705,583){*;+;mul(418,129)~@:^mul(449,366)@what()},-)):^*mul(842,848)%what()where():what()))<from()/mul(775,104);&mul(425,243)%//{what()who()?{mul(842,148)how()<!who()@mul(197,181)@(~ from()do()how()#-[&)who()mul(254,640) what(){why(807,52)-+~!+who()mul(948,873)mul(127,689)~*-mul(147,430)~!who()}where()where()mul(476,718)select(86,742)@!?when();from()do()>why()>why())+mul(96,44)mul(179,145):*~^what()~what()who(595,39)+mul(174,903)%?<]&~mul(13,307)how(98,997)}who()select()?^{:mul(858,7)]from()why()how()mul(681,532)from()when()from(){select()>[{(}mul(908,908) ,mul(221,664)!:)~mul(327,84)how()-&~+ ]mul(865,773);*who()%,/%where()+mul(543,871)!(){]from()mul(257,93)(?(]mul(659,259)}~-#)]/what()why()mul(647,723)what()(why()who()who()select()(where()mul(189,838)who()&mul(231,195):?[+#/ what()mul(892,824)(:mul(467,622)how()mul(245,161)(} ~mul(5,520),mul(116,331)what()<what()%]from(613,447)>mul(227,866)#mul(4,224)#;{~mul(543,410):#[/what(272,644)what()>:+why()mul(869,342)} mul(990,443)(mul(575,968)>who()'~;where()-^mul(496,181){why()/}do()mul(334,968)mul(390,266)what(),don't()@<-who(302,636)mul(780,340)-?who()]mul(256,735)who()mul(230,265)- why(),[/select()>-mul(822,763)who()how()<*-@mul(914,719)where()from(),mul(515,606)when()#~)how()mul(412,4)where())>@[mul(264,473)<why()}how()what()%}+!+mul(541,503)?(/what();where(){ from()+mul(381,126)%select(),'{^);mul(511,611)^mul(825,203)mul(117,405)mul(375,348)<:*(}!*?@mul(529,944)+who()(&+!mul(297,217);#who()}from(94,251)@do()!:mul(410,847):!;mul(585-^)who()mul(489,753),@&![mul(54,446)^<!@mul>when()#mul(164,221)when()<<how() (++when()mul(55,438)why()@[+~^!*{mul(784,845)why()(when()~mul(747,880)&>when()why()from()'#mul(122,483)mul(866,284))^why()!mul(620,149)mul(620,850)select(){#@[why()+#!mul(999,114)!mul(151,527)who();>+:)+what()where()(mul(34,202):*,!where()]#!mul(668,743);/<{when()}when()%mul(768,640)*)<{mul(920,573)??:mul(438,188)^&mul(126,506)}mul<mul(240,639)?when()<}';!mul(846,177)when()%who()why()&!& mul(267,452)/&;mul(940,265)who()+who()[~mul(966,189)who()what()!!+select()]#mul(777,758):;from()select()?})]mul(442,116)!select()/?*where()mul(667,853)select()where()~-/what()*/mul(488,725)]<]?from()mul(628,497)/(select()when()^(;don't()why()&/#*(mul(849,651)select()select()when()from()':mul(862:~'mul(502,27)?when() mul(444,563)where()!!mul(266,682)}~select()<how()#-;mul(394-)from()where() ~mul(787,146)where()when()mul(83,219)'<(!]#)mul(731,303)what()how()*why()?#mul(286,142)<,,<%}{;~'mul(203,494)}{from()>&mul(733,119)"""
        ).shouldHaveSize(114)
    }
})