package com.tiodwisatrio.kopintarandroid.view.roasting

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.data.api.ApiConfig
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResult
import com.tiodwisatrio.kopintarandroid.databinding.ActivityRoastingBinding
import com.tiodwisatrio.kopintarandroid.getImageUri
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.home.MainActivity
import com.tiodwisatrio.kopintarandroid.view.profile.ProfileActivity
import com.tiodwisatrio.kopintarandroid.view.roasting_result.RoastingResultActivity

class RoastingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoastingBinding

    private val viewModel: RoastingViewModel by viewModels {
        RoastingViewModelFactory(PredictRepository(ApiConfig().getApiService(this)))
    }

    private var currentImageUri: Uri? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRoastingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
        setupAction()
    }

    private fun setupAction() {
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

        binding.bottomNavigation.menu.findItem(R.id.navigation_roasting).isChecked = true

        binding.btnCamera.setOnClickListener { startCamera() }
        binding.btnGallery.setOnClickListener { startGallery() }
        binding.btnAnalyze.setOnClickListener { predictImage() }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            showToast("Tidak ada gambar terpilih")
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun predictImage() {
        val image = currentImageUri
        if (image != null) {
            binding.progressBar.visibility = View.VISIBLE

            viewModel.predictRoasting(image, this)
        } else {
            showToast("Pilih gambar terlebih dahulu")
        }
    }

    private fun observeViewModel() {
        viewModel.predictResult.observe(this) { result ->
            result.fold(
                onSuccess = {
                    // Handle successful predict, navigate to the next activity, etc.
                    val dataResult = RoastingResult(
                        result = it.result,
                        classResult = it.classResult,
                        confidenceScore = it.confidenceScore
                    )

                    showToast("Prediksi berhasil")
                    val intent = Intent(this, RoastingResultActivity::class.java)

                    intent.putExtra(RoastingResultActivity.EXTRA_RESULT, dataResult)
                    intent.putExtra(RoastingResultActivity.EXTRA_IMAGE, currentImageUri)

                    startActivity(intent)
                    binding.progressBar.visibility = View.GONE

                },
                onFailure = { exception ->
                    // Handle predict error
                    Toast.makeText(this, "Predict Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE

                }
            )
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.imagePreview.setImageURI(it)
        }
    }



    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}