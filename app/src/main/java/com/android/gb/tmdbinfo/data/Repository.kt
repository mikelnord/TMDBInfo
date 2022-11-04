package com.android.gb.tmdbinfo.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.gb.tmdbinfo.model.Movie
import com.android.gb.tmdbinfo.network.api.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val movieService: MovieService
) {

    fun getFilmTopRated(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieService, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }

}