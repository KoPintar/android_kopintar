package com.tiodwisatrio.kopintarandroid.data.model

data class UserModel (
    val id: String,
    val name: String,
    val email: String,
    val username: String,
    val profileImg: String,
    val token: String,
    val isLogin: Boolean,
)