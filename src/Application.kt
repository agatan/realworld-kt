package dev.agatan

import dev.agatan.controllers.*
import dev.agatan.services.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
  embeddedServer(Netty, commandLineEnvironment(args)).start(true)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
  install(Locations)
  install(ContentNegotiation) {
    jackson {

    }
  }
  val usersService = UsersService()
  usersRoute(usersService)
}

