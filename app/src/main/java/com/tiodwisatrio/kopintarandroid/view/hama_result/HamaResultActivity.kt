package com.tiodwisatrio.kopintarandroid.view.hama_result

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tiodwisatrio.kopintarandroid.data.response.hama.HamaResult
import com.tiodwisatrio.kopintarandroid.databinding.ActivityHamaResultBinding

class HamaResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHamaResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHamaResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {
        val hamaResult: HamaResult? = intent.getParcelableExtra(EXTRA_RESULT)
        val imageUri: Uri? = intent.getParcelableExtra(EXTRA_IMAGE)

        binding.resultImage.setImageURI(imageUri)
        binding.titlePenyakit.text = "Penyakit: ${hamaResult?.result}"
        val confidenceScorePercentage = hamaResult?.confidenceScore?.times(100)?.toInt()
        binding.akurasiPenyakit.text = "Akurasi: ${confidenceScorePercentage}%"
    }


    companion object {
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_RESULT = "extra_result"
    }
}