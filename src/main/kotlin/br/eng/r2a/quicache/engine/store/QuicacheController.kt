package br.eng.r2a.quicache.engine.store

import br.eng.r2a.quicache.engine.Constants

internal object QuicacheController {
    /**
     * Insert a value in engine
     */
    fun addOnContext(
        key: String,
        data: ByteArray,
        schema: String = Constants.defaultNamespace,
        ttl: Long = 0
    ) {
        val quicacheObject = QuicacheObject(key, data, schema, ttl)
        QuicacheContext.addToContext(quicacheObject)
    }

    /**
     * Get an byte in engine
     */
    fun getOnContext(
        key: String,
        schema: String = Constants.defaultNamespace
    ): ByteArray? {
        return QuicacheContext.getFromContext(key, schema)?.value
    }
}