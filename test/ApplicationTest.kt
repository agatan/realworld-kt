import com.fasterxml.jackson.module.kotlin.*
import dev.agatan.*
import dev.agatan.interfaces.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {
  @Test
  fun testRequests() = withTestApplication(Application::main) {
    handleRequest(HttpMethod.Get, "/users").let {
      assertEquals(HttpStatusCode.OK, it.response.status())
      val body = jacksonObjectMapper().readValue<Map<String, List<User>>>(it.response.content!!)
      assert(body.keys.contains("users"))
    }
  }
}
