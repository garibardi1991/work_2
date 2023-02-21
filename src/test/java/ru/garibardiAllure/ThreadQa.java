package ru.garibardiAllure;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ThreadQa {

    @BeforeAll
    static void configure() {
        Configuration.browser = CHROME;
        Configuration.headless = true;
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("start-maximized");
//        Configuration.browserCapabilities = options;
//        Configuration.browserSize = null;
//        Configuration.pageLoadStrategy = "eager";
//        Configuration.fileDownload = FileDownloadMode.PROXY;
    }

    @Test
    @Tag("threadqa")
    @Feature("Проверка сайта ThreadQA")
    @Story("Проверяем раздел Elements")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "http://85.192.34.140:8081/")
    @DisplayName("Проверка работы раздела Check Box")
    void testThreadQA() {
        step("Открываем ThreadQA", () ->
        open("http://85.192.34.140:8081/")
        );
        step("Открываем Elements", () ->
                $(".card-body").click()
        );
        step("Открываем Check Box", () ->
                $("#item-1").click()
        );
        step("Выбираем +", () ->
                $("[aria-label='Expand all']").click()
        );
        step("Выбираем public", () ->
                $("#tree-node-public").parent().lastChild().click()
        );
        step("Выбираем workspace", () ->
                $("#tree-node-workspace").parent().lastChild().click()
        );
        step("Проверяем что на странице есть workspace", () ->
                $("#result").shouldHave(text("workspace"))
        );
    }
}
