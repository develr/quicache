package br.eng.r2a.quicache.engine.store

data class QuicacheObject(
    val key: String,
    val value: Byte,
    val schema: String = "default",
    val ttl: Long // Time in ms
)