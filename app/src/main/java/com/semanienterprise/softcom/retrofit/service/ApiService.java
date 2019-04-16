package com.semanienterprise.softcom.retrofit.service;

import com.semanienterprise.softcom.models.FormData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    //TODO: Set the url to get the JSON Object
    //Api for getting JSON
    @Headers("Content-Type: application/json")
    @GET("Softcom/{fileName}")
    Single<FormData> getJson(
            @Path("fileName") String fileName
    );
}

