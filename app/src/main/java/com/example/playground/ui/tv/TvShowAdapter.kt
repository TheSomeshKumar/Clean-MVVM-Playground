package com.example.playground.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playground.Constants
import com.example.playground.databinding.ItemRowBinding
import com.example.playground.domain.model.TvShow

class TvShowAdapter(private val items: List<TvShow>, private val itemClick: (TvShow) -> Unit) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) = holder.bindView(items[position])

    override fun getItemCount(): Int = items.size

    class TvShowViewHolder(private val binding: ItemRowBinding, val itemClick: (TvShow) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: TvShow) {
            with(item) {
                binding.tvName.text = name
                Glide.with(binding.ivImage).load("${Constants.TMDB_POSTER_PATH}${posterPath}").into(binding.ivImage)
                binding.root.setOnClickListener { itemClick(this) }
            }
        }
    }
}
