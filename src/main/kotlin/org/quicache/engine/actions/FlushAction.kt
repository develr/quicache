package org.quicache.engine.actions

import org.quicache.engine.store.QuicacheContext

class FlushAction: IAction {
    override fun run(): ByteArray? {
        //TODO change flush mode, checking the thread and erase all ttl schedules
        QuicacheContext.unsafeResetContext()
        return null
    }
}