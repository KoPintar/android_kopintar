package com.tiodwisatrio.kopintarandroid.data.response.forgotPassword

import com.google.gson.annotations.SerializedName

data class ForgotPasswordResult(
    @SerializedName("email") val email: String? = null,
)