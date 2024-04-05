package com.bella.week4.ui.movie.popular

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bella.week4.ui.core.BaseViewModel
import com.bella.week4.data.MovieNetworkRepository
import com.bella.week4.ui.movie.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(
    repository: MovieNetworkRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = MutableStateFlow(state.get<String>(CURRENT_QUERY) ?: EMPTY_QUERY)

    init {
        state.getLiveData(CURRENT_QUERY, EMPTY_QUERY).observeForever {
            currentQuery.value = it ?: EMPTY_QUERY
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val movies: Flow<PagingData<MovieItem>> = currentQuery.flatMapLatest { query ->
        repository.getMovies(query).cachedIn(viewModelScope)
    }

    fun searchMovies(query: String) {
        currentQuery.value = query
    }
}