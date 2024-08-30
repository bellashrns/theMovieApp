package com.bella.week4.domain.model

data class MovieItem(
    val id: Int? = 0,
    val overview: String? = "",
    val title: String? = "",
    val posterPath: String? = "",
    val backdropPath: String? = "",
    val releaseDate: String? = ""
)