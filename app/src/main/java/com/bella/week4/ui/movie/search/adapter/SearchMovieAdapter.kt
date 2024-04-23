package com.bella.week4.ui.movie.search.adapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.R
import com.bella.week4.data.local.realm.model.SearchQuery
import com.bella.week4.domain.model.MovieItem

class SearchMovieAdapter(
    private val isQuery: LiveData<Boolean>,
    var onClick: ((Int, MovieItem) -> Unit)? = null
) : RecyclerView.Adapter<SearchMovieViewHolder>() {

    private var movieList: List<MovieItem> = listOf()
    private var queryList: List<SearchQuery> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        return SearchMovieViewHolder.create(parent, isQuery)
    }

    override fun getItemCount(): Int {
        return if (isQuery.value == true) queryList.size else movieList.size
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        if (queryList.isEmpty() && movieList.isEmpty()) {
            Toast.makeText(holder.itemView.context, R.string.data_is_empty, Toast.LENGTH_SHORT).show()
            return
        }

        if (isQuery.value == true) {
            val queryItem = queryList[position]
            holder.bindQuery(queryItem)
        } else {
            val movieItem = movieList[position]
            holder.bindMovie(movieItem)

            holder.itemView.setOnClickListener {
                onClick?.invoke(position, movieItem)
            }
        }
    }

    fun submitMovieList(movieList: List<MovieItem>) {
        this.movieList = movieList
    }

    fun submitQueryList(queryList: List<SearchQuery>) {
        this.queryList = if (queryList.size > 10) {
            queryList.subList(queryList.size - 10, queryList.size)
        } else {
            queryList
        }
    }

}