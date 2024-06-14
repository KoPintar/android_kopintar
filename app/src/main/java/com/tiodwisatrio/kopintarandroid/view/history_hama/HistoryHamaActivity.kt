package com.tiodwisatrio.kopintarandroid.view.history_hama

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tiodwisatrio.kopintarandroid.data.adapter.HistoryTypeAdapter
import com.tiodwisatrio.kopintarandroid.data.api.ApiConfig
import com.tiodwisatrio.kopintarandroid.data.repository.PredictRepository
import com.tiodwisatrio.kopintarandroid.data.response.historyType.HistoryTypeResult
import com.tiodwisatrio.kopintarandroid.databinding.ActivityHistoryTypeBinding
import com.tiodwisatrio.kopintarandroid.view.historyType.HistoryTypeViewModel
import com.tiodwisatrio.kopintarandroid.view.historyType.HistoryTypeViewModelFactory

class HistoryHamaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryTypeBinding

    private val viewModel: HistoryTypeViewModel by viewModels {
        HistoryTypeViewModelFactory(PredictRepository(ApiConfig().getApiService(this)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        viewModel.getPredictHistoryType("hama")
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvHistoryType.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHistoryType.addItemDecoration(itemDecoration)
    }

    private fun setListHistoryItem(listUser: List<HistoryTypeResult?>) {
        val adapter = HistoryTypeAdapter()
        adapter.submitList(listUser)
        binding.rvHistoryType.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.historyTypeList.observe(this) { result ->
            result.fold(
                onSuccess = {
                    setListHistoryItem(it)
                    Log.d(TAG, "observeViewModel: $it")
                    Toast.makeText(this, "Berhasil mendapatkan history", Toast.LENGTH_SHORT).show()
                },
                onFailure = { exception ->
                    // Handle update profile error
                    Toast.makeText(this, "Get History Data Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.progressCircular.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val TAG = "HistoryTypeActivity"
    }
}