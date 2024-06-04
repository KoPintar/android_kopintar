package com.tiodwisatrio.kopintarandroid.data.response.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("data") val data: RegisterResult? = null,
    @SerializedName("message") val message: String? = null
)