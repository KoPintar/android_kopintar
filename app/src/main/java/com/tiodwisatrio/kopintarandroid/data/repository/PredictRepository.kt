package com.tiodwisatrio.kopintarandroid.data.repository

import com.tiodwisatrio.kopintarandroid.data.api.ApiService
import com.tiodwisatrio.kopintarandroid.data.response.hama.HamaResponse
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResponse
import okhttp3.MultipartBody

class PredictRepository(private val apiService: ApiService) {
    suspend fun predictHama(image: MultipartBody.Part): HamaResponse {
        return apiService.predictHama(image)
    }

    suspend fun predictRoasting(image: MultipartBody.Part): RoastingResponse {
        return apiService.predictRoasting(image)
    }
}