package br.eng.r2a.quicache.engine.store

data class QuicacheObject(
    val key: String,
    val value: ByteArray,
    val schema: String = "default",
    val ttl: Int? // Time in ms
)