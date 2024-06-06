package com.tiodwisatrio.kopintarandroid.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tiodwisatrio.kopintarandroid.data.api.ApiConfig
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.databinding.ActivityLoginBinding
import com.tiodwisatrio.kopintarandroid.view.home.MainActivity
import com.tiodwisatrio.kopintarandroid.view.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(UserRepository(ApiConfig().getApiService(this)), UserPreferences(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupAction()
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { result ->
            result.fold(
                onSuccess = {
                    // Handle successful login, navigate to the next activity, etc.
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onFailure = { exception ->
                    // Handle login error
                    Toast.makeText(this, "Login Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.edUsername.text.toString()
            val password = binding.edPassword.text.toString()
            viewModel.login(username, password)
        }
    }
}