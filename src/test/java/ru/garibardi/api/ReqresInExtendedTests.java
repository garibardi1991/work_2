package ru.garibardi.api;

import org.junit.jupiter.api.Test;
import ru.garibardi.models.LoginBodyPojoModel;
import ru.garibardi.models.LoginResponsePojoModel;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ReqresInExtendedTests {


    @Test
    void loginWithPojoModelTest() {
        LoginBodyPojoModel body = new LoginBodyPojoModel();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");

        LoginResponsePojoModel response = given()
                .log().all()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}