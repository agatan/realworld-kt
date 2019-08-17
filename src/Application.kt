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
import org.kodein.di.*
import org.kodein.di.generic.*

fun main(args: Array<String>) {
  embeddedServer(Netty, commandLineEnvironment(args)).start(true)
}

@Suppress("unused")
fun Application.main() {
  Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
  installPlugins()

  val kodein = (Kodein {
    bind<UserRepository>() with singleton { UserRepositoryImpl() }
  })
  val usersService by kodein.newInstance { UsersService(instance()) }
  usersRoute(usersService)
}

fun Application.installPlugins() {
  install(DefaultHeaders)
  install(Locations)
  install(ContentNegotiation) {
    jackson {
    }
  }
}
