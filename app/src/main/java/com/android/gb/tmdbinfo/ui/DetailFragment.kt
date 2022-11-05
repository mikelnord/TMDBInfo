package com.android.gb.tmdbinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.android.gb.tmdbinfo.databinding.FragmentMovieDetailBinding
import com.android.gb.tmdbinfo.util.loadImage

class DetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val movie = viewModel.movie
        binding.tvTitle.text = movie.title
        binding.ivCover.loadImage("http://image.tmdb.org/t/p/w300${movie.poster_path}")
        binding.tvDescription.text = movie.overview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}