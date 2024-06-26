package com.tiodwisatrio.kopintarandroid.view.historyType

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository
import com.tiodwisatrio.kopintarandroid.data.response.ErrorResponse
import com.tiodwisatrio.kopintarandroid.data.response.historyType.HistoryTypeResult
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HistoryTypeViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    private val _historyTypeList = MutableLiveData<Result<List<HistoryTypeResult?>>>()
    val historyTypeList: LiveData<Result<List<HistoryTypeResult?>>> get() = _historyTypeList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getPredictHistoryType(type: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = predictRepository.getPredictHistoryType(type)
                if (response.success == true && response.data != null) {
                    _historyTypeList.value = Result.success(response.data)
                } else {
                    _historyTypeList.value = Result.failure(Exception(response.message))
                }
            } catch (e: HttpException) {
                val jsonString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
                _historyTypeList.value = Result.failure(Exception(errorBody.message))
            } catch (e: Exception) {
                _historyTypeList.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}