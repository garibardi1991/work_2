package ru.garibardiAllure;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    @Step("Открываем github")
    public WebSteps openMainPage() {
        open("https://github.com/");
        return this;
    }

    @Step("Ищем репозиторий [{repository}]")
    public WebSteps searchRepository(String repository) {
        $("[name = q]").setValue(repository).pressEnter();
        return this;
    }

    @Step("Ищем репозиторий [{repository}]")
    public WebSteps goToRepository(String repository) {
        $(linkText(repository)).click();
        return this;
    }

    @Step("Проверяем, что есть вкладка с названием [{tab}]")
    public WebSteps checkTab (String tab) {
        $(partialLinkText(tab)).shouldHave(text("Issues"));
        return this;
    }

}
