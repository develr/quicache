package org.quicache.engine

import org.quicache.engine.actions.GetAction
import org.quicache.engine.actions.IAction
import org.quicache.engine.actions.Parameters
import org.quicache.engine.actions.SetAction
import org.quicache.engine.config.QuicacheDefaultConfig
import java.io.OutputStream
import org.quicache.engine.extension.getIndexOrElse
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread


object QuicacheRunner {
    fun isTTT(): Boolean {
        return true
    }

    @JvmStatic fun main(args: Array<String>) {
        val server = ServerSocket(QuicacheDefaultConfig.defaultPort)
        println("Server running on port ${server.localPort}")

        do {
            val client = server.accept()
            println("Client connected: ${client.inetAddress.hostAddress}")
            thread { ClientHandler(client).run() }
        } while (isTTT())
    }
}

class ClientHandler(client: Socket) {
    private val client: Socket = client
    private val reader: Scanner = Scanner(client.getInputStream())
    private val writer: OutputStream = client.getOutputStream()
    private var running: Boolean = false

    fun run() {
        running = true
        while (running) {
            try {
                val paramArray = reader.nextLine().split(" ")
                var actionRunner: IAction? = null
                when (paramArray[0]) {
                    Parameters.SET.paramVerb -> {
                        val insertValue = paramArray[2]
                        val schema = paramArray.getIndexOrElse(3, null)
                        actionRunner = SetAction(paramArray[1], insertValue.toByteArray(), schema)
                    }
                    Parameters.GET.paramVerb -> {
                        val keyToFetch = paramArray[1]
                        val schema = paramArray.getIndexOrElse(2, null)
                        actionRunner = GetAction(keyToFetch, schema)
                    }
                }

                write(String(actionRunner?.run() ?: byteArrayOf()))
                shutdown()
            } catch (ex: Exception) {
                println("Error on insert data at engine")
                shutdown()
            }

        }
    }



    private fun write(message: String) {
        writer.write((message).toByteArray(Charset.defaultCharset()))
    }

    private fun shutdown() {
        running = false
        client.close()
    }

}
