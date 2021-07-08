package com.example.letstellauth.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthApi {


//    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Header("Accept-Language") lang: String,
        @Body request: LoginRequest
    ) : Call<LoginResponse>
}