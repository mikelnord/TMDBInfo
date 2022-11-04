package com.android.gb.tmdbinfo.network.api

import com.android.gb.tmdbinfo.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/movie/top_rated")
    suspend fun getFilmTopRated(
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesList
}