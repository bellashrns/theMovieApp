package com.bella.week4.data.model

import com.bella.week4.domain.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MovieItemResponse(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String
) {
    companion object {
        fun transform(itemResponse: MovieItemResponse): MovieItem {
            return MovieItem(
                overview = itemResponse.overview,
                title = itemResponse.title,
                posterPath = itemResponse.posterPath,
                backdropPath = itemResponse.backdropPath,
                releaseDate = itemResponse.releaseDate
            )
        }
    }
}
