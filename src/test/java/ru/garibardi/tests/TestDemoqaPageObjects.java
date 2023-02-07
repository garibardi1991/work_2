package ru.garibardi.tests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import ru.garibardi.pages.PageObjectsTestDemoqa;
import ru.garibardi.utils.FormDataGenerator;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoqaPageObjects extends TestBaseDemoQa {
    PageObjectsTestDemoqa pageObjectsTestDemoqa = new PageObjectsTestDemoqa();


    @Test
    void testDemoga() throws IOException {
        var model = FormDataGenerator.formDateContainer();

        pageObjectsTestDemoqa.openPage()
                .setFirstName(model.getFirstName())
                .setLastName(model.getLastName())
                .setEmail(model.getEmail())
                .setGender("Male")
                .setNumber(model.getPhone())
                .setBirthDate(model.getDay(), model.getMonth(), model.getYear())
                .setSubjects("ma", "Maths")
                .setHobbies("Sports")
                .setPicture("files/IMG_20211118_183446.jpg")
                .setAddress(model.getAddress())
                .setState("Haryana")
                .setCity("Karnal")
                .setSubmit()
                .checkResultsTableVisible()
                .checkResult("Student Name", model.getFirstName() + " " + model.getLastName())
                .checkResult("Student Email", model.getEmail())
                .checkResult("Date of Birth", model.getDay() + " " + model.getMonth() + "," + model.getYear());

        var screenshot = $(".table-responsive").screenshot();
//        var filename = UUID.randomUUID().toString();
//        var targetFile = new File("src/test/resources/files/" + filename + ".jpg");
//        FileUtils.copyFile(screenshot, targetFile);

        var isEqual = FileUtils.contentEquals(screenshot, new File("src/test/resources/files/template.jpg"));
        assertTrue(isEqual);
    }

    @Test
    void testDemogaMinimum() {
        var model = FormDataGenerator.formDateContainer();
        pageObjectsTestDemoqa.openPage()
                .setFirstName(model.getFirstName())
                .setLastName(model.getLastName())
                .setGender("Male")
                .setNumber(model.getPhone())
                .setSubmit();

        pageObjectsTestDemoqa.checkResultsTableVisible()
                .checkResult("Student Name", "Igor Trubikhov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "8952381104");
    }
}

