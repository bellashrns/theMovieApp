package com.bella.week4.domain.model

data class MovieItem(
    val overview: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val video: Boolean? = false,
    val title: String? = "",
    val genreIds: List<Int>,
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val releaseDate: String? = "",
    val popularity: Double? = 0.0,
    val voteAverage: Double? = 0.0,
    val id: Int? = 0,
    val adult: Boolean? = false,
    val voteCount: Int? = 0
)