package com.bella.week4.data.model

import com.bella.week4.domain.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MovieItemResponse(
    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("original_language")
    val originalLanguage: String,

    @field:SerializedName("original_title")
    val originalTitle: String,

    @field:SerializedName("video")
    val video: Boolean,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int>,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("adult")
    val adult: Boolean,

    @field:SerializedName("vote_count")
    val voteCount: Int
) {
    companion object {
        fun transform(itemResponse: MovieItemResponse): MovieItem {
            return MovieItem(
                overview = itemResponse.overview,
                originalLanguage = itemResponse.originalLanguage,
                originalTitle = itemResponse.originalTitle,
                video = itemResponse.video,
                title = itemResponse.title,
                genreIds = itemResponse.genreIds,
                posterPath = itemResponse.posterPath,
                backdropPath = itemResponse.backdropPath,
                releaseDate = itemResponse.releaseDate,
                popularity = itemResponse.popularity,
                voteAverage = itemResponse.voteAverage,
                id = itemResponse.id,
                adult = itemResponse.adult,
                voteCount = itemResponse.voteCount
            )
        }
    }
}
