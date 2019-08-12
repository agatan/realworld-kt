package dev.agatan.domain

data class UserId(val id: Int) {
  fun toInt(): Int = id
}

data class User(val id: UserId, val name: String)
