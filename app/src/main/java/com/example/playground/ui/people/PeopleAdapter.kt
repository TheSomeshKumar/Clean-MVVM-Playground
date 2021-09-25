package com.example.playground.ui.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playground.Constants
import com.example.playground.databinding.ItemRowBinding
import com.example.playground.domain.model.People

class PeopleAdapter(private val items: List<People>, private val itemClick: (People) -> Unit) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) = holder.bindView(items[position])

    override fun getItemCount(): Int = items.size

    class PeopleViewHolder(private val binding: ItemRowBinding, val itemClick: (People) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: People) {
            with(item) {
                binding.tvName.text = name
                Glide.with(binding.ivImage).load("${Constants.TMDB_POSTER_PATH}${profilePath}").into(binding.ivImage)
                binding.root.setOnClickListener { itemClick(this) }
            }
        }
    }
}
