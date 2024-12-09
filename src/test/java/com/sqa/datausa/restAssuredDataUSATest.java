package com.sqa.datausa;

import com.sqa.model.datausa.DataUSA;
import com.sqa.utils.TestLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class restAssuredDataUSATest implements TestLogger {

    public static final String DATAUSA_IO_API_DATA = "https://datausa.io/api/data";

    @Test
    public void drilldownNationMeasurePopulations() {
        Response response = given()
                .baseUri(DATAUSA_IO_API_DATA)
                .accept(ContentType.JSON)
                .param("drilldowns", "Nation")
                .param("measures", "Population")
                .when()
                .get("");

        DataUSA dataUSA = response.as(DataUSA.class);

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertTrue(dataUSA.getData().stream().allMatch(datum -> datum.getIDYear() > 1776))
        );

        //log(response.prettyPrint());
        /*log(response.jsonPath().getString("data.Nation"));
        log(response.jsonPath().get("data.Nation").toString());
        log(response.jsonPath().get("data[-1].Nation").toString());
        log(response.jsonPath().get("data.findAll{it.Year!='2022'}.Population").toString());
        log(response.jsonPath().get("data.findAll{it.Year!='2022'}.Population.min()").toString());
        log(response.jsonPath().get("data.findAll{it.Year!='2022'}.Population.sum()").toString());
        log(response.jsonPath().get("data.findAll{it.Year!='2022'}.size()").toString());
        log(response.jsonPath().get("data.findAll{it.Year!='2022'}.unique()").toString());
        log(response.jsonPath().get("data.findAll{it.Nation.startsWith('U')}").toString());
        log(response.jsonPath().get("data.findAll{it.Nation.contains('U')}").toString());
        log(response.jsonPath().get("data.findAll{it.Nation.contains('U')}.Year[0].toInteger()").toString());
        log(response.jsonPath().get("data.findAll{it.Nation.contains('U')}.collect{it.Year.toInteger()}").toString());*/

    }

    @Test
    public void drilldownYearMeasurePopulation() {
        Response response = given()
                .baseUri(DATAUSA_IO_API_DATA)
                .accept(ContentType.JSON)
                .param("drilldowns", "Year")
                .param("measures", "Population")
                .when()
                .get("");

        DataUSA dataUSA = response.as(DataUSA.class);

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertTrue(dataUSA.getData().stream().allMatch(datum -> datum.getIDYear() > 1776))
        );
    }

    @Test
    public void populationIncrease() {

        int countYears;
        int lastPopulation;
        int actualPopulation;
        int countTrues = 0;
        int i = 0;

        Response response = given()
                .baseUri(DATAUSA_IO_API_DATA)
                .accept(ContentType.JSON)
                .param("drilldowns", "Nation")
                .param("measures", "Population")
                .when()
                .get("");

        countYears = response.jsonPath().get("data.collect{it.Year}.unique().size()");
        countYears--;

        while (countYears > 0) {
            i = (10 - countYears);
            log("____________Iteration_" + i + "____________");
            actualPopulation = response.jsonPath().get(String.format("data.find{it.Year=='%s'}.Population",
                    Integer.valueOf(response.jsonPath().get(String.format("data.collect{it.Year}.unique().reverse()[%s]", countYears)))));
            countYears--;
            lastPopulation = response.jsonPath().get(String.format("data.find{it.Year=='%s'}.Population",
                    Integer.valueOf(response.jsonPath().get(String.format("data.collect{it.Year}.unique().reverse()[%s]", countYears)))));


            log("Population " + (response.jsonPath().get(String.format("data.collect{it.Year}.unique().reverse()[%s]", (countYears + 1)))) + " year = " + actualPopulation);
            log("Population " + (response.jsonPath().get(String.format("data.collect{it.Year}.unique().reverse()[%s]", (countYears)))) + " year = " + lastPopulation);
            assertTrue(lastPopulation < actualPopulation);
            if (lastPopulation < actualPopulation) {
                countTrues++;
            }
        }

        log("Все итерации заверщены");

        int finalCountTrues = countTrues;
        int finalI = i;

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertTrue(finalCountTrues == finalI)
        );
    }


    @Test
    /*Не совсем понял о какой проверки идет речь в задаче*/
    public void ratioPopulationOfNationToOfYear() {

        long sumPopulationOfNation =0 ;
        long sumPopulationOfYears = 0;
        int sizeNation;
        int sizeYeas;

        Response response = given()
                .baseUri(DATAUSA_IO_API_DATA)
                .accept(ContentType.JSON)
                .param("drilldowns", "Nation")
                .param("measures", "Population")
                .when()
                .get("");

        sizeNation = response.jsonPath().get("data.findAll{it.Year}.Population.size()");
        sizeNation--;
        sizeYeas = response.jsonPath().get("data.findAll{it.Nation}.Population.size()");
        sizeYeas--;

        while (sizeNation >= 0) {
            sumPopulationOfNation = sumPopulationOfNation + Long.parseLong(response.jsonPath().get(String.format("data[%d].Population",sizeNation)).toString());
            log("____________Nation_iteration_" + sizeNation + "____________");
            log("Population: " + response.jsonPath().get(String.format("data[%d].Population",sizeNation)).toString());
            log("Sum Population Of Nation: "+ sumPopulationOfNation);
            sizeNation--;
        }

        while (sizeYeas >= 0) {
            sumPopulationOfYears = sumPopulationOfYears + Long.parseLong(response.jsonPath().get(String.format("data[%d].Population",sizeYeas)).toString());
            log("____________Year_iteration_" + sizeYeas + "____________");
            log("Population: " + response.jsonPath().get(String.format("data[%d].Population",sizeYeas)).toString());
            log("Sum Population Of Year: "+ sumPopulationOfYears);
            sizeYeas--;
        }

        long finalSumPopulationOfNation = sumPopulationOfNation;
        long finalsumPopulationOfYears = sumPopulationOfYears;

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertTrue(finalsumPopulationOfYears == finalSumPopulationOfNation)
        );
    }
}
