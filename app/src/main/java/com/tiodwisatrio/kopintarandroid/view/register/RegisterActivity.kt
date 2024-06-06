package com.tiodwisatrio.kopintarandroid.view.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tiodwisatrio.kopintarandroid.data.api.ApiConfig
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.databinding.ActivityRegisterBinding
import com.tiodwisatrio.kopintarandroid.view.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModelFactory(UserRepository(ApiConfig().getApiService(this)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupAction()
    }

    private fun observeViewModel() {
        viewModel.registerResult.observe(this) { result ->
            result.fold(
                onSuccess = {

                    // Handle successful register, navigate to the next activity, etc.
                    Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                },
                onFailure = { exception ->
                    // Handle register error
                    Toast.makeText(this, "Register Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                },

            )
        }
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener() {
            val name = binding.edNamaLengkap.text.toString()
            val username = binding.edUsername.text.toString()
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            viewModel.register(name, username, email, password)
        }

        binding.btnLogin.setOnClickListener() {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}