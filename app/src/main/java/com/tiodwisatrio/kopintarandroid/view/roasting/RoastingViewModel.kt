package com.tiodwisatrio.kopintarandroid.view.roasting

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiodwisatrio.kopintarandroid.data.repository.RoastingRepository
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.IOException

class RoastingViewModel(private val repository: RoastingRepository) : ViewModel() {

    private val _roastingResult = MutableLiveData<Result<RoastingResponse>>()
    val roastingResult: LiveData<Result<RoastingResponse>> get() = _roastingResult

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun uploadImage(image: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                val response = repository.uploadImage(image)
                if (response.isSuccessful) {
                    _roastingResult.value = Result.success(response.body()!!)
                } else {
                    _roastingResult.value = Result.failure(retrofit2.HttpException(response))
                }
            } catch (e: IOException) {
                _roastingResult.value = Result.failure(e)
            }
        }
    }
}