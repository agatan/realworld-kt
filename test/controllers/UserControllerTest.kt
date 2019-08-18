package controllers

import com.fasterxml.jackson.module.kotlin.*
import dev.agatan.*
import dev.agatan.controllers.*
import dev.agatan.domain.*
import dev.agatan.interfaces.*
import dev.agatan.services.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.*
import kotlin.test.*
import dev.agatan.domain.User as DomainUser

class UserControllerTest {
  private val usersService = mockk<UsersService>()

  @Test
  fun testCreateUser() = testApp {
    val name = "foo"
    val email = "bar@example.com"
    every { usersService.createUser(name, email) } returns DomainUser(UserId(0), name, email)
    handleRequest(HttpMethod.Post, "/users") {
      addHeader("Content-Type", ContentType.Application.Json.toString())
      setBody(jacksonObjectMapper().writeValueAsString(CreateUserRequest(name, email)))
    }.apply {
      assertEquals(HttpStatusCode.OK, response.status())
    }
  }

  private fun testApp(callback: TestApplicationEngine.() -> Unit) {
    withTestApplication({
      installPlugins()
      registerUsersRoute(usersService)
    }) { callback() }
  }
}
