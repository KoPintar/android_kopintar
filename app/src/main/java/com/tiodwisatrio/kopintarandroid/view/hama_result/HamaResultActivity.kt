package com.tiodwisatrio.kopintarandroid.view.hama_result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiodwisatrio.kopintarandroid.databinding.ActivityHamaResultBinding

class HamaResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHamaResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHamaResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}