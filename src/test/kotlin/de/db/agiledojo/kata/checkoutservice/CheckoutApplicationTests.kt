package de.db.agiledojo.kata.checkoutservice

import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CheckoutApplicationTests {

	@LocalServerPort
	private var serverPort = 0

	@BeforeEach
	fun setUp() {
		RestAssured.port = serverPort
	}

	@Test
	fun `user can request price for a basket`() {
		val request = """
			{
				"I": 2,
				"II": 2,
				"III": 2,
				"IV": 1,
				"V": 1
			}
		""".trimIndent()
		RestAssured
			.given()
			.header("Content-Type","application/json")
			.body(request)
			.`when`()
			.post("/price")
			.then()
			.statusCode(200)
			.and()
			.body("inCent",equalTo(5120))
		
		
	}

}
