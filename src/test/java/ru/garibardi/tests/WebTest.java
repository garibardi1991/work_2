package ru.garibardi.tests;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.garibardi.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {
    @BeforeAll
    static void configure() {
        Configuration.holdBrowserOpen = true;}


    @ValueSource (strings = {"Selenide", "JUnit"} )
    @ParameterizedTest (name = "Проверка числа результов поисков в Яндексе для запроса {0}")
    void yandexSearchCommonTest(String testData) {
        open("https://www.google.com/");
        $(".gLFyf").setValue(testData);
        $(".gNO89b").click();
        $$("#rso")
                .first()
                .shouldHave(text(testData))
                ;
    }

    @CsvSource(value = {
            "Selenide| Selenide - это фреймворк для автоматизированного тестирования веб-приложений",

            "JUnit| JUnit 5 is the next generation of JUnit"
    }, delimiter = '|'
    )
    @ParameterizedTest (name = "Проверка числа результов поисков в Яндексе для запроса {0}")
    void yandexSearchCommonTestDifferentExpectedText(String searchQuery, String expectedText) {
        open("https://www.google.com/");
        $(".gLFyf").setValue(searchQuery);
        $(".gNO89b").click();
        $$("#rso")
                .first()
                .shouldHave(text(expectedText))
        ;
    }

    static Stream<Arguments> selenideSiteButtonTexts(){
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
                );
    }

    @MethodSource
    @ParameterizedTest
    void selenideSiteButtonTexts(Locale locale, List<String> buttonsTexts ){
        open("https://selenide.org/");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(CollectionCondition.texts(buttonsTexts));

    }
    @EnumSource (Locale.class)
    @ParameterizedTest
    void checkLocaleTest(Locale locale){
        open("https://selenide.org/");
        $$("#languages a").find(text(locale.name())).shouldBe(visible);
    }

}
