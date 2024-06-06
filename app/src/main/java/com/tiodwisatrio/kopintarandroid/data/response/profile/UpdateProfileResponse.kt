package com.tiodwisatrio.kopintarandroid.data.response.profile

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("data") val data: UpdateProfileResult? = null,
    @SerializedName("message") val message: String? = null
)