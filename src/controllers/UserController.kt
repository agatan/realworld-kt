package dev.agatan.controllers

import dev.agatan.domain.*
import dev.agatan.interfaces.*
import dev.agatan.interfaces.User
import dev.agatan.services.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.usersRoute(usersService: UsersService) {
  routing {
    route("/users") {
      get("/") {
        val users = usersService.getUsers()
        val usersData = users.map { User(it) }
        call.respond(mapOf("users" to usersData))
      }

      @Location("/{userId}")
      data class UserGet(val userId: Int)
      get<UserGet> { req ->
        val userId = UserId(req.userId)
        val user = usersService.getUser(userId)
        if (user == null) {
          call.respond(HttpStatusCode.NotFound)
        } else {
          call.respond(User(user.id.toInt(), user.name))
        }
      }

      post("/") {
        val request = call.receive<CreateUserRequest>()
        val user = usersService.createUser(request.name)
        call.respond(User(user))
      }
    }
  }
}
