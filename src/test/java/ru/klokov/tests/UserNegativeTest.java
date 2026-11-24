package ru.klokov.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import ru.klokov.utils.UriStorage;
import ru.klokov.utils.UserApiClient;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Epic("Получение пользователей")
@Feature("Получение пользователя с различными параметрами (негативные сценарии)")
public class UserNegativeTest {
    private final UserApiClient userApi = new UserApiClient();
    private final String BASE_URL = UriStorage.getJsonPlaceholderUri();

    @Test
    @Description("Проверка получения несуществующего пользователя (404 Not Found)")
    void getUserNotFound() {
        userApi.getUserById(9999, BASE_URL)
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body("isEmpty()", is(true));
    }

    @Test
    @Description("Проверка обработки запроса с пустым JSON-объектом")
    void createUserWithEmptyJsonObject() {
        given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body("{}") // Пустой JSON
                .when()
                .post("/users")
                .then()
                .statusCode(201); // JSONPlaceholder может вернуть 201 даже на пустое тело (он не строгий)
    }
}
