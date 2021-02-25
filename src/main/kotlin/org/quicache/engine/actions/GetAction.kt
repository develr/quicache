package org.quicache.engine.actions

import org.quicache.engine.store.QuicacheController

class GetAction(
    private val keyName: String,
    private val keySchema: String?
    ): IAction {
    override fun run(): ByteArray? {
        return QuicacheController.getOnContext(
            keyName,
            keySchema
        )
    }
}