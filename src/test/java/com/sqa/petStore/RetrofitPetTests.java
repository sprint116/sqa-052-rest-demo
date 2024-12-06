package com.sqa.petStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqa.model.petStore.Category;
import com.sqa.model.petStore.Pet;
import com.sqa.services.PetStoreService;
import com.sqa.utils.TestLogger;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RetrofitPetTests implements TestLogger {

    private Retrofit petStoreRetrofit;
    private PetStoreService petStoreService;

    public static final String BASE_URL = "https://petstore.swagger.io/v2/";


    public RetrofitPetTests() {
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
    public void getPetById() throws IOException {
        Response<Pet> petResponse = petStoreService.getPetById("application/json", "328096").execute();

        log(petResponse.raw().toString());
        log(petResponse.errorBody().string());

        assertAll(
                () -> assertEquals(200, petResponse.code()),
                () -> assertEquals("328096", petResponse.body().getId().toString()));
    }

    @Test
    public void postPet() throws IOException {
        Response<Pet> petResponse = petStoreService.postPet("application/json", getCommonPet()).execute();

        assertAll(
                () -> assertEquals(200, petResponse.code()),
                () -> assertEquals(getCommonPet(), petResponse.body())
        );
    }

    @Test
    public void deletePet() throws IOException {
        Response<Pet> petResponse = petStoreService.deletePet(getCommonPet().getId().toString()).execute();

        assertEquals(200, petResponse.code());
    }

    @Test
    public void postAndCheckPet() throws IOException {
        petStoreService.postPet("application/json", getCommonPet()).execute();

        Response<Pet> petResponse = petStoreService.getPetById("application/json", getCommonPet().getId().toString()).execute();

        assertAll(
                () -> assertEquals(200, petResponse.code()),
                () -> assertEquals(getCommonPet(), petResponse.body()));
    }

    @Test
    public void deleteAndCheckPet() throws IOException {
        petStoreService.postPet("application/json", getCommonPet()).execute();
        petStoreService.deletePet(getCommonPet().getId().toString());

        Response<List<Pet>> petResponse = petStoreService.findPet("application/json", "available").execute();

        assertAll(
                () -> assertFalse(petResponse.body().contains(getCommonPet())));
    }

    private Pet getCommonPet() {
        return new Pet(1001L,
                new Category(1L, "border colly"),
                "Oreo",
                Collections.emptyList(),
                Collections.emptyList(),
                "available");
    }
}
