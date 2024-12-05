package com.sqa.petStore;

import com.sqa.model.petStore.Category;
import com.sqa.model.petStore.Pet;
import com.sqa.utils.TestLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class RestAssuredPetTest implements TestLogger {
    public static final String BASE_URL = "https://petstore.swagger.io/v2/";

    @Test
    public void postAndCheckPet() {
        Pet postedPet = getCommonPet();
        Pet returnPet = postPet(postedPet);

        Pet gotPet = this.getPetById(returnPet.getId());

        List<Pet> pets = findPetsByStatus(postedPet.getStatus());

        assertAll(
                () -> assertEquals(returnPet, gotPet),
                () -> assertEquals(postedPet, gotPet),
                () -> assertTrue(pets.contains(postedPet))
        );
    }

    @Test
    public void deleteAndCheckPet() {
        Pet postedPet = getCommonPet();

        deletePetById(postedPet.getId());
        petNotFoundCheck(postedPet.getId());

        List<Pet> pets = findPetsByStatus(postedPet.getStatus());

        assertAll(
                () -> assertFalse(pets.contains(postedPet))
        );
    }

    private void petNotFoundCheck(long id) {
        given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .when()
                .get("/pet/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    public void findPet() {
        findPetsByStatus("available");
    }

    private List<Pet> findPetsByStatus(String status) {
        Response response = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .header("Authorization", "special-key")
                .param("status", status)
                .when()
                .get("/pet/findByStatus");

        //List pets = response.as(List.class);
        List<Pet> pets = Arrays.asList(response.as(Pet[].class));

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertFalse(pets.isEmpty())
        );

        return pets;
    }


    @Test
    public void postPet() {
        Pet postedPet = getCommonPet();
        Pet returnPet = postPet(postedPet);

        assertAll(
                () -> assertEquals(postedPet, returnPet));
    }

    private Pet postPet(Pet postedPet) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(postedPet)
                .when()
                .post("/pet");

        log(response.prettyPrint());
        assertEquals(200, response.statusCode());

        return response.body().as(Pet.class);
    }

    @Test
    public void getPetById() {
        Pet awaitPet = getCommonPet();
        Pet returnPet = getPetById(awaitPet.getId());

        assertAll(
                () -> assertEquals(awaitPet, returnPet));
    }

    private Pet getPetById(long id) {
        Response response = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .when()
                .get("/pet/" + id);

        assertEquals(200, response.statusCode());

        return response.body().as(Pet.class);
    }

    @Test
    public void putPet() {
        Pet postedPet = getCommonPet();

        Response response = given().baseUri(BASE_URL).header("Content-type", "application/json").body(postedPet).when().put("/pet");
        response.prettyPrint();

        Pet returnPet = response.body().as(Pet.class);
        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertEquals(postedPet, returnPet)
        );
    }

    @Test
    public void deletePet() {
        deletePetById(getCommonPet().getId());
    }

    private void deletePetById(long id) {
        Response response = given()
                .baseUri(BASE_URL)
                .header("api_key", "special-key")
                .accept(ContentType.JSON)
                .when()
                .delete("/pet/" + id);

        log(response.prettyPrint());
        assertAll(
                () -> assertEquals(200, response.statusCode())
        );
    }

    @Test
    public void postPetById() {

        Pet postedPet = new Pet(2001L, null, "doggie2", null, null, "sold");

        Response response = given().baseUri(BASE_URL).accept(ContentType.JSON).when().post("/pet/2001");
        response.prettyPrint();

        Pet returnPet = response.body().as(Pet.class);
        assertAll(() -> assertEquals(200, response.statusCode()));
    }


    private Pet getCommonPet() {
        return new Pet(2001L, new Category(10L, "dogs"), "doggie", Collections.emptyList(), Collections.emptyList(), "available");
    }
}
