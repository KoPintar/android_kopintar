package com.tiodwisatrio.kopintarandroid.data.api

import com.tiodwisatrio.kopintarandroid.data.response.login.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
//    @FormUrlEncoded
//    @POST("register")
//    suspend fun register(
//        @Field("name") name: String,
//        @Field("username") username: String,
//        @Field("email") email: String,
//        @Field("password") password: String,
//    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
        suspend fun login(
            @Field("username") username: String,
            @Field("password") password: String
        ): LoginResponse
}