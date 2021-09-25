package com.example.playground.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playground.Constants
import com.example.playground.databinding.ItemRowBinding
import com.example.playground.domain.model.Movie

class MovieAdapter(private val items: List<Movie>, private val itemClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bindView(items[position])

    override fun getItemCount(): Int = items.size

    class MovieViewHolder(private val binding: ItemRowBinding, val itemClick: (Movie) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Movie) {
            with(item) {
                binding.tvName.text = title
                Glide.with(binding.ivImage).load("${Constants.TMDB_POSTER_PATH}${posterPath}").into(binding.ivImage)
                binding.root.setOnClickListener { itemClick(this) }
            }
        }
    }
}
