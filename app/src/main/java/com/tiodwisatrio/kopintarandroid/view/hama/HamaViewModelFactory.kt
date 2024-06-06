package com.tiodwisatrio.kopintarandroid.view.hama

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository

class HamaViewModelFactory(private val predictRepository: PredictRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HamaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HamaViewModel(predictRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}