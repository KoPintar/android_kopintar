package com.tiodwisatrio.kopintarandroid.view.hama

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository
import com.tiodwisatrio.kopintarandroid.data.response.hama.HamaResult
import com.tiodwisatrio.kopintarandroid.reduceFileImage
import com.tiodwisatrio.kopintarandroid.uriToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class HamaViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    private val _predictResult = MutableLiveData<Result<HamaResult>>()
    val predictResult: LiveData<Result<HamaResult>> get() = _predictResult

    fun predictHama(imageUri: Uri, context: Context) {
        imageUri.let { uri: Uri ->
            val imageFile = uriToFile(uri, context).reduceFileImage()

            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val image = MultipartBody.Part.createFormData(
                "image",
                imageFile.name,
                requestImageFile
            )

            viewModelScope.launch {
                try {
                    val response = predictRepository.predictHama(image)
                    if (response.success == true && response.data != null) {
                        _predictResult.value = Result.success(response.data)
                    } else {
                        _predictResult.value = Result.failure(Exception(response.message))
                    }
                } catch (e: Exception) {
                    _predictResult.value = Result.failure(e)
                }
            }
        }
    }
}