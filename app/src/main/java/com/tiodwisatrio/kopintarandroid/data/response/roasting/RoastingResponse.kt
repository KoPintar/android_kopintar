package com.tiodwisatrio.kopintarandroid.data.response.roasting

import com.google.gson.annotations.SerializedName

data class RoastingResponse(
    @SerializedName("data") val data: RoastingResult? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null
)