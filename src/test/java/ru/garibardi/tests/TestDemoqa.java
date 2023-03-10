package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.garibardi.helpers.Attach;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoqa {

    @BeforeAll
    static void configure() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
//        Configuration.browser = EDGE;
        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.holdBrowserOpen = true;
//        Configuration.browserSize = null;
//        Configuration.pageLoadStrategy = "eager";

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }


    @Test
    @Tag("testDemoqa")
    void testDemoga() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        $("#firstName").setValue("Igor");
        $("#lastName").setValue("Trubikhov");
        $("#userEmail").setValue("garibardi@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#genterWrapper").$(byText("Female")).click();
        $("#genterWrapper").$(byText("Other")).click();
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("8952381104");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__day--025:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("ma");
        $(byText("Maths")).click();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("files/IMG_20211118_183446.jpg");
        $("#currentAddress").setValue("2-y Karavannay 15-4");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();


        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table").$(byText("Student Name")).parent().lastChild().shouldHave(text("Igor Trubikhov"));
        $(".table").$(byText("Student Email")).parent().lastChild().shouldHave(text("garibardi@mail.ru"));
        $(".table").$(byText("Gender")).parent().lastChild().shouldHave(text("Male"));
        $(".table").$(byText("Mobile")).parent().lastChild().shouldHave(text("8952381104"));
        $(".table").$(byText("Date of Birth")).parent().lastChild().shouldHave(text("25 April,1991"));
        $(".table").$(byText("Subjects")).parent().lastChild().shouldHave(text("Maths"));
        $(".table").$(byText("Hobbies")).parent().lastChild().shouldHave(text("Sports"));
        $(".table").$(byText("Picture")).parent().lastChild().shouldHave(text("IMG_20211118_183446.jpg"));
        $(".table").$(byText("Address")).parent().lastChild().shouldHave(text("2-y Karavannay 15-4"));
        $(".table").$(byText("State and City")).parent().lastChild().shouldHave(text("Haryana Karnal"));
    }

}

