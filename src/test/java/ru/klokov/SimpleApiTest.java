package ru.klokov;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SimpleApiTest {
    private static final Logger log = LoggerFactory.getLogger(SimpleApiTest.class);

    @Test
    void checkUserExists() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when().get("/users/1")
                .then().statusCode(200);
    }

    @Test
    void createAndExtractUser() {
        String requestBody = """
            {
              "name": "Andrey Klokov",
              "job": "SDET"
            }
            """;

        io.restassured.response.Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().response();

        String userName = response.jsonPath().getString("name");
        String userId = response.jsonPath().getString("id");
        String createdAt = response.jsonPath().getString("createdAt");

        log.info("Создан пользователь: {}", userName);
        log.info("Его ID: {}", userId);
        log.info("Время создания: {}", createdAt);

        assertEquals("Andrey Klokov", userName);
    }
}
