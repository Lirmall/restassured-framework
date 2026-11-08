package ru.klokov.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Step("Создание пользователя: {name}, должность: {job}")
    public Response createUser(String name, String job) {
        String body = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        return given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/users")
                .then()
                .extract().response();
    }
}
