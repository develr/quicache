package org.quicache.engine.extension

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ListExtensionTest : FunSpec({

    context("getIndexOrElse") {
        test("on index not defined returns null") {
            val listResult = listOf("rock", "and", "roll").toList()
            val idxValue = listResult.getIndexOrElse(4, "nothing")
            idxValue shouldNotBe null
            idxValue shouldBe "nothing"
        }

        test("on index defined returns value") {
            val listResult = listOf("rock", "and", "roll").toList()
            val idxValue = listResult.getIndexOrElse(1, "nothing")
            idxValue shouldNotBe null
            idxValue shouldBe listResult[1]
        }
    }
})
