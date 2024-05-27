package com.tiodwisatrio.kopintarandroid.view.home

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.profile.ProfileActivity
import com.tiodwisatrio.kopintarandroid.view.roasting.RoastingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle home action
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
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.menu.findItem(R.id.navigation_home).isChecked = true

    }
}