package com.tiodwisatrio.kopintarandroid.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiodwisatrio.kopintarandroid.data.model.UserModel
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import kotlinx.coroutines.launch

class UpdateProfileViewModel(private val userRepository: UserRepository, private val userPreferences: UserPreferences) : ViewModel() {
    private val _updateProfileResult = MutableLiveData<Result<UserModel>>()
    val updateProfileResult: LiveData<Result<UserModel>> get() = _updateProfileResult

    fun updateProfile(name: String, username: String, email:String, password: String) {
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
            } catch (e: Exception) {
                _updateProfileResult.value = Result.failure(e)
            }
        }
    }
}