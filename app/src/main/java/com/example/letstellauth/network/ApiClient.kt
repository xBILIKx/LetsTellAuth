package com.example.letstellauth.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var authApi: AuthApi
    private lateinit var logOutApi: LogOutApi

    private val URL = "https://lets-tell.herokuapp.com/api/v1/"

    fun getAuthService(): AuthApi{

        if(!::authApi.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            authApi = retrofit.create(AuthApi::class.java)
        }

        return authApi
    }

    fun getLogOutService(): LogOutApi{

        if(!::logOutApi.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            logOutApi = retrofit.create(LogOutApi::class.java)
        }

        return logOutApi
    }

}