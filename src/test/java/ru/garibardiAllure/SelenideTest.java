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
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class SelenideTest {


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
    @Tag("testselenide")
    @Feature("Проверка сайта Github")
    @Story("Проверяем репозиторий electrichestvo/qa_guru_15_5")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://github.com/")
    @DisplayName("Проверка отображения репозитория")
    public void testSelenide() {
        step("Открываем Github", () ->
        open("https://github.com/"));

        step("Вводим в поле поиска данные", () ->
        $("[name = q]").setValue("electrichestvo/qa_guru_15_5").pressEnter());

        step("Кликаем по наденным данным", () ->
        $(linkText("electrichestvo/qa_guru_15_5")).click());

        step("Проверяем данные на странице", () ->
        $(partialLinkText("Issues")).shouldHave(text("Issues")));
    }
}
