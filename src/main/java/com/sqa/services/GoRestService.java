package com.sqa.services;

import com.sqa.model.gorest.Post;
import com.sqa.model.gorest.User;
import com.sqa.model.gorest.Users;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface GoRestService {

    @POST("/public/v1/users/{user}/posts")
    Call<String> postIssueUrl(
            @Header("Authorization") String authToken,
            @Path("user") String user,
            @Query("title") String title,
            @Query("body") String body);

    @GET("/public/v1/users")
    Call<Users> getUsers(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType
    );

    @POST("/public/v2/users")
    Call<User> createUser(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Body User user
    );

    @GET("/public/v2/users/{userId}")
    Call<User> getUser(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Path("userId") int userId
    );

    @DELETE("/public/v2/users/{userId}")
    Call<User> delUser(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Path("userId") int userId
    );

    @GET("/public/v2/users/{userId}/posts")
    Call<List<Post>> getPosts(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Path("userId") int userId
    );

        @POST("/public/v2/users/{userId}/posts")
    Call<Post> createPost(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Path("userId") int userId,
            @Body Post post
    );

    @GET("/public/v2/posts/{postId}")
    Call<Post> getPost(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Path("postId") int postId
    );

    @DELETE("/public/v2/posts/{postId}")
    Call<Post> delPost(
            @Header("Authorization") String authToken,
            @Header("Content-type") String ContentType,
            @Path("postId") int postId
    );
}
