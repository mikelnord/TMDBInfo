package com.android.gb.tmdbinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
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
//        val header = LoadStateAdapter { adapter.retry() }
//        binding.recyclerResult.adapter = adapter.withLoadStateHeaderAndFooter(
//            header = header,
//            footer = LoadStateAdapter { adapter.retry() }
//        )
        binding.recyclerResult.adapter = adapter
        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest {
                adapter.submitData(it)
            }
        }

//        lifecycleScope.launch {
//            adapter.loadStateFlow.collect { loadState ->
//                header.loadState = loadState.mediator
//                    ?.refresh
//                    ?.takeIf { it is LoadState.Error && adapter.itemCount > 0 }
//                    ?: loadState.prepend
//
//                val isListEmpty =
//                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
//                if (_binding != null) {
//                    binding.emptyList.isVisible = isListEmpty
//                    binding.recyclerResult.isVisible =
//                        loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading
//                    binding.loading.isVisible = loadState.mediator?.refresh is LoadState.Loading
//                    binding.retryButton.isVisible =
//                        loadState.mediator?.refresh is LoadState.Error && adapter.itemCount == 0
//                }
//                val errorState = loadState.source.append as? LoadState.Error
//                    ?: loadState.source.prepend as? LoadState.Error
//                    ?: loadState.append as? LoadState.Error
//                    ?: loadState.prepend as? LoadState.Error
//                errorState?.let {
//                    Toast.makeText(
//                        context,
//                        "\uD83D\uDE28 Wooops ${it.error}",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}