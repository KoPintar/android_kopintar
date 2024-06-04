package com.tiodwisatrio.kopintarandroid.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences

class MainViewModelFactory(private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(userPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}