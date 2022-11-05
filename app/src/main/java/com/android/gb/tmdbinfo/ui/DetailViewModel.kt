package com.android.gb.tmdbinfo.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.gb.tmdbinfo.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val movie: Movie = savedStateHandle.get<Movie>(MOVIE_ID_SAVED_STATE_KEY)!!

    companion object {
        private const val MOVIE_ID_SAVED_STATE_KEY = "movie"
    }

}