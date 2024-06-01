package com.tiodwisatrio.kopintarandroid.view.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.databinding.ActivityWelcomeBinding
import com.tiodwisatrio.kopintarandroid.view.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener() {
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}