package com.bella.week4.domain.usecase.favouriteMovie

import androidx.lifecycle.LiveData
import com.bella.week4.data.local.room.entity.MovieEntity
import com.bella.week4.domain.repository.FavouriteMovieRepository
import javax.inject.Inject

class FavouriteMovieUseCaseImpl @Inject constructor(
    private val repository: FavouriteMovieRepository
) : FavouriteMovieUseCase {

    override suspend fun insertFavouriteMovie(movie: MovieEntity) {
        repository.insertFavouriteMovie(movie)
    }

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        return repository.getAllMovies()
    }

    override suspend fun getMovieById(id: Int): Boolean {
        return repository.getMovieById(id)
    }

    override suspend fun deleteFavouriteMovie(movie: MovieEntity) {
        repository.deleteFavouriteMovie(movie)
    }
}