package com.bella.week4.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bella.week4.data.local.room.entity.MovieEntity
import com.bella.week4.domain.usecase.favouriteMovie.FavouriteMovieUseCase
import com.bella.week4.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val favouriteMovieUseCase: FavouriteMovieUseCase
) : BaseViewModel() {

    private val _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> = _isFavourite

    fun toggleFavourite(movie: MovieEntity) {
        viewModelScope.launch {
            if (_isFavourite.value == true) {
                deleteFavouriteMovie(movie)
            } else {
                insertFavouriteMovie(movie)
            }
            checkIsFavourite(movie.id)
        }
    }

    private fun insertFavouriteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            favouriteMovieUseCase.insertFavouriteMovie(movie)
        }
    }

    private fun deleteFavouriteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            favouriteMovieUseCase.deleteFavouriteMovie(movie)
        }
    }

    fun checkIsFavourite(id: Int) {
        viewModelScope.launch {
            val isFavourite = favouriteMovieUseCase.getMovieById(id)
            _isFavourite.value = isFavourite
        }
    }
}