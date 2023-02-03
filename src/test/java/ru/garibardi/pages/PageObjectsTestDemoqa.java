package ru.garibardi.pages;

import com.codeborne.selenide.SelenideElement;
import ru.garibardi.pages.components.CalendarComponent;
import ru.garibardi.pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageObjectsTestDemoqa {

    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

    private SelenideElement
            firsNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emeilInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            numberInput = $("#userNumber");

    private final static String TITLE_TEXT = "Student Registration Form";

    public PageObjectsTestDemoqa openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public PageObjectsTestDemoqa setFirstName(String value) {
        firsNameInput.setValue(value);

        return this;
    }
    public PageObjectsTestDemoqa clearFirstName() {
        firsNameInput.clear();

        return this;
    }

    public PageObjectsTestDemoqa setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PageObjectsTestDemoqa setEmail(String value) {
        emeilInput.setValue(value);

        return this;
    }

    public PageObjectsTestDemoqa setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    public PageObjectsTestDemoqa setNumber(String value) {
        numberInput.setValue(value);

        return this;
    }

    public PageObjectsTestDemoqa setBirthDate(String day,String month,String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public PageObjectsTestDemoqa checkResultsTableVisible() {
        resultsTableComponent.checkVisible();

        return this;
    }

    public PageObjectsTestDemoqa checkResult(String key, String value) {
        resultsTableComponent.checkResult(key,value);

        return this;
    }
}
