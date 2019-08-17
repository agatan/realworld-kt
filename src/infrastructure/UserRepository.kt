package dev.agatan.infrastructure

import dev.agatan.domain.*
import dev.agatan.services.*
import org.jetbrains.exposed.dao.*

object Users : IntIdTable() {
  var name = text("name")
  var email = text("email")
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<UserEntity>(Users)

  var name by Users.name
  var email by Users.email

  fun toDomainUser(): User = User(UserId(id.value), name, email)
}

class UserRepositoryImpl : UserRepository {
  override fun getUsers(): List<User> = UserEntity.all().map { it.toDomainUser() }
  override fun getUser(id: UserId): User? = UserEntity.findById(id.toInt())?.let { it.toDomainUser() }
  override fun createUser(name: String, email: String): User = UserEntity.new {
    this.name = name
    this.email = email
    this.id
  }?.let { it.toDomainUser() }
}
