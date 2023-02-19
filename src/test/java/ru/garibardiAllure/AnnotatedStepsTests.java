package ru.garibardiAllure;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

public class AnnotatedStepsTests {

    private static final String REPOSITORY = "electrichestvo/qa_guru_15_5";
    private static final String TAB = "Issues";

    @Test
    public void testAnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage()
                .searchRepository(REPOSITORY).
                goToRepository(REPOSITORY).
                checkTab(TAB);
    }
}
