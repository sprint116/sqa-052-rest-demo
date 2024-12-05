package com.sqa.petStore;

import com.sqa.model.petStore.Category;
import com.sqa.model.petStore.Order;
import com.sqa.model.petStore.Pet;
import com.sqa.utils.TestLogger;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class RestAssuredStoreTest implements TestLogger {
    public static final String BASE_URL = "https://petstore.swagger.io/v2/";

    private void getStoreInventory() /*Returns pet inventories by status*/ {
        Response response = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .when()
                .get("/store/inventory");

        log(response.prettyPrint());

        assertEquals(200, response.statusCode());
    }

    private Order postStoreOrder(Order postedOrder) /*Place an order for a pet*/ {

        Response response = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(postedOrder)
                .when()
                .post("/store/order");

        log(response.prettyPrint());

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertNotNull(response.body().as(Order.class).getId())
        );
        return response.body().as(Order.class);
    }

    private Order getStoreOrderByOrderId(long orderId) /*Find purchase order by ID*/ {

        Response response = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .when()
                .get("/store/order/" + orderId);

        log(response.prettyPrint());

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertEquals(orderId, response.body().as(Order.class).getId())
        );

        return response.body().as(Order.class);
    }

    private void notFoundOrderByOrderId(long orderId) /*Find purchase order by ID*/ {
        Response response = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .when()
                .get("/store/order/" + orderId);

        log(response.prettyPrint());

        assertAll(
                () -> assertEquals(404, response.statusCode())
        );

    }

    private void deleteStoreOrderByOrderId(long orderId) /*Delete purchase order by ID*/ {

        Response response = given()
                .baseUri(BASE_URL)
                .accept(ContentType.JSON)
                .when()
                .delete("/store/order/" + orderId);

        log(response.prettyPrint());

        assertAll(
                () -> assertEquals(200, response.statusCode()),
                () -> assertEquals(true, response.body().asString().contains("message")),
                () -> assertEquals(true, response.body().asString().contains("code"))
        );
    }


    @Test
    public void createAndCheckOrder() {
        Order postedOrder = getCommonOrder(0, getCommonPet().getId());
        Order returnOrder = postStoreOrder(postedOrder);

        Order gotOrder = this.getStoreOrderByOrderId(returnOrder.getId());

        assertAll(
                () -> assertEquals(returnOrder, gotOrder)
        );
    }

    @Test
    public void deleteAndCheckOrder() {
        Order postedOrder = getCommonOrder(0, getCommonPet().getId());
        Order returnOrder = postStoreOrder(postedOrder);
        deleteStoreOrderByOrderId(returnOrder.getId());
        notFoundOrderByOrderId(returnOrder.getId());
    }

    private Order getCommonOrder(long orderId, long petId) {
        return new Order(orderId, petId, 1l, String.valueOf(LocalDateTime.now()), "placed", true);
    }

    private Pet getCommonPet() {
        return new Pet(2001L, new Category(10L, "dogs"), "doggie", Collections.emptyList(), Collections.emptyList(), "available");
    }
}
