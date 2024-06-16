package com.tiodwisatrio.kopintarandroid.view.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.data.response.ErrorResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _registerResult = MutableLiveData<Result<Boolean>>()
    val registerResult: LiveData<Result<Boolean>> get() = _registerResult

    fun register(name: String, username: String, email:String, password: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.register(name, username, email, password)
                if (response.success == true && response.data != null) {
                    _registerResult.value = Result.success(true)
                } else {
                    _registerResult.value = Result.failure(Exception(response.message))
                }
            } catch (e: HttpException) {
                val jsonString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
                _registerResult.value = Result.failure(Exception(errorBody.message))
            } catch (e: Exception) {
                _registerResult.value = Result.failure(e)
            }
        }
    }
}