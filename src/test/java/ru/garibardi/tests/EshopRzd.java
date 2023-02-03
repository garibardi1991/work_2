package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class EshopRzd {

    @BeforeAll
    static void configure () {
        Configuration.browser = CHROME;
    }

    @AfterAll
    static void terDown() {
        Configuration.browser = CHROME;
    }

    @Test
    void openeshop () {
        open("https://eshoprzd.ru/home");
        $("#login-btn").click();
        $("[ng-click*='showLoginForm']").should(appear);
        sleep(3000);
    }
}
