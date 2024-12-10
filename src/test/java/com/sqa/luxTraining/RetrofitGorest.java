package com.sqa.luxTraining;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqa.model.gorest.Post;
import com.sqa.model.gorest.User;
import com.sqa.model.gorest.Users;
import com.sqa.services.GoRestService;
import com.sqa.utils.TestLogger;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RetrofitGorest implements TestLogger {
    private Retrofit retrofitGorest;
    private GoRestService gorestService;
    private static final String GOREST_URL = "https://gorest.co.in";
    private String token = "Bearer eaeebb68b29e14ba28ac86fdbc4e6914d8492375ab2d5c41447627fd16af8669";
    private String ContentTypeJSON = "application/json";
    String randomNum = String.valueOf(new Random().nextInt(500) + 1);
    String randomEmail = String.format("%s.ramakrishna@11ce.com", new Random().nextInt(500) + 1);


    public RetrofitGorest() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofitGorest = new Retrofit.Builder()
                .baseUrl(GOREST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gorestService = retrofitGorest.create(GoRestService.class);
    }

    User getCommonUserToGet() throws IOException {
        Response<Users> response = gorestService.getUsers(token, ContentTypeJSON).execute();
        response.body().getData().get(0).getId();
        return new User(
                response.body().getData().get(0).getId(),
                response.body().getData().get(0).getName(),
                response.body().getData().get(0).getEmail(),
                response.body().getData().get(0).getGender(),
                response.body().getData().get(0).getStatus()
        );
    }

    Post getCommonPostToGet() throws IOException {
        Response<List<Post>> response = gorestService.getPosts(token, ContentTypeJSON,getCommonUserToGet().getId()).execute();
        response.body().toString();
        return new Post(
                response.body().get(0).getId(),
                response.body().get(0).getUserId(),
                response.body().get(0).getTitle(),
                response.body().get(0).getBody()
        );
    }


    @Test
    void getUsers() throws IOException {
        Response<Users> response = gorestService.getUsers(token, ContentTypeJSON).execute();
        log(response.raw().toString());
        log(response.body().toString());
        assertEquals(200, response.code());
    }

    @Test
    void createUser() throws IOException {
        Response<User> response = gorestService.createUser(token, ContentTypeJSON, getCommonUserToGet().withId(null).withEmail(randomEmail)).execute();
        log(response.raw().toString());
        log(response.body().toString());
        assertEquals(201, response.code());
    }

    @Test
    void getUser() throws IOException {
        Response<User> response = gorestService.getUser(token, ContentTypeJSON, getCommonUserToGet().getId()).execute();
        log(response.raw().toString());
        log(response.body().toString());
        assertEquals(200, response.code());
    }

    @Test
    void delUser() throws IOException {
        Response<User> response = gorestService.delUser(token, ContentTypeJSON, getCommonUserToGet().getId()).execute();
        log(response.raw().toString());
        assertEquals(204, response.code());
    }

    @Test
    void getPosts() throws IOException {
        Response<List<Post>> response = gorestService.getPosts(token, ContentTypeJSON,getCommonUserToGet().getId()).execute();
        log(response.raw().toString());
        log(response.body().toString());
        assertEquals(200, response.code());
    }

    @Test
    void createPost() throws IOException {
        int userId = getCommonUserToGet().getId();
        Response<Post> response = gorestService.createPost(
                token,
                ContentTypeJSON,
                userId,
                getCommonPostToGet()
                        .withId(null)
                        .withBody("Test body " + randomNum)
                        .withTitle("Test Title " + randomNum)
                        .withUserId(userId)
        ).execute();
        log(response.raw().toString());
        log(response.body().toString());
        assertEquals(201, response.code());
    }

    @Test
    void getPost() throws IOException {
        Response<Post> response = gorestService.getPost(token, ContentTypeJSON, getCommonPostToGet().getId()).execute();
        log(response.raw().toString());
        log(response.body().toString());
        assertEquals(200, response.code());
    }

    @Test
    void delPost() throws IOException {
        Response<Post> response = gorestService.delPost(token, ContentTypeJSON, getCommonPostToGet().getId()).execute();
        log(response.raw().toString());
        assertEquals(204, response.code());
    }
}