package ru.garibardi;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeOptions;

import static com.codeborne.selenide.Browsers.EDGE;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestThreadQA {

    @BeforeAll
    static void configure () {
        Configuration.browser = EDGE;
        Configuration.holdBrowserOpen = true;
        EdgeOptions options = new EdgeOptions();
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
    }
}

