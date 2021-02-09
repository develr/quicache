package br.eng.r2a.quicache.engine.store

data class QuicacheObject(
    val key: String,
    val value: ByteArray,
    val schema: String = "default",
    val ttl: Long // Time in ms
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is QuicacheObject) return false

        if (key != other.key) return false
        if (!value.contentEquals(other.value)) return false
        if (schema != other.schema) return false
        if (ttl != other.ttl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + value.contentHashCode()
        result = 31 * result + schema.hashCode()
        result = 31 * result + ttl.hashCode()
        return result
    }
}