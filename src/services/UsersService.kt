package dev.agatan.services

import dev.agatan.domain.*

class UsersService {
  private val usersStore = mutableListOf<User>()

  fun getUsers(): List<User> = usersStore.toList()
  fun createUser(name: String): User {
    val newUser = User(UserId(usersStore.size), name)
    usersStore.add(newUser)
    return newUser
  }
  fun getUser(id: UserId): User? {
    return usersStore.find { it.id == id }
  }
}
