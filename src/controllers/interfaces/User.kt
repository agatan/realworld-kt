package dev.agatan.interfaces

import dev.agatan.domain.User

data class User(val id: Int, val name: String, val email: String) {
  constructor(entity: User) : this(entity.id.toInt(), entity.name, entity.email)
}

data class CreateUserRequest(val name: String)
