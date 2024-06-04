package com.tiodwisatrio.kopintarandroid.data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data") val data: LoginResult? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null
)