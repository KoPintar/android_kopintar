package com.tiodwisatrio.kopintarandroid.view
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.tiodwisatrio.kopintarandroid.data.di.Injection
//import com.tiodwisatrio.kopintarandroid.data.repository.AuthRepository
//import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
//import com.tiodwisatrio.kopintarandroid.view.login.LoginViewModel
//
//class ViewModelFactory(
//    private val authRepository: AuthRepository
//) : ViewModelProvider.NewInstanceFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
//                LoginViewModel(authRepository) as T
//            }
//
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//
//        @JvmStatic
//        fun getInstance(context: Context): ViewModelFactory {
//            val authRepository = Injection.provideAuthRepository(context)
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: ViewModelFactory(authRepository)
//            }.also { INSTANCE = it }
//        }
//    }
//}