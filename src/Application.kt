package dev.agatan

import dev.agatan.controllers.*
import dev.agatan.services.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.kodein.di.*
import org.kodein.di.generic.*

fun main(args: Array<String>) {
  embeddedServer(Netty, commandLineEnvironment(args)).start(true)
}

@Suppress("unused")
fun Application.main() {
  mainModule(Kodein {
    bind<UsersService>() with singleton { UsersService() }
  })
}

fun Application.installPlugins() {
  install(DefaultHeaders)
  install(Locations)
  install(ContentNegotiation) {
    jackson {
    }
  }
}

fun Application.mainModule(kodein: Kodein) {
  installPlugins()
  val usersService by kodein.instance<UsersService>()
  usersRoute(usersService)
}
