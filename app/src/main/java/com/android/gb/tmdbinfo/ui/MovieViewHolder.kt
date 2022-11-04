package com.android.gb.tmdbinfo.ui

import androidx.recyclerview.widget.RecyclerView
import com.android.gb.tmdbinfo.databinding.MovieViewholderBinding
import com.android.gb.tmdbinfo.model.Movie

class MovieViewHolder(
    private val binding: MovieViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            binding.title.text = movie.title
            binding.description.text = movie.overview
        }
    }
}