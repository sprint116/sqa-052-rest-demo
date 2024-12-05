package com.sqa.services;

import com.sqa.model.github.Issue;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface GitHubService {

    @GET("zen")
    Call<String> getZen();

    @GET("users/defunkt")
    Call<String> getDefunkt();

    @GET("repos/{user}/rest/issues")
    Call<String> getUsersIssuesNoAuth(@Path("user") String user);

    @GET("repos/{user}/rest/issues")
    Call<List<Issue>> getUsersIssues(
            @Header("Authorization") String authToken,
            @Path("user") String user);

    @GET("repos/{user}/rest/issues")
    Call<List<Issue>> getUsersIssuesXml(
            @Header("Accept") String acceptHeader,
            @Header("Authorization") String authToken,
            @Path("user") String user);

    @POST("repos/{user}/rest/issues")
    Call<String> postIssue(
            @Header("Accept") String acceptHeader,
            @Header("Authorization") String authToken,
            @Path("user") String user,
            @Body String body);

    @POST("repos/{user}/rest/issues")
    Call<Issue> postIssuePojo(
            @Header("Accept") String acceptHeader,
            @Header("Authorization") String authToken,
            @Path("user") String user,
            @Body Issue body);

    @POST("repos/{user}/rest/issues")
    Call<Map<String, Object>> postIssueMap(
            @Header("Accept") String acceptHeader,
            @Header("Authorization") String authToken,
            @Path("user") String user,
            @Body Map<String, Object> body);
}
