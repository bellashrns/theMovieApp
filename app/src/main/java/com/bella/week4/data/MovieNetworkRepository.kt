package com.bella.week4.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bella.week4.data.network.MovieApi
import com.bella.week4.ui.movie.model.MovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 20

class MovieNetworkRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    fun getMovies(query: String?): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieApi, query) }
        ).flow
    }
}