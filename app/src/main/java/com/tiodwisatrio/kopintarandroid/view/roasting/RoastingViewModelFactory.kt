package com.tiodwisatrio.kopintarandroid.view.roasting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.repository.RoastingRepository

class RoastingViewModelFactory(private val repository: RoastingRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoastingViewModel::class.java)) {
            return RoastingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}