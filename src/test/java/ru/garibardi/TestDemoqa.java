package ru.garibardi;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoqa {

    @BeforeAll
    static void configure () {
        Configuration.browser = EDGE;
        Configuration.baseUrl = "https://demoqa.com";
    }


    @Test
    void testDemoga () {
        open("/automation-practice-form");
        $("#firstName").setValue ("Igor");
        $("#lastName").setValue ("Trubikhov");
        $("#userEmail").setValue ("garibardi@mail.ru");
        $("#gender-radio-1").click();
        $("#gender-radio-2").click();
        $("#gender-radio-3").click();
        $("#userNumber").setValue ("+79523811047");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click ();
        $(".react-datepicker__month-select").selectOptionContainingText("3");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOptionContainingText("1991");
        $(".react-datepicker__day react-datepicker__day--025").click();
        $(".subjects-auto-complete__placeholder css-1wa3eu0-placeholder").setValue("Volgograd");
        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-2").click();
        $("#hobbies-checkbox-3").click();


    }
}

