package net.abetar0

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.routing
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

data class Snippet(val text: String)

val snippets = Collections.synchronizedList(mutableListOf(
    Snippet("Hello"),
    Snippet("World!")
))
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {

        }
    }
    routing {
        get("/snippets") {
            //call.respondText("OK")
            call.respond(mapOf("snippets" to synchronized(snippets) { snippets.toList() }))
        }
    }
}

