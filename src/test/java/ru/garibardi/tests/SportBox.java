package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.edge.EdgeOptions;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SportBox {

    @DisplayName("Домашняя работа по JUnit5")

    @BeforeAll
    static void configure() {
        Configuration.browser = EDGE;
        Configuration.holdBrowserOpen = true;
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.pageLoadStrategy = "eager";
    }

    @ValueSource(strings = {"Liverpool", "Arsenal"})
    @ParameterizedTest(name = "Проверка информации в статьях по наличию заголовка АНГЛИЯ {0}")
    void sportBoxSoccerClub(String soccerClub) {
        open("https://news.sportbox.ru/");
        $("#smart_search").setValue(soccerClub).pressEnter();
        $$("#left-column").find(text("АНГЛИЯ"));

        clearBrowserCookies();
        closeWebDriver();

    }

    @CsvSource(value = {
            "Liverpool| Клопп прогневал футбольного бога словами о России. Нечего было лезть в политику!",

            "Arsenal| Ганский агент объяснил, почему в «Спартаке» не заиграли Овусу-Абейе и Уорис"
    }, delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка информации в первых статьях по поиску {0}")
    void sportBoxLiverpoolArsenal(String soccerClub, String expectedText) {
        open("https://news.sportbox.ru/");
        $("#smart_search").setValue(soccerClub).pressEnter();
        $$(".teazer-list")
                .first()
                .shouldHave(text(expectedText));

        clearBrowserCookies();
        closeWebDriver();

    }

}
