package br.eng.r2a.quicache.engine.actions

import br.eng.r2a.quicache.engine.store.QuicacheContext

class FlushAction: IAction {
    override fun run(): ByteArray? {
        //TODO change flush mode, checking the thread and erase all ttl schedules
        QuicacheContext.unsafeResetContext()
        return null
    }
}