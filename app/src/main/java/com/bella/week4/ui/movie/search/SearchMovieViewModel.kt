package com.bella.week4.ui.movie.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bella.week4.data.local.realm.model.SearchQuery
import com.bella.week4.data.model.MovieItemResponse
import com.bella.week4.data.network.repository.MovieNetworkRepository
import com.bella.week4.ui.core.BaseViewModel
import com.bella.week4.domain.model.MovieItem
import com.bella.week4.domain.usecase.searchHistory.SearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieViewModel @Inject constructor(
    private val movieRepository: MovieNetworkRepository,
    private val searchHistoryUseCase: SearchHistoryUseCase,
    state: SavedStateHandle
) : BaseViewModel() {

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val _movies = MutableLiveData<List<MovieItem>>()
    val movies: LiveData<List<MovieItem>> = _movies

    private val _queries = MutableLiveData<List<SearchQuery>?>()
    val queries: MutableLiveData<List<SearchQuery>?> = _queries

    private val currentQuery = MutableLiveData<String>().apply {
        value = state[CURRENT_QUERY] ?: EMPTY_QUERY
    }

    fun searchMovies(query: String) {
        currentQuery.value = query
        viewModelScope.launch {
            val result = movieRepository.searchMovies(query).results
            val movieList = result.map {
                MovieItemResponse.transform(it)
            }
            _movies.postValue(movieList)
        }
        insertQuery(query)
    }

    fun clearMovies() {
        _movies.postValue(emptyList())
    }

    private fun insertQuery(query: String) {
        viewModelScope.launch {
            searchHistoryUseCase.insertQuery(query)
        }
    }

    fun getAllQuery() {
        viewModelScope.launch {
            val result = searchHistoryUseCase.getSearchHistory()
            _queries.postValue(result)
        }
    }
}