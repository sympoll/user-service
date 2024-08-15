package com.MTAPizza.Sympoll.usermanagementservice;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import com.MTAPizza.Sympoll.usermanagementservice.dto.user.id.UserIdExistsResponse;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserManagementServiceApplicationTests {

	private static UUID firstUserId;
	private static UUID secondUserId;
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

		String requestBodyUsr2 = """
                {
                  "username": "SirFeliX",
                  "password": "m1e2o3w4",
                  "email": "iHuntParrots3@gmail.com"
                }
                """;

		UserResponse user2Response = tryToCreateUserAndAssertStatusCode(requestBodyUsr2, HttpStatus.CREATED).as(UserResponse.class);

		/* Verify user response */
		assertNotNull(user2Response.userID(), "User ID should not be null"); // Verify ID
		assertEquals("iHuntParrots3@gmail.com", user2Response.email()); // Verify email
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

	@Test
	@Order(2)
	void shouldGetAllUsers(){
		// Check that response is in fact 200
		Response response = RestAssured.given()
				.contentType("application/json")
				.when()
				.get("/api/user/all")
				.then()
				.statusCode(200)
				.extract().response();

		List<UserResponse> userResponses = response.as(new TypeRef<>() {});

		/* Verify user response */
		assertEquals(2, userResponses.size(), "Expected 2 users in the response");
		firstUserId = userResponses.get(0).userID();
		secondUserId = userResponses.get(1).userID();
	}

	@Test
	@Order(3)
	void shouldGetUserById() {
		// Check that response is in fact 200
		Response response = RestAssured.given()
				.queryParam("userId", firstUserId.toString())
				.contentType("application/json")
				.when()
				.get("/api/user/by-user-id")
				.then()
				.statusCode(200)
				.extract().response();

		UserResponse userResponse = response.as(UserResponse.class);

		/* Verify user response */
		assertNotNull(userResponse.userID(), "User ID should not be null");
		assertEquals(firstUserId, userResponse.userID());
		assertEquals("MTAPizza@gmail.com", userResponse.email());
	}

	@Test
	@Order(4)
	void shouldDeleteUserById(){
		// Check that response is in fact 200
		Response response = RestAssured.given()
				.queryParam("userId", firstUserId.toString())
				.contentType("application/json")
				.when()
				.delete("/api/user/by-user-id")
				.then()
				.statusCode(200)
				.extract().response();

		UUID uuidResponse = response.as(UUID.class);

		/* Verify uuid response */
		assertEquals(firstUserId, uuidResponse);
	}

	@Test
	@Order(5)
	void shouldUserIdExists() {
		String requestBody = String.format("""
                {
                  "userId": "%s"
                }
                """, secondUserId);

		// Check that response is in fact 200
		Response response = RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.get("/api/user/id")
				.then()
				.statusCode(200)
				.extract().response();

		UserIdExistsResponse userIdExistsResponse = response.as(UserIdExistsResponse.class);

		/* Verify user id exists response */
        assertTrue(userIdExistsResponse.isExists());
	}


}
