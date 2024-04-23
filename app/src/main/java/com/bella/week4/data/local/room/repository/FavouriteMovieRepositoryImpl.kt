package com.bella.week4.data.local.room.repository

import androidx.lifecycle.LiveData
import com.bella.week4.data.local.room.MovieDao
import com.bella.week4.data.local.room.entity.MovieEntity
import com.bella.week4.domain.repository.FavouriteMovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavouriteMovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
) : FavouriteMovieRepository {
    override suspend fun insertFavouriteMovie(movie: MovieEntity) {
        movieDao.insertFavouriteMovie(movie)
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        return movieDao.getAllMovies()
    }

    override suspend fun getMovieById(id: Int): Boolean {
        return movieDao.getMovieById(id)
    }

    override suspend fun deleteFavouriteMovie(movie: MovieEntity) {
        movieDao.deleteFavouriteMovie(movie)
    }

}
