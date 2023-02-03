package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestThreadQA {

    @BeforeAll
    static void configure () {
        Configuration.browser = CHROME;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void testThreadQA () {
        open("http://85.192.34.140:8081/");
        $(".card-body").click();
        $("#item-1").click();
        $("[aria-label='Expand all']").click();
        $("#tree-node-public").parent().lastChild().click();
        $("#tree-node-workspace").parent().lastChild().click();
        $("#item-2").click();
        $("#yesRadio").parent().lastChild().click();
        $(".mt-3").shouldHave(text("You have selected"));
        $(".text-success").shouldHave(text("Yes"));
        $("#item-2").sibling(0).click();
        $("#item-2").preceding(1).click();
        $("#item-7").click();

    }
}
