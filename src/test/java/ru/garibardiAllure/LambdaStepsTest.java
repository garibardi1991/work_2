package ru.garibardiAllure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepsTest {

    private static final String REPOSITORY = "electrichestvo/qa_guru_15_5";
    private static final String TAB = "Issues";


    @Test
    @Feature("Issue в репозитории")
    @Story("Ищем репозиторий с Issue")
    @Owner("trubikhoviv")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Проверяем, что есть вкладка с названием Issue")
    public void testLambdaSteps() {

        step("Открываем github", () ->
                open("https://github.com/")
        );

        step("Ищем репозиторий " + REPOSITORY, () ->
                $("[name = q]").setValue(REPOSITORY).pressEnter()
        );

        step("Открываем репозиторий " + REPOSITORY, () ->
                $(linkText(REPOSITORY)).click()
        );

        step("Проверяем, что есть вкладка с названием " + TAB, () ->
                $(partialLinkText(TAB)).shouldHave(text("Issues"))
        );
    }
}

