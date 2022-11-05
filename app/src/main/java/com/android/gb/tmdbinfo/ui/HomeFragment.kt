package com.android.gb.tmdbinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.android.gb.tmdbinfo.adapter.MovieAdapter
import com.android.gb.tmdbinfo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        val adapter = MovieAdapter()
        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerResult.addItemDecoration(decoration)
        binding.recyclerResult.adapter = adapter
        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest {
                adapter.submitData(it)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}