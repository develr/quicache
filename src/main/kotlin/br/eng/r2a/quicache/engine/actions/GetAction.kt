package br.eng.r2a.quicache.engine.actions

import br.eng.r2a.quicache.engine.store.QuicacheController

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