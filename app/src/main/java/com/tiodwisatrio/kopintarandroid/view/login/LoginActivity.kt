package com.tiodwisatrio.kopintarandroid.view.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tiodwisatrio.kopintarandroid.R
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
                    showToast("Berhasil Masuk")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                onFailure = { exception ->
                    showToast("Gagal: ${exception.message}")
                }
            )
        }

        viewModel.forgotPasswordResult.observe(this) { result ->
            result.fold(
                onSuccess = {
                    // Handle successful forgot password
                    toggleForgetPassword(false)
                    showToast("Berhasil kirim ke Email")
                },
                onFailure = { exception ->
                    // Handle forgot password error
                    showToast("Gagal: ${exception.message}")
                }
            )
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.btnLogin.text = getString(R.string.action_loading)
            } else {
                binding.btnLogin.text = getString(R.string.action_login)
            }
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

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (password.length >= 8) {
                    viewModel.login(username, password)
                } else {
                    showToast(getString(R.string.error_password_length))
                }
            } else {
                showToast(getString(R.string.error_empty_field))
            }
        }

        binding.btnForgetPassword.setOnClickListener { toggleForgetPassword(true) }

        binding.btnSendEmail.setOnClickListener {
            val email = binding.edEmail.text.toString()

            if (email.isNotEmpty()) {
                viewModel.forgotPassword(email)
            } else {
                showToast(getString(R.string.error_empty_field))
            }
        }

        binding.btnBack.setOnClickListener { toggleForgetPassword(false) }
    }

    private fun toggleForgetPassword(isForgetPassword: Boolean) {
        binding.layoutEmail.visibility = if (isForgetPassword) View.VISIBLE else View.GONE
        binding.btnSendEmail.visibility = if (isForgetPassword) View.VISIBLE else View.GONE
        binding.btnBack.visibility = if (isForgetPassword) View.VISIBLE else View.GONE

        binding.title.text = if (isForgetPassword) getString(R.string.forgot_password_title) else getString(R.string.login_title)
        binding.description.text = if (isForgetPassword) getString(R.string.forgot_password_description) else getString(R.string.login_description)

        binding.btnForgetPassword.visibility = if (!isForgetPassword) View.VISIBLE else View.GONE
        binding.btnLogin.visibility = if (!isForgetPassword) View.VISIBLE else View.GONE
        binding.btnRegister.visibility = if (!isForgetPassword) View.VISIBLE else View.GONE
        binding.layoutUsername.visibility = if (!isForgetPassword) View.VISIBLE else View.GONE
        binding.layoutPassword.visibility = if (!isForgetPassword) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}