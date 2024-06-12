package com.tiodwisatrio.kopintarandroid.data.repository

import com.tiodwisatrio.kopintarandroid.data.api.ApiService
import com.tiodwisatrio.kopintarandroid.data.response.disease.DiseaseResponse
import com.tiodwisatrio.kopintarandroid.data.response.historyType.HistoryTypeResponse
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PredictRepository(private val apiService: ApiService) {
    suspend fun predictRoasting(image: MultipartBody.Part): RoastingResponse {
        return apiService.predictRoasting(image)
    }

    suspend fun predictDisease(image: MultipartBody.Part, type: RequestBody): DiseaseResponse {
        return apiService.predictDisease(image, type)
    }

    suspend fun getPredictHistoryType(type: String): HistoryTypeResponse {
        return apiService.getHistoryType(type)
    }

    suspend fun getPredictPredictHistories(): HistoryTypeResponse {
        return apiService.getHistories()
    }
}