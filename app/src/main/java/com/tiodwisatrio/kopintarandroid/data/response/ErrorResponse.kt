package com.tiodwisatrio.kopintarandroid.data.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @field:SerializedName("error")
    val error: Boolean? = false,
    @field:SerializedName("message")
    val message: String? = null
)