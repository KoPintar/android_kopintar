package com.tiodwisatrio.kopintarandroid.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tiodwisatrio.kopintarandroid.data.response.historyType.HistoryTypeResult
import com.tiodwisatrio.kopintarandroid.databinding.ItemHistoryTypeBinding

class HistoryTypeAdapter : ListAdapter<HistoryTypeResult, HistoryTypeAdapter.HistoryTypeViewHolder>(DIFF_CALLBACK) {
    class HistoryTypeViewHolder(val binding: ItemHistoryTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(historyItem: HistoryTypeResult) {
            Glide.with(binding.root)
                .load(historyItem.image)
                .into(binding.imageView)
            binding.tvName.text = historyItem.type
            binding.tvDescription.text = historyItem.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryTypeBinding.inflate(inflater, parent, false)
        return HistoryTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryTypeViewHolder, position: Int) {
        val historyItem = getItem(position)
        holder.bind(historyItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryTypeResult>() {
            override fun areItemsTheSame(oldItem: HistoryTypeResult, newItem: HistoryTypeResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HistoryTypeResult, newItem: HistoryTypeResult): Boolean {
                return oldItem == newItem
            }
        }
    }
}