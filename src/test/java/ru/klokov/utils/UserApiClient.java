package ru.klokov.utils;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiClient {
    private static final String BASE_URL = UriStorage.getJsonPlaceholderUri();

    @Step("Создание пользователя: {name}, должность: {job}")
    public Response createUser(String name, String job, String uri) {
        String body = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        return given()
                .baseUri(uri)
                .contentType("application/json")
                .body(body)
                .log().all()
                .when()
                .post("/users")
                .then()
                .log().all()
                .extract().response();
    }

    @Step("Получение пользователя по id: {id}")
    public Response getUserById(int id, String uri) {
        return given()
                .baseUri(uri)
                .log().all()
                .when()
                .get("/users/" + id)
                .then()
                .log().all()
                .extract().response();
    }
}
