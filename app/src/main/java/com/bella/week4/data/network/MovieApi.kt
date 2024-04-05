package com.bella.week4.data.network

import com.bella.week4.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MovieResponse
}