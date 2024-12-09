package com.sqa.datausa;

import com.sqa.utils.TestLogger;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

public class DataUsaRestAssuredDemo implements TestLogger {
    public static final String URL = "https://datausa.io/api";
    public static final String DRILLDOWNS_PARAM_NAME = "drilldowns";
    public static final String NATION_PARAM = "Nation";
    public static final String POPULATION_PARAM = "Population";


    //Nation, State, Year
    @Test
    public void check() {
        Response response = given()
                .baseUri(URL)
                .param(DRILLDOWNS_PARAM_NAME, NATION_PARAM)
                .param("measures", POPULATION_PARAM, POPULATION_PARAM)
                .when()
                .get("/data");

        log(response.prettyPrint());
    }

    @Test
    public void getDrilldownNationMeasurePopulation() {
        given()
                .baseUri(URL)
                .param(DRILLDOWNS_PARAM_NAME, NATION_PARAM)
                .param("measures", POPULATION_PARAM)
                .when()
                .get("/data")
                .then()
                .statusCode(200)
                .body("data", not(Matchers.emptyArray()));
    }

    @Test
    public void getDrilldownNationMeasurePopulationChecks() {
        Response response = given()
                .baseUri(URL)
                .param(DRILLDOWNS_PARAM_NAME, NATION_PARAM)
                .param("measures", POPULATION_PARAM)
                .when()
                .get("/data");

        Example example = response.as(Example.class);
        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertTrue(example.getData().get(0).getPopulation() > 0));

    }
}
