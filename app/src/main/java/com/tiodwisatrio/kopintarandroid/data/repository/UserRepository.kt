package com.tiodwisatrio.kopintarandroid.data.repository

import com.tiodwisatrio.kopintarandroid.data.api.ApiService
import com.tiodwisatrio.kopintarandroid.data.response.login.LoginResponse
import com.tiodwisatrio.kopintarandroid.data.response.profile.UpdateProfileResponse
import com.tiodwisatrio.kopintarandroid.data.response.register.RegisterResponse

class UserRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): LoginResponse {
        return apiService.login(username, password)
    }

    suspend fun register(name: String, username: String, email: String, password: String): RegisterResponse {
        return apiService.register(name, username, email, password)
    }

    suspend fun updateProfile(name: String, username: String, email: String, password: String): UpdateProfileResponse {
        return apiService.updateProfile(name, username, email, password)
    }
}