package br.eng.r2a.quicache.engine

import br.eng.r2a.quicache.engine.store.QuicacheController
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket
import java.nio.charset.Charset
import java.util.*
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    val server = ServerSocket(Constants.defaultPort)
    println("Server running on port ${server.localPort}")

    while (true) {
        val client = server.accept()
        println("Client connected: ${client.inetAddress.hostAddress}")
        thread { ClientHandler(client).run() }
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
                val text = reader.nextLine()
                if (text == "set"){
                    QuicacheController.addOnContext(
                        "teste",
                        "valor em bytes".toByteArray()
                    )
                    shutdown()
                    continue
                } else {
                    val result = QuicacheController.getOnContext(
                        "teste"
                    )
                    result?.let { write(String(result)) }

                }

            } catch (ex: Exception) {
                println("Error on insert data at engine")
                shutdown()
            }

        }
    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun shutdown() {
        running = false
        client.close()
        println("${client.inetAddress.hostAddress} closed the connection")
    }

}
