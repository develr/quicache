package org.quicache.engine.store

import java.util.*
import java.util.function.Predicate
import kotlin.concurrent.timerTask

/**
 * Quicache general context
 *
 * @author Ari
 */
internal object QuicacheContext {
    private val currentContext = mutableListOf<QuicacheObject>()

    fun addToContext(quicacheObject: QuicacheObject) {
        currentContext.add(quicacheObject)
    }

    fun getFromContext(key: String, context: String?): QuicacheObject? {
        return currentContext.firstOrNull { current -> current.key == key && current.schema == context }
    }

    fun unsafeResetContext() {
        currentContext.removeIf { true }
    }

    fun scheduleWithTTL(quicacheObject: QuicacheObject) {
        if(quicacheObject.ttl > 0) return;
        val condition = Predicate { current: QuicacheObject -> current.key == quicacheObject.key }
        Timer(quicacheObject.key, true).
            schedule(
                timerTask {
                    currentContext.removeIf(condition)
                },
                quicacheObject.ttl
            )
    }
}