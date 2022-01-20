package com.example.a4restapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestAPI {
    @GET("product/{id}")
    fun getFood(@Path("id") id: String?
    ): Call<Food?>?
}