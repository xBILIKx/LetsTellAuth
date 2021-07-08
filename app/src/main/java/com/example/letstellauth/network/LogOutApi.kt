package com.example.letstellauth.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LogOutApi {

    @POST("auth/logout")
    fun login(
        @Body request: LoginRequest
    ) : Call<LoginResponse>
}