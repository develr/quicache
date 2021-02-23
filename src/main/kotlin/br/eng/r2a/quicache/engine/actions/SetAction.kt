package br.eng.r2a.quicache.engine.actions

import br.eng.r2a.quicache.engine.store.QuicacheController

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