package com.android.gb.tmdbinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.gb.tmdbinfo.data.Repository
import com.android.gb.tmdbinfo.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_QUERY = "ru"

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var accept: (UiAction) -> Unit
    val pagingDataFlow: Flow<PagingData<Movie>>

    init {
        val initialQuery: String = DEFAULT_QUERY
        val actionStateFlow = MutableSharedFlow<UiAction>(replay = 1)
        val searches = actionStateFlow
            .filterIsInstance<UiAction.Search>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Search(query = initialQuery)) }

        accept = { action ->
            viewModelScope.launch { actionStateFlow.emit(action) }
        }
        pagingDataFlow = searches
            .flatMapLatest { getFilmTopRated(it.query) }
            .cachedIn(viewModelScope)
    }


    private fun getFilmTopRated(queryString: String): Flow<PagingData<Movie>> =
        repository.getFilmTopRated(queryString)
}

sealed class UiAction {
    data class Search(val query: String) : UiAction()
}