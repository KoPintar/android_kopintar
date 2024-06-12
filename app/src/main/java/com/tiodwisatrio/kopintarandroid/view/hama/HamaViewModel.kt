package com.tiodwisatrio.kopintarandroid.view.hama

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository
import com.tiodwisatrio.kopintarandroid.data.response.disease.DiseaseResult
import com.tiodwisatrio.kopintarandroid.reduceFileImage
import com.tiodwisatrio.kopintarandroid.uriToFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class HamaViewModel(private val predictRepository: PredictRepository) : ViewModel() {
    private val _predictResult = MutableLiveData<Result<DiseaseResult>>()
    val predictResult: LiveData<Result<DiseaseResult>> get() = _predictResult

    fun predictDisease(imageUri: Uri, type: String, context: Context) {
        imageUri.let { uri: Uri ->
            val imageFile = uriToFile(uri, context).reduceFileImage()

            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val image = MultipartBody.Part.createFormData(
                "image",
                imageFile.name,
                requestImageFile
            )

            val typePart = type.toRequestBody("text/plain".toMediaType())

            viewModelScope.launch {
                try {
                    val response = predictRepository.predictDisease(image, typePart)
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