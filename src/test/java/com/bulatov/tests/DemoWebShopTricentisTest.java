package com.bulatov.tests;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Cookie;


import static com.bulatov.specs.LoginSpecs.loginRequestSpec;
import static com.bulatov.specs.LoginSpecs.responseSpecification;
import static com.bulatov.tests.TestData.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTricentisTest {

    @Test
    @DisplayName("Проверка добавления в корзину ноутбука")
    void addLaptopToCertTest() {

        step("Вводим значение куки, отправляем запрос POST  на добавление ноутбуку," +
                " проверяем его добалвение в корзину", () -> {
            given(loginRequestSpec)
                    .cookie("NOPCOMMERCE.AUTH", cookieValue)
                    .when()
                    .post("/addproducttocart/catalog/31/1/1")
                    .then()
                    .spec(responseSpecification(200))
                    .body("success", is(true))
                    .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
        });
    }

    @Test
    @DisplayName("Проверка измененния значения в  поле LastName ")
    void changeLastNameUserTest() {
        step("Отправляем запрос на изменение поля LastName", () -> {
            given(loginRequestSpec)
                    .cookie("NOPCOMMERCE.AUTH", receiptCookie())
                    .cookie("__RequestVerificationToken", requestTokenHeader)
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("FirstName", "petya")
                    .formParam("LastName", "petrov")
                    .formParam("Gender", "M")
                    .formParam("Email", "tonali3852@crtsec.com")
                    .formParam("__RequestVerificationToken", requestTokenBody)
                    .when()
                    .post("/customer/info")
                    .then()
                    .spec(responseSpecification(302))
                    .extract().response();
        });

        step("Открываем страницу с авторизацией кукой", () -> {
            open("https://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", receiptCookie()));
        });

        step("Открываем страницу на вкладке Customer info", () -> {
            open("https://demowebshop.tricentis.com/customer/info");
        });

        step("Проверяем что значение поля LastName соотвествует введенному значению", () -> {
            $("#LastName").shouldHave(Condition.value("petrov"));
        });
    }
}

