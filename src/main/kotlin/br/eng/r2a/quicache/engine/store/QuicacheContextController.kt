package br.eng.r2a.quicache.engine.store

import br.eng.r2a.quicache.engine.Constants
import br.eng.r2a.quicache.engine.exception.InvalidQuicacheValueException

internal object QuicacheContextController {

    fun addOnContext(
        key: String,
        data: Byte,
        schema: String = Constants.defaultNamespace,
        ttl: Long = 0
    ) {
        val quicacheObject = QuicacheObject(key, data, schema, ttl)
        QuicacheContext.addToContext(quicacheObject)
    }
}