package com.tiodwisatrio.kopintarandroid.view.historyType

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository

class HistoryTypeViewModelFactory(private val predictRepository: PredictRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryTypeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryTypeViewModel(predictRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}