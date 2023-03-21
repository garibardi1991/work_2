package ru.garibardi.api;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.garibardi.models.LoginBodyLombokModel;
import ru.garibardi.models.LoginResponseLombokModel;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.garibardi.specs.LoginSpecs.*;

@Tag("ReqresTest")
public class ReqresInTestsHomeWork {


    @Test
    void singleResourceTest() {
        given()
                .spec(loginRequestSpecsingleResourceTest)
                .when()
                .get()
                .then()
                .spec(loginResponseSpecsingleResourceTest);

    }

    @Test
    void singleUserNotFoundTest() {
        given()
                .spec(loginRequestSpecsingleUserNotFoundTest)
                .when()
                .get()
                .then()
                .spec(loginResponseSpecsingleUserNotFoundTest);

    }

    @Test
    void registerSuccessfulTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");

        LoginResponseLombokModel response = given()
                .spec(loginRequestSpecregisterSuccessfulTest)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpecregisterSuccessfulTest)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

    }

    @Test
    void registerUnsuccessfulTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setEmail("sydney@fife");

        given()
                .spec(loginRequestSpecregisterUnsuccessfulTes)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpecregisterUnsuccessfulTes);
    }

    @Test
    void updateTest() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setName("morpheus");
        body.setJob("zion resident");

        given()
                .spec(loginRequestSpecupdateTest)
                .body(body)
                .when()
                .put()
                .then()
                .spec(loginResponseSpecupdateTest);
    }
}
