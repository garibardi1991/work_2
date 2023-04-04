package ru.garibardi.api;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.Date;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.restassured.http.ContentType.JSON;


public class TestAuth {



    @Test
    public void authTestEshop() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("login", "10012206");
        body.put("password", "cgjrjggbc8");

        var response = RestAssured.given()
                .relaxedHTTPSValidation()
                .contentType(JSON)
                .header("type-shop", "rzd")
                .body(body)
                .log().uri().log().body()
                .post("https://eshoprzd.ru/rest/auth/api/user/login")
                .then()
                .log().body()
                .extract()
                .response();

        var token = response.jsonPath().get("result.tokenId").toString();

        open("https://eshoprzd.ru/home");
        Selenide.sessionStorage().setItem("key", token);
        Selenide.refresh();
        $("[title='Общая учетка УТП']").should(appear);


    }

    @Test
    public void stagingFinbookTest() {
        var cookie = RestAssured.given()
                .log().all()
                .multiPart("username","aidaituratbekova02@gmail.com")
                .multiPart("password","Finbook2023!")
                .post("https://staging.finbook.kg/auth/login")
                .then()
                .log().all()
                .extract().response().getCookie("SESSION");

        open("https://staging.finbook.kg");

        Date expdate = new Date();
        expdate.setTime(expdate.getTime() + (10000 * 10000));

        var cookies = new Cookie("SESSION",cookie,"staging.finbook.kg", "/", expdate);
        WebDriverRunner.getWebDriver().manage().addCookie(cookies);

        refresh();

        webdriver().shouldHave(url("https://staging.finbook.kg/analytics"));
        sleep(5000);
    }
}
