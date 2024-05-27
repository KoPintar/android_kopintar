package com.tiodwisatrio.kopintarandroid.view.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.home.MainActivity
import com.tiodwisatrio.kopintarandroid.view.roasting.RoastingActivity

class ProfileActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                R.id.navigation_hama -> {
                    startActivity(Intent(this, HamaActivity::class.java))
                    true
                }
                R.id.navigation_roasting -> {
                    startActivity(Intent(this, RoastingActivity::class.java))
                    true
                }
                R.id.navigation_profile -> {
                    true
                }
                else -> false
            }

        }
        bottomNavigationView.menu.findItem(R.id.navigation_profile).isChecked = true
    }
}