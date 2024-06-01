package com.tiodwisatrio.kopintarandroid.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("loginResult")
    val loginResult: RegisterResult,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String? = null
)

data class RegisterResult(

    @field:SerializedName("name")
    val name: String,

)