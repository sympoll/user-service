package com.MTAPizza.Sympoll.usermanagementservice;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserManagementServiceApplicationTests {

	private static final Gson gson;

	/**
	 * Initialize postgres test container with the init script inside poll-management-service/test/resources
	 * */
	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgreSQLContainer =
			new PostgreSQLContainer<>("postgres:16.2").withInitScript("init.sql");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		postgreSQLContainer.start(); //  Run mock test container.
		gson = new Gson();
	}

	/**
	 * Send request to create the specified user.
	 */
	@Test
	@Order(1)
	void shouldCreateNewUser() {
		String requestBodyUsr1 = """
                {
                  "username": "MTAPizza",
                  "password": "2334445555",
                  "email": "MTAPizza@gmail.com"
                }
                """;

		UserResponse user1Response = tryToCreateUserAndAssertStatusCode(requestBodyUsr1, HttpStatus.CREATED).as(UserResponse.class);

		/* Verify user response */
		assertNotNull(user1Response.userID(), "User ID should not be null"); // Verify ID
		assertEquals("MTAPizza@gmail.com", user1Response.email()); // Verify email
	}

	/**
	 * Send a request to the user service to create a user with the given properties in the request body.
	 * The expectedStatus is the status that the request is expected to return based on the body provided.
	 * Returns the service's response.
	 */
	Response tryToCreateUserAndAssertStatusCode(String requestBody, HttpStatus expectedStatus){
		// Check that response is in fact 201
		return RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/user")
				.then()
				.statusCode(expectedStatus.value())
				.extract().response();
	}

}
