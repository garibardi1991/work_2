package ru.garibardi.api;

import org.junit.jupiter.api.Test;
import ru.garibardi.models.LoginBodyLombokModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static ru.garibardi.helpers.CustomApiListener.withCustomTemplates;

public class PetStoreTest {

    @Test
    void testByStatus() {
        given()
                .log().uri()
                .when()
                .get("https://petstore.swagger.io/#/pet/findPetsByStatus")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);

    }

//    "id": 0,
//            "username": "string",
//            "firstName": "string",
//            "lastName": "string",
//            "email": "string",
//            "password": "string",
//            "phone": "string",
//            "userStatus": 0

    @Test
    void testCreateWithArray() {
        LoginBodyLombokModel body = new LoginBodyLombokModel();
        body.setId("integer");
        body.setUsername("garibardi");
        body.setFirstName("Igor");
        body.setLastName("Truba");
        body.setEmail("get@mail.ru");
        body.setPassword("12345");
        body.setPhone("895238110");
        body.setUserStatus("integer");

        given()
                .filter(withCustomTemplates())
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithArray")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("type", is("unknown"))
                .body("message", is("ok"));

    }
}
