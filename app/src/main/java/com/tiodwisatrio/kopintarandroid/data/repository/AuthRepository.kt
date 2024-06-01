package com.tiodwisatrio.kopintarandroid.data.repository

import com.tiodwisatrio.kopintarandroid.data.api.ApiService
import com.tiodwisatrio.kopintarandroid.data.response.LoginResponse
import com.tiodwisatrio.kopintarandroid.data.response.RegisterResponse

class AuthRepository private constructor(private val apiService: ApiService) {
    suspend fun register(name: String, username: String, email: String, password: String): RegisterResponse {
        return apiService.register(name, username, email, password)
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return apiService.login(email, password)
    }

    companion object {
        fun getInstance(apiService: ApiService) = AuthRepository(apiService)
    }
}