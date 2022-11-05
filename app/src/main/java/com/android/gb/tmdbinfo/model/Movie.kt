package com.android.gb.tmdbinfo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val genre_ids: List<Int>
) : Parcelable

data class MoviesList(
    val results: List<Movie>,
    val total_results: Int,
    val page: Int,
    val total_pages: Int
)
