package ru.garibardi.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.util.stream.Stream;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
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


    }
    static Stream<Arguments> openEshopRzdCheckForm(){
        File file = new File("src/test/resources/files/IMG_20211118_183446.jpg");
        return Stream.of(
                Arguments.of("Прочие вопросы", "Игорь", "garibardi@list.ru", "Hello", file, "Заполните все обязательные поля." ),
                Arguments.of("Техническая поддержка, изменение реквизитов и пр.", "Вася", "garibardi@mail.ru", "Good job",file, "Заполните все обязательные поля.")
        );
    }

    @MethodSource
    @ParameterizedTest
    void openEshopRzdCheckForm(String subject, String name, String email, String text, File file, String message){
        open("https://eshoprzd.ru/contacts");
        $("#theme").click();
        $(byText(subject)).click();
        $("#name").setValue(name);
        $("#email").setValue(email);
        $("#text").setValue(text);
        $("[ng-click='it.showAttachBlock()']").click();
        $(".attachments__file input[type='file']").uploadFile(file);
        $("[ng-click='sendFeedback(content)']").click();
        $(".alert-danger").shouldHave(Condition.text(message));


    }
}
