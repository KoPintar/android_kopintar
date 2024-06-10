package com.tiodwisatrio.kopintarandroid.view.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.viewpager2.widget.ViewPager2
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.databinding.ActivityMainBinding
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.history_hama.HistoryRoastingActivity
import com.tiodwisatrio.kopintarandroid.view.login.LoginActivity
import com.tiodwisatrio.kopintarandroid.view.profile.ProfileActivity
import com.tiodwisatrio.kopintarandroid.view.roasting.RoastingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(UserPreferences(this))
    }

    private val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val adapter = ImagesSliderAdapter(this, images)
        viewPager.adapter = adapter

        setupBottomNavigation()
        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history_pest_detection -> {
                startActivity(Intent(this, HistoryRoastingActivity::class.java))
                true
            }

            R.id.menu_history_roasting -> {
                startActivity(Intent(this, HistoryRoastingActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
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