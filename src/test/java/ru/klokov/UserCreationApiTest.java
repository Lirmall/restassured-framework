package ru.klokov;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Управление пользователями")
@Feature("Создание пользователей")
public class UserCreationApiTest {
    private final UserApiClient userApi = new UserApiClient();

    @Test
    @Description("Проверка создания пользователя и извлечения его имени")
    void createAndExtractUser() {
        Response response = userApi.createUser("Andrey Klokov", "SDET");

        response.then().statusCode(201);

        String userName = response.jsonPath().getString("name");
        assertEquals("Andrey Klokov", userName);
    }
}
