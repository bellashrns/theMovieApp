package com.bella.week4.domain.repository

import androidx.lifecycle.LiveData
import com.bella.week4.data.local.room.entity.MovieEntity

interface FavouriteMovieRepository {
    suspend fun insertFavouriteMovie(movie: MovieEntity)
    fun getAllMovies(): LiveData<List<MovieEntity>>
    suspend fun getMovieById(id: Int): Boolean
    suspend fun deleteFavouriteMovie(movie: MovieEntity)
}
