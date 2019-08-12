package dev.agatan.controllers

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.usersRoute() {
  routing {
    route("/users") {
      get("/") {
        call.respondText("list all users", contentType = ContentType.Text.Plain)
      }
    }
  }
}
