package dev.agatan.services

import dev.agatan.domain.*

interface UserRepository {
  fun getUsers(): List<User>
  fun getUser(id: UserId): User?
  fun createUser(name: String, email: String): User
}

class UsersService(private val userRepository: UserRepository) {
  fun getUsers(): List<User> = userRepository.getUsers()

  fun createUser(name: String, email: String): User = userRepository.createUser(name, email)

  fun getUser(id: UserId): User? = userRepository.getUser(id)
}
