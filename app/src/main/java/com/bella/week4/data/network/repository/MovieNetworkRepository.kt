package com.bella.week4.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bella.week4.data.model.MovieResponse
import com.bella.week4.data.pagingSource.PopularMoviePagingSource
import com.bella.week4.data.network.MovieApi
import com.bella.week4.domain.model.MovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20
class MovieNetworkRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    fun getMovies(): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularMoviePagingSource(movieApi) }
        ).flow
    }

    suspend fun searchMovies(query: String): MovieResponse {
        return movieApi.searchMovie(query)
    }
}