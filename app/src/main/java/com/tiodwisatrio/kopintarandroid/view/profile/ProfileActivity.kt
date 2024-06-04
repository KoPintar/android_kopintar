package com.tiodwisatrio.kopintarandroid.view.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.databinding.ActivityProfileBinding
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.home.MainActivity
import com.tiodwisatrio.kopintarandroid.view.login.LoginActivity
import com.tiodwisatrio.kopintarandroid.view.roasting.RoastingActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var userPreferences: UserPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreferences = UserPreferences(this)

        displayUserInfo()


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

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun displayUserInfo() {
        val user = userPreferences.getUser()
        user?.let {
            binding.edNamaLengkap.isEnabled = false
            binding.edNamaLengkap.setText(it.name)

            binding.edEmail.isEnabled = false
            binding.edEmail.setText(it.email)

            binding.edUsername.isEnabled = false
            binding.edUsername.setText(it.username)
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