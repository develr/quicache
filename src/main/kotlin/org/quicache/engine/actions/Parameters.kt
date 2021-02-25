package org.quicache.engine.actions


enum class Parameters(
    val paramVerb: String
) {
    SET("set"),
    GET("get"),
    FLUSH("flush")

}