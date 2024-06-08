package com.tiodwisatrio.kopintarandroid.data.api

import com.tiodwisatrio.kopintarandroid.data.response.disease.DiseaseResponse
import com.tiodwisatrio.kopintarandroid.data.response.hama.HamaResponse
import com.tiodwisatrio.kopintarandroid.data.response.login.LoginResponse
import com.tiodwisatrio.kopintarandroid.data.response.profile.UpdateProfileResponse
import com.tiodwisatrio.kopintarandroid.data.response.register.RegisterResponse
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
        suspend fun login(
            @Field("username") username: String,
            @Field("password") password: String
        ): LoginResponse

    @FormUrlEncoded
    @PUT("user/profile")
    suspend fun updateProfile(
        @Field("name") name: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): UpdateProfileResponse

    @Multipart
    @POST("predict/daun")
    suspend fun predictHama(
        @Part file: MultipartBody.Part,

    ): HamaResponse

    @Multipart
    @POST("predict/disease")
    suspend fun predictDisease(
        @Part file: MultipartBody.Part,
        @Part("type") type: RequestBody

        ): DiseaseResponse

    @Multipart
    @POST("predict/roasting")
    suspend fun predictRoasting(
        @Part file: MultipartBody.Part,
    ): RoastingResponse
}