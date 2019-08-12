package dev.agatan.interfaces

import dev.agatan.domain.User

data class User(val id: Int, val name: String) {
  constructor(entity: User) : this(entity.id.toInt(), entity.name)
}

data class CreateUserRequest(val name: String)
