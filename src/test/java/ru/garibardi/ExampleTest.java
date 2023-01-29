package ru.garibardi;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExampleTest {

    @BeforeAll
    static void configure () {
        Configuration.browser = EDGE;
    }

    @AfterAll
    static void terDown() {
        Configuration.browser = EDGE;
    }

    @Test
    void test1() {
        assertTrue(true);
    }

    @Test
    void test2() {
        assertTrue(true);
    }

    @Test
    void test3() {
        assertTrue(true);
    }
    @Test
    void openeshop() {
        open("https://eshoprzd.ru/home");
        $("#login-btn").click();
        $("[ng-click*='showLoginForm']").should(appear);
        sleep(3000);
    }
}
