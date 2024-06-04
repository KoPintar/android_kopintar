package com.tiodwisatrio.kopintarandroid.view.login

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiodwisatrio.kopintarandroid.data.model.UserModel
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModel() {
    private val _loginResult = MutableLiveData<Result<UserModel>>()
    val loginResult: LiveData<Result<UserModel>> get() = _loginResult

    fun login(username: String, password: String) {
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
                } else {
                    _loginResult.value = Result.failure(Exception(response.message))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}