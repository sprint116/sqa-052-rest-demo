package com.sqa.petStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqa.model.petStore.Category;
import com.sqa.model.petStore.Order;
import com.sqa.model.petStore.Pet;
import com.sqa.utils.TestLogger;
import com.sqa.services.PetStoreService;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RetrofitStoreTest implements TestLogger {

    private Retrofit petStoreRetrofit;
    private PetStoreService petStoreService;

    public static final String BASE_URL = "https://petstore.swagger.io/v2/";


    public RetrofitStoreTest() {
        Gson gson = new GsonBuilder().setLenient().create();

        petStoreRetrofit = new Retrofit
                .Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();

        petStoreService = petStoreRetrofit.create(PetStoreService.class);
    }

    @Test
    public void getInventory() throws IOException {
        Response<Category> categoryResponse = petStoreService.getInventory("application/json").execute();

        log(categoryResponse.raw().toString());
        log(categoryResponse.message());

        assertAll(
                () -> assertEquals(200, categoryResponse.code())
        );
    }

    @Test
    public void postOrder() throws IOException {
        Response<Order> orderResponse = petStoreService.postOrder("application/json", getCommonOrder()).execute();

        log(orderResponse.raw().toString());
        log(orderResponse.body().toString());

        assertAll(
                () -> assertEquals(200, orderResponse.code()),
                () -> assertEquals(getCommonOrder().getId(), orderResponse.body().getId()),
                () -> assertEquals(getCommonOrder().getPetId(), orderResponse.body().getPetId()),
                () -> assertEquals(getCommonOrder().getQuantity(), orderResponse.body().getQuantity()),
                () -> assertEquals(getCommonOrder().getStatus(), orderResponse.body().getStatus()),
                () -> assertEquals(getCommonOrder().getComplete(), orderResponse.body().getComplete())
        );
    }

    @Test
    public void getOrderById() throws IOException {
        Response<Order> orderResponse = petStoreService.getOrderById("application/json", getCommonOrder().getId().toString()).execute();

        log(orderResponse.raw().toString());
        //log(orderResponse.errorBody().string());

        assertAll(
                () -> assertEquals(200, orderResponse.code()),
                () -> assertEquals(getCommonOrder().getId(), orderResponse.body().getId()),
                () -> assertEquals(getCommonOrder().getPetId(), orderResponse.body().getPetId()),
                () -> assertEquals(getCommonOrder().getQuantity(), orderResponse.body().getQuantity()),
                () -> assertEquals(getCommonOrder().getStatus(), orderResponse.body().getStatus()),
                () -> assertEquals(getCommonOrder().getComplete(), orderResponse.body().getComplete())
        );

    }

    @Test
    public void deleteOrder() throws IOException {
        Response<Order> orderResponse = petStoreService.deleteOrder(getCommonOrder().getId().toString()).execute();
        assertEquals(200, orderResponse.code());
    }


    @Test
    public void postAndCheckOrder() throws IOException {

        petStoreService.postOrder("application/json", getCommonOrder()).execute();

        Response<Order> orderResponse = petStoreService.getOrderById("application/json", getCommonOrder().getId().toString()).execute();

        log(orderResponse.raw().toString());

        assertAll(
                () -> assertEquals(200, orderResponse.code()),
                () -> assertEquals(getCommonOrder().getId(), orderResponse.body().getId()),
                () -> assertEquals(getCommonOrder().getPetId(), orderResponse.body().getPetId()),
                () -> assertEquals(getCommonOrder().getQuantity(), orderResponse.body().getQuantity()),
                () -> assertEquals(getCommonOrder().getStatus(), orderResponse.body().getStatus()),
                () -> assertEquals(getCommonOrder().getComplete(), orderResponse.body().getComplete())
        );
    }


    @Test
    public void deleteAndCheckOrder() throws IOException {
        Response<Order> postOrderResponse = petStoreService.postOrder("application/json", getCommonOrder()).execute();
        log(postOrderResponse.raw().toString());
        assertEquals(200, postOrderResponse.code());

        Response<Order> deleteOrderResponse = petStoreService.deleteOrder(getCommonOrder().getId().toString()).execute();
        log(deleteOrderResponse.raw().toString());
        assertEquals(200, deleteOrderResponse.code());

        Response<Order> getOrderResponse = petStoreService.getOrderById("application/json", "available").execute();
        log(getOrderResponse.raw().toString());
        assertEquals(404, getOrderResponse.code());
    }

    private Order getCommonOrder() {
        return new Order(1235L, getCommonPet().getId(), 1l, String.valueOf(LocalDateTime.now()), "placed", true);
    }

    private Pet getCommonPet() {
        return new Pet(2001L, new Category(10L, "dogs"), "doggie", Collections.emptyList(), Collections.emptyList(), "available");
    }
}