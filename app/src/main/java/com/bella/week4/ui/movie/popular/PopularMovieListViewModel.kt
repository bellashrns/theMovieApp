package com.bella.week4.ui.movie.popular

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bella.week4.data.network.repository.MovieNetworkRepository
import com.bella.week4.domain.model.MovieItem
import com.bella.week4.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(
    repository: MovieNetworkRepository
) : BaseViewModel() {
    val movies: Flow<PagingData<MovieItem>> = repository.getMovies().cachedIn(viewModelScope)
}