package org.quicache.engine.actions

interface IAction {
    fun run(): ByteArray?
}