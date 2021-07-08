package com.example.letstellauth.network

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    var accessToken: String?,

    @SerializedName("status")
    var status: String,

    @SerializedName("user")
    var user: User
)
