package com.sqa.services;

import com.sqa.datausa.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataUsaService {
    @GET("/api/data")
    Call<Example> getData(
            @Query("drilldowns") String drilldowns,
            @Query("measures") String measures
    );

    @GET("/api/data")
    Call<String> getDataRaw(
            @Query("drilldowns") String drilldowns,
            @Query("measures") String measures
    );
}
