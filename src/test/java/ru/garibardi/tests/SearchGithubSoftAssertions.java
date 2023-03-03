package ru.garibardi.tests;

import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchGithubSoftAssertions {



    @Test
    void SearchJUnit5 () {
        open("https://github.com/");
        $("[data-scoped-placeholder=Search]").setValue("selenide").pressEnter();
        $$(".repo-list li").first().$("a").click();
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(byText("SoftAssertions")).click();
        $(".gollum-markdown-content").shouldHave(text("Using JUnit5 extend test class:"));
    }

    @Test
    void SearchSelenide () {
        open("https://github.com/selenide/selenide");
        $(".Layout-sidebar").$(byText("Contributors"))
                .ancestor(".BorderGrid-cell").$$("ul li")
                .first().hover();

        $$(".Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));


    }

    @Test
    void SearchEnterprise () {
        open("https://github.com/");
        $(withText("Solutions")).hover();
        $(withText("Enterprise")).click();
        $("h1").shouldHave(text("Build like the best"));

    }


}
