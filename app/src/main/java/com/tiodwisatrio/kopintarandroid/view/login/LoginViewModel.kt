package com.tiodwisatrio.kopintarandroid.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiodwisatrio.kopintarandroid.data.model.UserModel
import com.tiodwisatrio.kopintarandroid.data.repository.AuthRepository
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository, private val userRepository: UserRepository): ViewModel() {
    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password)
        }
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }
}