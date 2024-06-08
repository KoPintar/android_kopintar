package com.tiodwisatrio.kopintarandroid.data.response.disease

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiseaseResult(
    @SerializedName("result") val result: String? = null,
    @SerializedName("classResult") val classResult: Int? = null,
    @SerializedName("confidenceScore") val confidenceScore: Double? = null,
    @SerializedName("suggestion") val suggestion: Array<String>? = null

): Parcelable