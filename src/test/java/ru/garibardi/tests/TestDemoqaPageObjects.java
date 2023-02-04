package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeOptions;
import ru.garibardi.pages.PageObjectsTestDemoqa;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testDemoga() throws IOException {

        pageObjectsTestDemoqa.openPage()
                .setFirstName("Igor")
                .setLastName("Trubikhov")
                .setEmail("garibardi@mail.ru")
                .setGender("Male")
                .setNumber("8952381104")
                .setBirthDate("25", "April", "1991")
                .setSubjects("ma", "Maths")
                .setHobbies("Sports")
                .setPicture("files/IMG_20211118_183446.jpg")
                .setAddress("2-y Karavannay 15-4")
                .setState("Haryana")
                .setCity("Karnal")
                .setSubmit()
                .checkResultsTableVisible()
                .checkResult("Student Name", "Igor Trubikhov")
                .checkResult("Student Email", "garibardi@mail.ru")
                .checkResult("Date of Birth", "25 April,1991");

        var screenshot = $(".table-responsive").screenshot();
//        var filename = UUID.randomUUID().toString();
//        var targetFile = new File("src/test/resources/files/" + filename + ".jpg");
//        FileUtils.copyFile(screenshot, targetFile);

        var isEqual = FileUtils.contentEquals(screenshot, new File("src/test/resources/files/template.jpg"));
        assertTrue(isEqual);
    }

    @Test
    void testDemogaMinimum() {
        pageObjectsTestDemoqa.openPage()
                .setFirstName("Igor")
                .setLastName("Trubikhov")
                .setGender("Male")
                .setNumber("8952381104")
                .setSubmit();

        pageObjectsTestDemoqa.checkResultsTableVisible()
                .checkResult("Student Name", "Igor Trubikhov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "8952381104");
    }
}

