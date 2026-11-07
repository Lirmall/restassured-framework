# RestAssured Framework

Учебный проект по созданию фреймворка для автоматизации тестирования API на Java.

[![Allure Report](https://img.shields.io/badge/Allure-Report-blue)](https://Lirmall.github.io/restassured-framework/)

##  Стек технологий

*   **Java 17**
*   **Maven**
*   **RestAssured** (тестирование API)
*   **JUnit 5** (фреймворк для тестов)
*   **Allure** (отчетность)
*   **GitHub Actions** (CI/CD)

##  Как запустить

1.  Клонируйте репозиторий:
    ```bash
    git clone https://github.com/Lirmall/restassured-framework.git
    ```
    
2. Перейдите в папку проекта
    ```bash
    cd restassured-framework
   ```
3. Запустите тесты
    ```bash
    mvn clean test
    ```
   
4. Сгенерируйте и откройте Allure-отчет:
    ```bash
    mvn allure:serve
   ```
## Отчетность
При каждом пуше в ветку main запускается GitHub Actions, который прогоняет тесты и публикует свежий Allure-отчет на GitHub Pages.
Вы можете посмотреть последний отчет по ссылке в бейдже выше.
