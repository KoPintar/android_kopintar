package com.tiodwisatrio.kopintarandroid.data.pref

import android.content.Context
import com.tiodwisatrio.kopintarandroid.data.model.UserModel


class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(user: UserModel) {
        sharedPreferences.edit().apply {
            putString("id", user.id)
            putString("name", user.name)
            putString("email", user.email)
            putString("username", user.username)
            putString("password", user.password)
            putString("token", user.token)
            putBoolean("isLogin", user.isLogin)
            apply()
        }
    }

    fun getUser(): UserModel {
        return UserModel(
            id = sharedPreferences.getString("id", "") ?: "",
            name = sharedPreferences.getString("name", "") ?: "",
            email = sharedPreferences.getString("email", "") ?: "",
            username = sharedPreferences.getString("username", "") ?: "",
            password = sharedPreferences.getString("password", "") ?: "",
            token = sharedPreferences.getString("token", "") ?: "",
            isLogin = sharedPreferences.getBoolean("isLogin", false)
        )
    }

    fun clearUser() {
        sharedPreferences.edit().clear().apply()
    }
}