package org.quicache.engine.actions

import org.quicache.engine.store.QuicacheController

class SetAction(
    private val key: String,
    private val value: ByteArray,
    private val schema: String?
): IAction {
    override fun run(): ByteArray? {
        QuicacheController.addOnContext(key, value, schema)
        return null
    }
}