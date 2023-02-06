package ru.garibardi.tests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import ru.garibardi.pages.PageObjectsTestDemoqa;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoqaPageObjects extends TestBaseDemoQa {
    PageObjectsTestDemoqa pageObjectsTestDemoqa = new PageObjectsTestDemoqa();

    @Test
    void testDemoga() throws IOException {

        pageObjectsTestDemoqa.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender("Male")
                .setNumber(phone)
                .setBirthDate(day, mount, year)
                .setSubjects("ma", "Maths")
                .setHobbies("Sports")
                .setPicture("files/IMG_20211118_183446.jpg")
                .setAddress(Address)
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
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender("Male")
                .setNumber(phone)
                .setSubmit();

        pageObjectsTestDemoqa.checkResultsTableVisible()
                .checkResult("Student Name", "Igor Trubikhov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "8952381104");
    }
}

