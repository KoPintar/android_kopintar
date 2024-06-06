package com.tiodwisatrio.kopintarandroid.view.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository

class UpdateProfileViewModelFactory(private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UpdateProfileViewModel(userRepository, userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}