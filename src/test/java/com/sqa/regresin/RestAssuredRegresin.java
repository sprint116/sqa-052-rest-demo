package com.sqa.regresin;

import com.sqa.utils.TestLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestAssuredRegresin implements TestLogger {

    public static final String URL = "https://reqres.in/api";
    private static RequestSpecification requestSpecification;

    @BeforeAll
    public static void setUp() {
        requestSpecification = given()
                .baseUri(URL)
                .accept(ContentType.JSON);
    }

    @Test
    public void getUsers() {
        Response response = given(requestSpecification)
                .when()
                .get("users");

        response.then().body("data.first_name",not(Matchers.emptyArray()));
        response.then().body("data.findAll{!it.email.contains('@reqres.in')}", Matchers.empty());
        response.then().body("data.findAll{it.avatar.size()>=100}", Matchers.empty());

        log(response.body().jsonPath().get("data.findAll{it.avatar.size()<100}").toString());
        log(response.body().jsonPath().get("data.first_name.unique()").toString());
        log(response.body().jsonPath().get("data.findAll{it.email.endsWith('@reqres.in')}").toString());
        log(response.body().jsonPath().get("data.findAll{!it.email.contains('@reqres.in')}").toString());
        log(response.body().jsonPath().get("data.findAll{it.email=='emma.wong@reqres.in'}").toString());
        log(response.body().jsonPath().get("data.first_name").toString());
        log(response.prettyPrint());
    }

    @Test
    public void getUsersCollection() {
        Response response = given(requestSpecification)
                .when()
                .get("users");

        List<Datum> users = response.as(Example.class).getData();

        assertTrue(users.stream().allMatch(data -> data.getAvatar().length() < 100));
        assertTrue(users.stream().allMatch(data -> data.getEmail().contains("@reqres.in")));
        assertTrue(users.stream().noneMatch(datum -> datum.getFirstName().isEmpty()));
    }
}
