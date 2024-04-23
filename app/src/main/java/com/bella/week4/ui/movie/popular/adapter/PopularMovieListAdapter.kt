package com.bella.week4.ui.movie.popular.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bella.week4.domain.model.MovieItem

class PopularMovieListAdapter(
    var onClick: ((Int, MovieItem) -> Unit)? = null
) : PagingDataAdapter<MovieItem, PopularMovieListViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: PopularMovieListViewHolder, position: Int) {
        val movieItem = getItem(position)
        if (movieItem != null) {
            holder.bind(movieItem)
        }

        holder.itemView.setOnClickListener {
            if (movieItem == null) {
                return@setOnClickListener
            }

            onClick?.invoke(position, movieItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieListViewHolder {
        return PopularMovieListViewHolder.create(parent)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}