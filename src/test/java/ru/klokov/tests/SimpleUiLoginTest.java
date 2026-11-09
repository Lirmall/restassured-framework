package ru.klokov.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.klokov.ui.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleUiLoginTest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Запуск без окна (для CI)
        options.addArguments("--no-sandbox"); // Для GitHub Actions (Linux)
        options.addArguments("--disable-dev-shm-usage"); // Для GitHub Actions (Linux)
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
    }



    @Test
    void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
