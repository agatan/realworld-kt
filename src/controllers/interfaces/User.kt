package dev.agatan.interfaces

data class User(val id: Int, val name: String)

data class CreateUserRequest(val name: String)
