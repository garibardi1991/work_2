package ru.garibardiAllure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.garibardi.helpers.Attach;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ThreadQa {

    @BeforeAll
    static void configure() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";


    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Tag("testThreadQA")
    @Feature("Проверка сайта ThreadQA")
    @Story("Проверяем раздел Elements")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "http://85.192.34.140:8081/")
    @DisplayName("Проверка работы раздела Check Box")
    void testthreadQA() {
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
