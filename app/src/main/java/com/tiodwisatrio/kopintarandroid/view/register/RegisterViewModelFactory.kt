package com.tiodwisatrio.kopintarandroid.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository

class RegisterViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}