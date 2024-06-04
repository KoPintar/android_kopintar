package com.tiodwisatrio.kopintarandroid.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tiodwisatrio.kopintarandroid.data.model.UserModel
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences

class MainViewModel(userPreferences: UserPreferences) : ViewModel() {
    val userSession: LiveData<UserModel> = userPreferences.observeUser()
}