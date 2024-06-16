package com.tiodwisatrio.kopintarandroid.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tiodwisatrio.kopintarandroid.data.model.UserModel
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.data.response.ErrorResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<UserModel>>()
    val loginResult: LiveData<Result<UserModel>> get() = _loginResult

    private val _forgotPasswordResult = MutableLiveData<Result<Boolean>>()
    val forgotPasswordResult: LiveData<Result<Boolean>> get() = _forgotPasswordResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(username: String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = userRepository.login(username, password)
                if (response.success == true && response.data != null) {
                    val user = UserModel(
                        id = response.data.id ?: "",
                        name = response.data.name ?: "",
                        email = response.data.email ?: "",
                        username = response.data.username ?: "",
                        password = password,
                        token = response.data.token ?: "",
                        isLogin = true
                    )
                    userPreferences.saveUser(user)
                    _loginResult.value = Result.success(user)
                }
            } catch (e: HttpException) {
                val jsonString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
                _loginResult.value = Result.failure(Exception(errorBody.message))
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            try {
                val response = userRepository.forgotPassword(email)
                if (response.success == true && response.data != null) {
                    _forgotPasswordResult.value = Result.success(true)
                } else {
                    _forgotPasswordResult.value = Result.failure(Exception(response.message))
                }
            } catch (e: HttpException) {
                val jsonString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
                _forgotPasswordResult.value = Result.failure(Exception(errorBody.message))
            } catch (e: Exception) {
                _forgotPasswordResult.value = Result.failure(e)
            }
        }
    }
}