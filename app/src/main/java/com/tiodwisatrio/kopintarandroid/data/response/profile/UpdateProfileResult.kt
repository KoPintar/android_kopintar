package com.tiodwisatrio.kopintarandroid.data.response.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileResult(
    @SerializedName("createdAt") val createdAt: String? = null,
    @SerializedName("updatedAt") val updatedAt: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("password") val password: String? = null,
    @SerializedName("profileImg") val profileImg: String? = null,
    @SerializedName("resetPasswordToken") val resetPasswordToken: String? = null,
)
