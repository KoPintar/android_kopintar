package com.tiodwisatrio.kopintarandroid.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.view.login.LoginViewModel

class LoginViewModelFactory(private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userRepository, userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}