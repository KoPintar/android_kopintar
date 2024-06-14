package com.tiodwisatrio.kopintarandroid.view.roasting_result

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResult
import com.tiodwisatrio.kopintarandroid.databinding.ActivityRoastingResultBinding

class RoastingResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoastingResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoastingResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        val roastingResult: RoastingResult? = intent.getParcelableExtra(RoastingResultActivity.EXTRA_RESULT)
        val imageUri: Uri? = intent.getParcelableExtra(RoastingResultActivity.EXTRA_IMAGE)

        binding.resultImage.setImageURI(imageUri)
        binding.titleRoasting.text = "Profile Roasting: ${roastingResult?.result}"

//        val confidenceScorePercentage = roastingResult?.confidenceScore?.times(100)
//        binding.akurasiRoasting.text = "Akurasi: ${confidenceScorePercentage?.toInt()}%"
    }

    companion object {
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_IMAGE = "extra_image"
    }
}