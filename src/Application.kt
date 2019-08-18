package dev.agatan

import dev.agatan.controllers.*
import dev.agatan.infrastructure.*
import dev.agatan.services.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.locations.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.*
import org.kodein.di.generic.*
import org.kodein.di.ktor.*

fun main(args: Array<String>) {
  embeddedServer(Netty, commandLineEnvironment(args)).start(true)
}

@Suppress("unused")
fun Application.main() {
  Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

  install(DefaultHeaders)
  install(Locations)
  install(ContentNegotiation) {
    jackson()
  }

  kodein {
    bind<UserRepository>() with singleton { UserRepositoryImpl() }
    bind<UsersService>() with singleton { UsersService(instance()) }
  }

  registerUsersRoute(kodein())
}
