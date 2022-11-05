package com.android.gb.tmdbinfo.ui

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.gb.tmdbinfo.databinding.MovieViewholderBinding
import com.android.gb.tmdbinfo.model.Movie
import com.android.gb.tmdbinfo.util.loadImage

class MovieViewHolder(
    private val binding: MovieViewholderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            binding.title.text = movie.title
            binding.reit.text = movie.popularity.toString()
            binding.imageView.loadImage("http://image.tmdb.org/t/p/w300${movie.poster_path}")
            root.setOnClickListener {
                navigateToMovieDetail(movie, it)
            }
        }

    }

    private fun navigateToMovieDetail(
        movie: Movie,
        view: View
    ) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie)
        view.findNavController().navigate(direction)
    }

}