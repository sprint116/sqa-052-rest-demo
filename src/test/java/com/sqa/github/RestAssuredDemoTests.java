package com.sqa.github;

import com.sqa.model.github.Issue;
import com.sqa.utils.TestLogger;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestAssuredDemoTests implements TestLogger {

    private static final String BASE_URL = "https://api.github.com/";
    private String issueTitle = String.format("issue %s", RandomStringUtils.randomAlphabetic(5));
    private String issueDescription = "Description of new issue";
    private String token  = "Bearer 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";

    private String token_go_rest  = "Bearer eaeebb68b29e14ba28ac86fdbc4e6914d8492375ab2d5c41447627fd16af8669";

    /*
        01. Проверяем, что приходит 200 код в ответ на простой GET
    */
    @Test
    @Tag("tag")
    public void verifyHealthcheckTest() {
        log("START: Verify GET zen");
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/zen")
                .then()
                .statusCode(200)
                .log().ifStatusCodeIsEqualTo(200);
        log("END: Verify GET zen");
    }

    /*
        02. Проверяем, что приходит непустое тело ответа на простой GET
    */
    @Test
    public void verifyDefunktBodyTest() {
        log("START: Verify GET defunkt");
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/zen");
        response.prettyPrint();
        response.then()
                .body(Matchers.not(Matchers.empty()));
        log("END: Verify GET defunkt");
    }

    /*
        03. Проверяем, что тело ответа содержит поле, равное значению
    */
    @Test
    public void verifyIssuesContainTest() {
        log("START: Verify GET issues");
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/repos/sprint116/Test/issues");
        response.prettyPrint();
        response.then()
                .body("message", equalTo("Not Found"));
        log("END: Verify GET issues");
    }

    /*
        04. Проверяем, что тело ответа содержит поле после авторизации
    */
    @Test
    public void verifyIssuesAuthorized() {
        log("START: Verify GET issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Authorization", token)
                .when()
                .get("/repos/sprint116/Test/issues");
        response.prettyPrint();
        response.then()
                .body("title", Matchers.hasItem("lux-training 07"));
        log("END: Verify GET issues");
    }

    /*
        05. Проверяем, что тело ответа содержит ошибку и 403 код
    */
    @Test
    public void verifyIssuesNoUserAgent() {
        log("START: Verify GET issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/xml")
                .when()
                .get("/repos/sprint116/Test/issues");
        response.prettyPrint();
        response.then()
                .statusCode(415)
                .body("message", containsString("Must accept 'application/json'"));
        log("END: Verify GET issues");
    }

    /*
        06. Проверяем, что ишью публикуется (тело запроса в строке)
    */
    @Test
    public void verifyPostIssues() {
        log("START: Verify POST issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/json")
                .header("Authorization", token)
                .body("{\n" +
                        "    \"title\":\"lux-training 07\",\n" +
                        "    \"body\": \"Description of issue\"\n" +
                        "}")
                .when()
                .post("/repos/sprint116/Test/issues");
        response.prettyPrint();
        response.then()
                .statusCode(201)
                .body("title", containsString("lux-training 07"));
        log("END: Verify POST issues");
    }

    /*
        07. Проверяем, что тело ответа содержит ошибку и 403 код
    */
    @Test
    public void verifyPostIssuesUrlParam() {
        log("START: Verify POST issues");
        Response response = given()
                // тут другой ресурс
                .baseUri("https://gorest.co.in/public/v1")
                .relaxedHTTPSValidation()
                .header("Authorization", token_go_rest)
                .param("title", "test-title")
                .param("body", "test-body")
                .when()
                .post("/users/7440150/posts");
        response.prettyPrint();
        response.then()
                .statusCode(201)
                .body("data", Matchers.hasEntry("title", "test-title"));
        log("END: Verify POST issues");
    }

    /*
        08. Проверяем, что ишью публикуется (тело запроса в POJO)
    */
    @Test
    public void verifyPostPojo() {
        Issue requestIssue = new Issue();
        requestIssue
                .setTitle(issueTitle)
                .setBody(issueDescription);

        log("START: Verify POST issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/json")
                .header("Authorization", token)
                .body(requestIssue)
                .when()
                .post("/repos/sprint116/Test/issues");
        response.prettyPrint();

        Issue responseIssue = response.body().as(Issue.class);

        assertAll(
                () -> assertEquals(201, response.statusCode()),
                () -> assertEquals(issueTitle, responseIssue.getTitle(), "Issue title"),
                () -> assertEquals(issueDescription, responseIssue.getBody(), "Issue description"));
        log("END: Verify POST issues");
    }

    /*
        09. Проверяем, что ишью публикуется (тело запроса в Map)
    */
    @Test
    public void verifyPostMap() {
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("title", issueTitle);
        requestBody.put("body", issueDescription);

        log("START: Verify POST issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/json")
                .header("Authorization", token)
                .body(requestBody)
                .when()
                .post("/repos/sprint116/Test/issues");
        response.prettyPrint();

        Map<String, Object> responseIssue = response.body().as(LinkedHashMap.class);

        assertAll(
                () -> assertEquals(201, response.statusCode()),
                () -> assertEquals(issueTitle, responseIssue.get("title"), "Issue title"),
                () -> assertEquals(issueDescription, responseIssue.get("body"), "Issue description"));
        log("END: Verify POST issues");
    }

    /*
        10. Проверяем, что ишью публикуется (тело запроса в POJO, поиск с помощью json path)
    */
    @Test
    public void verifyPostPojoWithJsonPath() {
        Issue requestIssue = new Issue();
        requestIssue
                .setTitle(issueTitle)
                .setBody(issueDescription);

        log("START: Verify POST issues");
        Response response = given()
                .baseUri(BASE_URL)
                .header("Accept", "application/json")
                .header("Authorization", token)
                .body(requestIssue)
                .when()
                .post("/repos/sprint116/Test/issues");
        response.prettyPrint();

        assertAll(
                () -> assertEquals(201, response.statusCode()),
                () -> assertEquals(issueTitle, response.jsonPath().get("title"), "Issue title"),
                () -> assertEquals(issueDescription, response.jsonPath().get("body"), "Issue description"));
        log("END: Verify POST issues");

    }
}
