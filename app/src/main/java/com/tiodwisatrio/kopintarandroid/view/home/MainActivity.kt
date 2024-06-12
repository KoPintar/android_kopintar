package com.tiodwisatrio.kopintarandroid.view.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.databinding.ActivityMainBinding
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.historyType.HistoryTypeActivity
import com.tiodwisatrio.kopintarandroid.view.login.LoginActivity
import com.tiodwisatrio.kopintarandroid.view.profile.ProfileActivity
import com.tiodwisatrio.kopintarandroid.view.roasting.RoastingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(UserPreferences(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        observeViewModel()

        binding.historyButton.setOnClickListener {
            startActivity(Intent(this, HistoryTypeActivity::class.java))
        }
    }

    private fun setupBottomNavigation() {

        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(binding.bottomNavigation, "bottom_navigation"),
            )

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle home action
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
                    startActivity(
                        Intent(this, ProfileActivity::class.java),
                        optionsCompat.toBundle()
                    )
                    true
                }

                else -> false
            }
        }

        binding.bottomNavigation.menu.findItem(R.id.navigation_home).isChecked = true
    }

    private fun observeViewModel() {
        viewModel.userSession.observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}