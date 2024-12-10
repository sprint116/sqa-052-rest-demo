package com.sqa.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqa.model.github.Issue;
import com.sqa.services.GitHubService;
import com.sqa.services.GoRestService;
import com.sqa.utils.TestLogger;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RetrofitDemoTest implements TestLogger {

    private Retrofit retrofitGit;
    private Retrofit retrofitGorest;
    private GitHubService gitHubService;
    private GoRestService gorestService;
    private static final String GIT_HUB_URL = "https://api.github.com/";
    private static final String GOREST_URL = "https://gorest.co.in/";

    private String issueTitle = String.format("issue %s", RandomStringUtils.randomAlphabetic(5));
    private String issueDescription = "Description of new issue";
    private String token_go_rest  = "Bearer eaeebb68b29e14ba28ac86fdbc4e6914d8492375ab2d5c41447627fd16af8669";

    public RetrofitDemoTest() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        this.retrofitGit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(GIT_HUB_URL)
                .build();


        this.retrofitGorest = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(GOREST_URL)
                .build();

        this.gitHubService = retrofitGit.create(GitHubService.class);
        this.gorestService = retrofitGorest.create(GoRestService.class);
    }

    /*
        01. Проверяем, что приходит 200 код в ответ на простой GET
    */
    @Test
    public void verifyHealthcheckTest() throws IOException {
        log("START: Verify GET zen");
        Response<String> response = gitHubService.getZen().execute();
        assertEquals(response.code(), 200);

        log("END: Verify GET zen");
    }

    /*
        02. Проверяем, что приходит непустое тело ответа на простой GET
    */
    @Test
    public void verifyDefunktBodyTest() throws IOException {
        log("START: Verify GET defunkt");
        Response<String> response = gitHubService.getDefunkt().execute();
        assertFalse(response.body().isEmpty());
        log("END: Verify GET defunkt");
    }

    /*
        03. Проверяем, что тело ответа содержит поле, равное значению
    */
    @Test
    public void verifyIssuesContainTest() throws IOException {
        log("START: Verify GET issues");
        Response<String> response = gitHubService.getUsersIssuesNoAuth("ilyademchenko").execute();
        assertTrue(response.raw().message().contains("Not Found"));
        log("END: Verify GET issues");
    }

    /*
        04. Проверяем, что тело ответа содержит поле после авторизации
    */
    @Test
    public void verifyIssuesAuthorized() throws IOException {
        log("START: Verify GET issues");
        Response<List<Issue>> response =
                gitHubService.getUsersIssues(
                        "Bearer ",
                        "ilyademchenko").execute();
        assertTrue(response.body().stream().anyMatch(issue -> issue.getTitle().equals("lux-training 02")));
        log("END: Verify GET issues");
    }

    /*
        05. Проверяем, что тело ответа содержит ошибку и 403 код
    */
    @Test
    public void verifyIssuesNoUserAgent() throws IOException {
        log("START: Verify GET issues");
        Response<List<Issue>> response =
                gitHubService.getUsersIssuesXml(
                        "application/xml",
                        "Bearer ",
                        "ilyademchenko").execute();
        assertAll(
                () -> assertEquals(response.code(), 415),
                () -> assertTrue(response.errorBody().string().contains("Must accept 'application/json'"))
        );
        log("END: Verify GET issues");
    }

    /*
        06. Проверяем, что ишью публикуется
    */
    @Test
    public void verifyPostIssues() throws IOException {
        log("START: Verify POST issues");
        String body = "{\n" +
                "    \"title\":\"lux-training 09\",\n" +
                "    \"body\": \"Description of issue\"\n" +
                "}";
        Response<String> response =
                gitHubService.postIssue(
                        "application/json",
                        "Bearer ",
                        "ilyademchenko",
                        body)
                        .execute();
        assertAll(
                () -> assertEquals(201, response.code()),
                () -> assertTrue(response.body().contains("lux-training 09"))
        );
        log("END: Verify POST issues");
    }

    /*
        07. Проверяем, что тело ответа содержит ошибку и 403 код
    */
    @Test
    public void verifyPostIssuesUrlParam() throws IOException {
        log("START: Verify POST issues");
        Response<String> response =
                gorestService.postIssueUrl(
                        "Bearer ",
                        "56",
                        "test-title",
                        "body").execute();
        assertAll(
                () -> assertEquals(201, response.code()),
                () -> assertTrue(response.body().contains("test-title"))
        );

        log("END: Verify POST issues");
    }

    /*
        08. Проверяем, что ишью публикуется (тело запроса в POJO)
    */
    @Test
    public void verifyPostPojo() throws IOException {
        Issue requestIssue = new Issue();
        requestIssue
                .setTitle(issueTitle)
                .setBody(issueDescription);

        log("START: Verify POST issues");
        Response<Issue> response =
                gitHubService.postIssuePojo(
                                "application/json",
                                "Bearer ",
                                "ilyademchenko",
                                requestIssue)
                        .execute();
        assertAll(
                () -> assertEquals(201, response.code()),
                () -> assertEquals(issueTitle, response.body().getTitle()),
                () -> assertEquals(issueDescription, response.body().getBody())
        );

        log("END: Verify POST issues");
    }

    /*
        09. Проверяем, что ишью публикуется (тело запроса в Map)
    */
    @Test
    public void verifyPostMap() throws IOException {
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("title", issueTitle);
        requestBody.put("body", issueDescription);

        log("START: Verify POST issues");
        Response<Map<String, Object>> response =
                gitHubService.postIssueMap(
                                "application/json",
                                "Bearer ",
                                "ilyademchenko",
                                requestBody)
                        .execute();

        assertAll(
                () -> assertEquals(201, response.code()),
                () -> assertEquals(issueTitle, response.body().get("title")),
                () -> assertEquals(issueDescription, response.body().get("body"))
        );

        log("END: Verify POST issues");
    }

}
