package com.tiodwisatrio.kopintarandroid.data.response.hama

import com.google.gson.annotations.SerializedName

data class HamaResponse(
    @SerializedName("data") val data: HamaResult? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null
)