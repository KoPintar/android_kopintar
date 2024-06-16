package com.tiodwisatrio.kopintarandroid.view.profile

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

class UpdateProfileViewModel(private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModel() {
    private val _updateProfileResult = MutableLiveData<Result<UserModel>>()
    val updateProfileResult: LiveData<Result<UserModel>> get() = _updateProfileResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun updateProfile(name: String, username: String, email:String, password: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = userRepository.updateProfile(name, username, email, password)
                if (response.success == true && response.data != null) {
                    val oldUser = userPreferences.getUser()
                    val newUser = UserModel(
                        id = oldUser.id,
                        name = name,
                        username = username,
                        email = email,
                        password = oldUser.password,
                        token = oldUser.token,
                        isLogin = oldUser.isLogin
                    )
                    userPreferences.saveUser(newUser)
                    _updateProfileResult.value = Result.success(newUser)
                } else {
                    _updateProfileResult.value = Result.failure(Exception(response.message))
                }
            } catch (e: HttpException) {
                val jsonString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
                _updateProfileResult.value = Result.failure(Exception(errorBody.message))
            } catch (e: Exception) {
                _updateProfileResult.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}