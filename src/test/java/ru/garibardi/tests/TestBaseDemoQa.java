package ru.garibardi.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.edge.EdgeOptions;

import static com.codeborne.selenide.Browsers.EDGE;

public class TestBaseDemoQa {

    Faker faker = new Faker();
    String Address = faker.address().fullAddress();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String day = "25";
    String mount = "April";
    String year = "1991";
    String email = faker.internet().emailAddress();
    String phone = faker.phoneNumber().subscriberNumber(10);


    @BeforeAll
    static void configure() {
        Configuration.browser = EDGE;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = true;
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.pageLoadStrategy = "eager";
    }
}
