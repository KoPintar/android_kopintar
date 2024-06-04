package com.tiodwisatrio.kopintarandroid.data.response.login

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("token") val token: String? = null
)