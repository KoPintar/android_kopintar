package com.tiodwisatrio.kopintarandroid.data.repository

import com.tiodwisatrio.kopintarandroid.data.api.ApiService
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

class RoastingRepository(private val apiService: ApiService) {
    suspend fun uploadImage(image: MultipartBody.Part): Response<RoastingResponse> {
        return apiService.uploadImage(image)
    }
}