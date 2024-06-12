package com.tiodwisatrio.kopintarandroid.view.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.data.api.ApiConfig
import com.tiodwisatrio.kopintarandroid.data.model.UserModel
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.databinding.ActivityProfileBinding
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.home.MainActivity
import com.tiodwisatrio.kopintarandroid.view.login.LoginActivity
import com.tiodwisatrio.kopintarandroid.view.roasting.RoastingActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    private val viewModel: UpdateProfileViewModel by viewModels {
        UpdateProfileViewModelFactory(UserRepository(ApiConfig().getApiService(this)), UserPreferences(this))
    }

    private lateinit var userPreferences: UserPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(this)

        observeViewModel()
        setupAction()
        displayUserInfo()
    }

    private fun setupAction() {
        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(binding.bottomNavigation, "bottom_navigation"),
            )

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(
                        Intent(this, MainActivity::class.java),
                        optionsCompat.toBundle()
                    )
                    true
                }

                R.id.navigation_hama -> {
                    startActivity(
                        Intent(this, HamaActivity::class.java),
                        optionsCompat.toBundle()
                    )
                    true
                }

                R.id.navigation_roasting -> {
                    startActivity(
                        Intent(this, RoastingActivity::class.java),
                        optionsCompat.toBundle()
                    )
                    true
                }

                R.id.navigation_profile -> {
                    true
                }

                else -> false
            }

        }
        binding.bottomNavigation.menu.findItem(R.id.navigation_profile).isChecked = true

        binding.btnUpdate.setOnClickListener { updateUserInfo() }
        binding.btnLogout.setOnClickListener { logout() }
        binding.btnSave.setOnClickListener { updateProfile() }
        binding.btnBack.setOnClickListener { displayUserInfo() }
    }

    private fun observeViewModel() {
        viewModel.updateProfileResult.observe(this) { result ->
            result.fold(
                onSuccess = {
                    updatedUserInfo(it)
                    Toast.makeText(this, "Berhasil memperbarui profile", Toast.LENGTH_SHORT).show()
                },
                onFailure = { exception ->
                    // Handle update profile error
                    Toast.makeText(this, "Update Profile Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.btnSave.text = getString(R.string.action_loading)
            } else {
                binding.btnSave.text = getString(R.string.action_update)
            }
        }
    }

    private fun displayUserInfo() {
        toggleUpdateMode(false)

        val user = userPreferences.getUser()

        user.let {
            binding.edNamaLengkap.isEnabled = false
            binding.edNamaLengkap.setText(it.name)

            binding.edEmail.isEnabled = false
            binding.edEmail.setText(it.email)

            binding.edUsername.isEnabled = false
            binding.edUsername.setText(it.username)

            binding.edReenterPassword.text.clear()
        }
    }

    private fun updatedUserInfo(newUser: UserModel) {
        toggleUpdateMode(false)

        userPreferences.saveUser(newUser)

        newUser.let {
            binding.edNamaLengkap.isEnabled = false
            binding.edNamaLengkap.setText(it.name)

            binding.edEmail.isEnabled = false
            binding.edEmail.setText(it.email)

            binding.edUsername.isEnabled = false
            binding.edUsername.setText(it.username)

            binding.edReenterPassword.text.clear()
        }
    }

    private fun updateUserInfo() {
        toggleUpdateMode(true)

        val user = userPreferences.getUser()

        user.let {
            binding.edNamaLengkap.isEnabled = true
            binding.edNamaLengkap.setText(it.name)

            binding.edEmail.isEnabled = true
            binding.edEmail.setText(it.email)

            binding.edUsername.isEnabled = true
            binding.edUsername.setText(it.username)

            binding.edPassword.isEnabled = true
            binding.edPassword.setText(it.password)

            binding.edReenterPassword.isEnabled = true
        }
    }

    private fun toggleUpdateMode(isEditMode: Boolean) {
        binding.btnSave.visibility = if (isEditMode) View.VISIBLE else View.GONE
        binding.btnBack.visibility = if (isEditMode) View.VISIBLE else View.GONE
        binding.layoutPassword.visibility = if (isEditMode) View.VISIBLE else View.GONE
        binding.layoutReenterPassword.visibility = if (isEditMode) View.VISIBLE else View.GONE
        binding.btnUpdate.visibility = if (!isEditMode) View.VISIBLE else View.GONE
        binding.btnLogout.visibility = if (!isEditMode) View.VISIBLE else View.GONE
    }

    private fun updateProfile() {
        val name = binding.edNamaLengkap.text.toString()
        val username = binding.edUsername.text.toString()
        val email = binding.edEmail.text.toString()
        val password = binding.edPassword.text.toString()
        val reenterPassword = binding.edReenterPassword.text.toString()

        if (name.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && reenterPassword.isNotEmpty()) {
            if (password == reenterPassword) {
                viewModel.updateProfile(name, username, email, password)
            } else {
                Toast.makeText(this, getString(R.string.error_password_not_match), Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show()
        }
    }

    private fun logout() {
        userPreferences.clearUser()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}