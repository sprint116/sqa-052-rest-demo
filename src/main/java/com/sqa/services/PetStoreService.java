package com.sqa.services;

import com.sqa.model.petStore.Category;
import com.sqa.model.petStore.Order;
import com.sqa.model.petStore.Pet;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PetStoreService {
    /*pet*/
    @GET("pet/{petId}")
    Call<Pet> getPetById(
            @Header("Accept") String accept,
            @Path("petId") String petId
    );

    @POST("pet")
    Call<Pet> postPet(
            @Header("Accept") String accept,
            @Body Pet pet
    );

    @DELETE("pet/{petId}")
    Call<Pet> deletePet(
            @Path("petId") String petId
    );

    @GET("pet/findByStatus")
    Call<List<Pet>> findPet(
            @Header("Accept") String accept,
            @Query("status") String status
    );

    /*store*/
    @GET("store/inventory")
    Call<Category> getInventory(
            @Header("Accept") String accept
    );

    @POST("store/order")
    Call<Order> postOrder(
            @Header("Accept") String accept,
            @Body Order order
    );

    @GET("store/order/{orderId}")
    Call<Order> getOrderById(
            @Header("Accept") String accept,
            @Path("orderId") String orderId
    );

    @DELETE("store/order/{orderId}")
    Call<Order> deleteOrder(
            @Path("orderId") String orderId
    );
}
