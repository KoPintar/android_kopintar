package com.tiodwisatrio.kopintarandroid.data.response.disease

import com.google.gson.annotations.SerializedName
import com.tiodwisatrio.kopintarandroid.data.response.hama.HamaResult

data class DiseaseResponse(
    @SerializedName("data") val data: DiseaseResult? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null,
)