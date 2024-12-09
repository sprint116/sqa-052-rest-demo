package com.sqa.datausa;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqa.services.DataUsaService;
import com.sqa.utils.TestLogger;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DataUsaRetrofitTest implements TestLogger {
    public static final String URL = "https://datausa.io/api/";

    private Retrofit dataUsaRetrofit;
    private DataUsaService dataUsaService;

    public DataUsaRetrofitTest() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        dataUsaRetrofit = new Retrofit.Builder()
                                .baseUrl(URL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build();

        dataUsaService = dataUsaRetrofit.create(DataUsaService.class);
    }

    @Test
    public void getData() throws IOException {
        Response<Example> response = dataUsaService.getData("Nation", "Population").execute();

        assertEquals(200, response.code());
        assertFalse(response.body().getData().isEmpty());
    }

    @Test
    public void getDataState() throws IOException {
        Response<Example> response = dataUsaService.getData("State", "Population").execute();

        assertEquals(200, response.code());
        assertFalse(response.body().getData().isEmpty());
    }

    @Test
    public void getDataYear() throws IOException {
        Response<Example> response = dataUsaService.getData("Year", "Population").execute();

        assertEquals(200, response.code());
        assertFalse(response.body().getData().isEmpty());
    }

    @Test
    public void getDataRaw() throws IOException {
        Response<String> response = dataUsaService.getDataRaw("Year", "Population").execute();

        JsonPath path = JsonPath.from(response.body());

        assertTrue(path.getString("data[0].Population").contains("2"));

        log(path.getString("data[0].Population"));
    }
}
