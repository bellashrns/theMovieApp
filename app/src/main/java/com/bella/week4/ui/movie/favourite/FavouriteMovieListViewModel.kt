package com.bella.week4.ui.movie.favourite

import androidx.lifecycle.LiveData
import com.bella.week4.data.local.room.entity.MovieEntity
import com.bella.week4.domain.usecase.favouriteMovie.FavouriteMovieUseCase
import com.bella.week4.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteMovieListViewModel @Inject constructor(
    private val favouriteMovieUseCase: FavouriteMovieUseCase
) : BaseViewModel() {

    fun getFavouriteMovies(): LiveData<List<MovieEntity>> {
        return favouriteMovieUseCase.getAllMovies()
    }
}