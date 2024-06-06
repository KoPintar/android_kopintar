package com.tiodwisatrio.kopintarandroid.data.response.hama

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HamaResult(
    @SerializedName("result") val result: String? = null,
    @SerializedName("classResult") val classResult: Int? = null,
    @SerializedName("confidenceScore") val confidenceScore: Double? = null,
): Parcelable