package com.bella.week4.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bella.week4.data.model.MovieItemResponse
import com.bella.week4.data.network.MovieApi
import com.bella.week4.ui.movie.model.MovieItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val service: MovieApi,
    private val query: String?
) : PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = if (query != null && query != "") {
                service.searchMovie(query, page)
            } else {
                service.getMoviePopular(page)
            }
            val movieItemResponse = response.results
            val movieItem = movieItemResponse.map {
                MovieItemResponse.transform(it)
            }

            LoadResult.Page(
                data = movieItem,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (movieItem.isEmpty()) throw IOException("End of Data") else page + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }
}