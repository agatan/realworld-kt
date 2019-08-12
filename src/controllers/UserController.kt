package dev.agatan.controllers

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*


data class User(val id: Int, val name: String)

fun Application.usersRoute() {
  routing {
    route("/users") {
      get("/") {
        val users = (1 until 10).toList().map { User(it, it.toString())}
        call.respond(mapOf("users" to users))
      }

      @Location("/{userId}")
      data class UserGet(val userId: Int)
      get<UserGet> {
        call.respond(User(1, "me"))
      }
    }
  }
}
