package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestThreadQA {


    @BeforeAll
    static void configure() {
        Configuration.browser = CHROME;
        Configuration.holdBrowserOpen = true;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void testThreadQA() {
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

    @Test
    public void downloadFile() throws IOException {
        open("http://85.192.34.140:8081/");
        $(".card-body").click();
        $("#item-7").click();
        var downloadedFile = $("#downloadButton").download();
        var etalon = new File("src/test/resources/files/sticker.png");
        var isEqual = FileUtils.contentEquals(downloadedFile, etalon);

        assertTrue(isEqual);

    }
}

