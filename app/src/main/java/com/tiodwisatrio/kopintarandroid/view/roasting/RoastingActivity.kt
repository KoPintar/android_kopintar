package com.tiodwisatrio.kopintarandroid.view.roasting

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.net.toFile
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tiodwisatrio.kopintarandroid.R
import com.tiodwisatrio.kopintarandroid.data.api.ApiConfig
import com.tiodwisatrio.kopintarandroid.data.pref.UserPreferences
import com.tiodwisatrio.kopintarandroid.data.repository.RoastingRepository
import com.tiodwisatrio.kopintarandroid.data.repository.UserRepository
import com.tiodwisatrio.kopintarandroid.data.response.roasting.RoastingResponse
import com.tiodwisatrio.kopintarandroid.databinding.ActivityRoastingBinding
import com.tiodwisatrio.kopintarandroid.view.hama.HamaActivity
import com.tiodwisatrio.kopintarandroid.view.home.MainActivity
import com.tiodwisatrio.kopintarandroid.view.login.LoginViewModel
import com.tiodwisatrio.kopintarandroid.view.login.LoginViewModelFactory
import com.tiodwisatrio.kopintarandroid.view.profile.ProfileActivity
import com.tiodwisatrio.kopintarandroid.view.roasting_result.RoastingResultActivity
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class RoastingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoastingBinding
    private val viewModel: RoastingViewModel by viewModels {
        RoastingViewModelFactory(RoastingRepository(ApiConfig.getApiService()))
    }
    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRoastingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedImageUri?.let { uri ->
            Log.d("RoastingActivity", "Selected Image Uri: $uri")
            displaySelectedImage(uri)
        } ?: run {
            Log.d("RoastingActivity", "Selected Image Uri is null.")
        }

        selectedImageUri?.let { displaySelectedImage(it) }

        viewModel.roastingResult.observe(this, Observer { result ->
            result.fold(
                onSuccess = { roastingResponse ->
                    navigateToResultActivity(roastingResponse)
                },
                onFailure = { exception ->
                    showToast("Error: ${exception.message}")
                }
            )
        })

        val optionsCompat: ActivityOptionsCompat =
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(binding.bottomNavigation, "bottom_navigation"),
            )

        binding.btnGallery.setOnClickListener() {
            onPickImageClick()
        }

        binding.btnAnalyze.setOnClickListener {
            onResultClick()
        }

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
    }

    private fun onPickImageClick() {
        pickImageLauncher.launch("image/*")
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun onResultClick() {
        selectedImageUri?.let { uri ->
            val file = uri.toFile()
            val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("image", file.name, requestBody)
            viewModel.uploadImage(body)
        } ?: showToast("Please select an image first.")
    }

    private fun displaySelectedImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(binding.imagePreview)
    }


    private fun navigateToResultActivity(roastingResponse: RoastingResponse) {
        val intent = Intent(this, RoastingResultActivity::class.java).apply {
            putExtra("result", roastingResponse.data?.result)
            putExtra("classResult", roastingResponse.data?.classResult)
            putExtra("confidenceScore", roastingResponse.data?.confidenceScore)
        }
        startActivity(intent)
    }

//    private fun showDefaultImage() {
//        binding.previewImageView.setImageResource(R.drawable.ic_place_holder)
//    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}