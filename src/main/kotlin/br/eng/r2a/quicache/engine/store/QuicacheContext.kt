package br.eng.r2a.quicache.engine.store

import br.eng.r2a.quicache.engine.exception.InvalidQuicacheValueException

/**
 * Quicache general context
 */
internal object QuicacheContext {
    val currentContext = mutableListOf<QuicacheObject>()

    fun addOnContext(key: String, value: ByteArray) {
        this.currentContext(createQuicacheObject(key, value))
    }

    fun createQuicacheObject(key: String, value: ByteArray): QuicacheObject {
        if(value.size == 0) {
            throw InvalidQuicacheValueException("Not a valid value for key $key on default schema")
        }
        return QuicacheObject(key, value)
    }

    fun createQuicacheObjectOnSchema(key: String, value: ByteArray, schema: String): QuicacheObject {
        if(value.size == 0) {
            throw InvalidQuicacheValueException("Not a valid value for key $key on $schema schema")
        }
        return QuicacheObject(key, value, schema)
    }
}