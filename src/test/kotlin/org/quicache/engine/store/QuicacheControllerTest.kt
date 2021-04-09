package org.quicache.engine.store

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.instanceOf
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.quicache.engine.exception.ContextException

class QuicacheControllerTest : FunSpec({

    lateinit var quicacheController: QuicacheController
    lateinit var successResult: QuicacheObject
    lateinit var successObject: QuicacheObject
    lateinit var successObject2: QuicacheObject
    lateinit var errorObject: QuicacheObject

    beforeTest {
        mockkObject(QuicacheContext)
        quicacheController = QuicacheController
        //success
        successObject = QuicacheObject("key", "value".toByteArray(), "schema", 0)
        successObject2 = QuicacheObject("key", "value".toByteArray(), null, 1)
        successResult = successObject
        //error
        errorObject = QuicacheObject("key", "valuer".toByteArray(), "schema", 0)

        every { QuicacheContext.addToContext(successObject) } returns Unit
        every { QuicacheContext.addToContext(successObject2) } returns Unit
        every { QuicacheContext.getFromContext("rocks", null) } returns successResult
        every { QuicacheContext.getFromContext("rocks", "quicache") } returns successResult
        every { QuicacheContext.addToContext(errorObject) } throws ContextException("err")
        every { QuicacheContext.getFromContext("skcor", null) } returns null
    }

    afterTest {
        unmockkAll()
    }

    context("quicache controller success tests") {
        test("addOnContext") {
            val returning = quicacheController
                .addOnContext("key", "value".toByteArray(), "schema", 0)

            returning shouldBe Unit
        }

        test("addOnContext with null values") {
            val returning = quicacheController
                .addOnContext("key", "value".toByteArray(), null, 1)

            returning shouldBe Unit
        }

        test("getOnContext") {
            val quicacheReturned = quicacheController
                .getOnContext("rocks", null)

            quicacheReturned shouldNotBe null
            String(quicacheReturned!!) shouldBe "value"
        }


        test("getOnContext with defined context") {
            val quicacheReturned = quicacheController
                .getOnContext("rocks", "quicache")

            quicacheReturned shouldNotBe null
            String(quicacheReturned!!) shouldBe "value"
        }
    }

    context("quicache controller error tests") {
        test("addOnContext") {
            shouldThrow<ContextException> {
                quicacheController
                    .addOnContext("key", "valuer".toByteArray(), "schema", 0)
            }
        }

        test("getOnContext") {
            val quicacheReturned = quicacheController
                .getOnContext("skcor", null)

            quicacheReturned shouldBe null
        }
    }
})
