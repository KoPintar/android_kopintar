package com.tiodwisatrio.kopintarandroid.view.roasting_result

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.databinding.ActivityRoastingResultBinding

class RoastingResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoastingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoastingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getStringExtra("result")
        val classResult = intent.getIntExtra("classResult", -1)
        val confidenceScore = intent.getDoubleExtra("confidenceScore", -1.0)

        binding.tvResult.text = "Result: $result"
        binding.tvClassResult.text = "Class Result: $classResult"
        binding.tvConfidenceScore.text = "Confidence Score: $confidenceScore"
    }
}