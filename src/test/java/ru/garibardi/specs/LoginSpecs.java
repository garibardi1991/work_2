package ru.garibardi.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static ru.garibardi.helpers.CustomApiListener.withCustomTemplates;

// todo move to request & response specs classes
public class LoginSpecs {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/login")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("token", notNullValue())
            .build();

    public static RequestSpecification loginRequestSpecsingleResourceTest = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/unknown/2")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpecsingleResourceTest = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("data.id", is(2))
            .expectBody("data.name", is("fuchsia rose"))
            .expectBody("data.year", is(2001))
            .expectBody("data.color", is("#C74375"))
            .expectBody("data.pantone_value", is("17-2031"))
            .expectBody("support.url", is("https://reqres.in/#support-heading"))
            .expectBody("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"))
            .build();

    public static RequestSpecification loginRequestSpecsingleUserNotFoundTest = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/unknown/23")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpecsingleUserNotFoundTest = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(404)
            .build();

    public static RequestSpecification loginRequestSpecregisterSuccessfulTest = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/register")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpecregisterSuccessfulTest = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("token", notNullValue())
            .expectBody("id", is(4))
            .build();

    public static RequestSpecification loginRequestSpecregisterUnsuccessfulTes = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/register")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpecregisterUnsuccessfulTes = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(400)
            .expectBody("error", is("Missing password"))
            .build();

    public static RequestSpecification loginRequestSpecupdateTest = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/users/2")
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification loginResponseSpecupdateTest = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("name", is("morpheus"))
            .expectBody("job", is("zion resident"))
            .build();
}
