package com.android.gb.tmdbinfo.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val genre_ids: List<Int>
)

data class MoviesList(
    val results: List<Movie>,
    val total_results: Int,
    val page: Int,
    val total_pages: Int
)
