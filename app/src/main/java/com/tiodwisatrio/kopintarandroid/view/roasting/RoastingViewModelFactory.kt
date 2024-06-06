package com.tiodwisatrio.kopintarandroid.view.roasting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository

class RoastingViewModelFactory(private val predictRepository: PredictRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoastingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoastingViewModel(predictRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}