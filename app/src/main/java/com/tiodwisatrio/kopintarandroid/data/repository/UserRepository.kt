package com.tiodwisatrio.kopintarandroid.data.repository

import com.tiodwisatrio.kopintarandroid.data.api.ApiService
import com.tiodwisatrio.kopintarandroid.data.response.login.LoginResponse

class UserRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(username, password)
    }
}