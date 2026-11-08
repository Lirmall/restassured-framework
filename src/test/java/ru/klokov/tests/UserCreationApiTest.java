package ru.klokov.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ru.klokov.utils.UserApiClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Управление пользователями")
@Feature("Создание пользователей")
public class UserCreationApiTest {
    private final UserApiClient userApi = new UserApiClient();
    private final Faker faker = new Faker();

    @Test
    @Description("Проверка создания пользователя и извлечения его имени")
    void createAndExtractUser() {
        String name = faker.name().firstName();
        String job = faker.job().title();
        Response response = userApi.createUser(name, job);

        response.then().statusCode(201);

        assertEquals(name, response.jsonPath().getString("name"));
    }
}
