package com.tiodwisatrio.kopintarandroid.data.response.roasting

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoastingResult(
    @SerializedName("result") val result: String? = null,
    @SerializedName("classResult") val classResult: Int? = null,
    @SerializedName("confidenceScore") val confidenceScore: Double? = null,
): Parcelable