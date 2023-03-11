package ru.garibardiAllure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.garibardi.helpers.Attach;
import ru.garibardiAllure.properties.Property;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Tag("testThreadQA")
public class ThreadQa {

    @BeforeAll
    static void configure() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.browserCapabilities = capabilities;
        Configuration.browser = Property.browser();
        Configuration.browserVersion = Property.browserVersion();
        Configuration.browserSize = Property.browserSize();
        if (!Property.remoteUrl().equals("")) {
            Configuration.remote = Property.remoteUrl();
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Feature("Проверка сайта ThreadQA")
    @Story("Проверяем раздел Elements")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "http://85.192.34.140:8081/")
    @DisplayName("Проверка работы раздела Check Box")
    void testthreadQAElements() {
        step("Открываем ThreadQA", () ->
        open("http://85.192.34.140:8081/")
        );
        step("Открываем Elements", () ->
                $(byXpath("//h5[contains(text(),'Elements')]")).click()
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

    @Test
    @Feature("Проверка сайта ThreadQA")
    @Story("Проверяем раздел Forms")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "http://85.192.34.140:8081/")
    @DisplayName("Проверка работы раздела Forms")
    void testthreadQAForms() {
        step("Открываем ThreadQA", () ->
                open("http://85.192.34.140:8081/")
        );
        step("Открываем Forms", () ->
                $(byXpath("//h5[contains(text(),'Forms')]")).click()
        );
        step("Открываем Practice Form", () ->
                $(byXpath("//span[contains(text(),'Practice Form')]")).click()
        );
        step("Заполняем Student Registration Form", () -> {
                $("#firstName").setValue("Igor");
                $("#lastName").setValue("Trubikhov");
                $("#genterWrapper").$(byText("Male")).click();
                $("#userNumber").setValue("8952381104");
        });
        step("Нажимаем Submit", () ->
                $("#submit").click()
        );
        step("Проверяем  заполненную форму", () -> {
            $(".modal-header").shouldHave(text("Thanks for submitting the form"));
            $(".table").$(byText("Student Name")).parent().lastChild().shouldHave(text("Igor Trubikhov"));
            $(".table").$(byText("Gender")).parent().lastChild().shouldHave(text("Male"));
            $(".table").$(byText("Mobile")).parent().lastChild().shouldHave(text("8952381104"));
        });
    }
}
