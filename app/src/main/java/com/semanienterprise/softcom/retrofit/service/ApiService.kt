package com.semanienterprise.softcom.retrofit.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.semanienterprise.softcom.models.FormData
import com.semanienterprise.softcom.retrofit.network.ApiClient

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    //Api for getting JSON
    @GET("Softcom/{fileName}")
    fun getJson(@Path("fileName") fileName: String): Single<FormData>

    companion object Client {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://generalkolo.github.io/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}

