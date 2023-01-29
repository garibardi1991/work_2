package ru.garibardi;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoqa {

    @BeforeAll
    static void configure () {
        Configuration.browser = EDGE;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }


    @Test
    void testDemoga () {
        open("/automation-practice-form");
        $("#firstName").setValue ("Igor");
        $("#lastName").setValue ("Trubikhov");
        $("#userEmail").setValue ("garibardi@mail.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#genterWrapper").$(byText("Female")).click();
        $("#genterWrapper").$(byText("Other")).click();
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue ("+79523811047");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("April");
        $(".react-datepicker__year-select").selectOptionContainingText("1991");
        $(".react-datepicker__day--025").click();
        $("#subjectsInput").setValue("Volgograd").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();

        $("#uploadPicture").uploadFromClasspath("1.jpeg");

        $("#currentAddress").setValue("2-y Karavannay 15-4");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();

        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

    }
}

