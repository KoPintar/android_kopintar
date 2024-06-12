package com.tiodwisatrio.kopintarandroid.data.response.forgotPassword

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("data") val data: ForgotPasswordResult? = null,
    @SerializedName("message") val message: String? = null
)