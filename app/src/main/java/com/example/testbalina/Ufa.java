package com.example.testbalina;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Дмитрий on 13.12.2016.
 */

public interface Ufa {

    @GET("getyml")
    Call<Yml_catalog> getData(@Query("key") String resourceName);
}
