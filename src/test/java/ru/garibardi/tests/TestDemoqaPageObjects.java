package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeOptions;
import ru.garibardi.pages.PageObjectsTestDemoqa;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoqaPageObjects {
    PageObjectsTestDemoqa pageObjectsTestDemoqa = new PageObjectsTestDemoqa();

    @BeforeAll
    static void configure() {
        Configuration.browser = EDGE;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.pageLoadStrategy = "eager";
    }


    @Test
    void testDemoga() {
        pageObjectsTestDemoqa.openPage()
                .setFirstName("Igor")
                .setLastName("Trubikhov")
                .setEmail("garibardi@mail.ru")
                .setGender("Male")
                .setNumber("8952381104")
                .setBirthDate("25", "April", "1991")
                .setSubjects("ma","Maths")
                .setHobbies("Sports")
                .setPicture("files/IMG_20211118_183446.jpg")
                .setAddress("2-y Karavannay 15-4")
                .setState("Haryana")
                .setCity("Karnal");

        pageObjectsTestDemoqa.checkResultsTableVisible()
                .checkResult("Student Name", "Igor Trubikhov")
                .checkResult("Student Email", "garibardi@mail.ru")
                .checkResult("Date of Birth", "25 April,1991");

        $(".table-responsive table").shouldHave(text("Igor"), text("Trubikhov"),
                text("8952381104"));
    }

    @Test
    void testDemogaMinimum() {
        pageObjectsTestDemoqa.openPage()
                .setFirstName("Igor")
                .setLastName("Trubikhov")
                .setGender("Male")
                .setNumber("8952381104");

        $("#submit").click();

        $(".modal-dialog").should(appear);
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive table").shouldHave(text("Igor"), text("Trubikhov"),
                text("8952381104"));
    }
}

