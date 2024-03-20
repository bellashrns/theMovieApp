package com.bella.week4.ui.movie.popular.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bella.week4.ui.movie.model.MovieItem

class PopularMovieListAdapter : PagingDataAdapter<MovieItem, PopularMovieListViewHolder>(COMPARATOR) {

    private var onClickListener: OnClickListener? = null
    override fun onBindViewHolder(holder: PopularMovieListViewHolder, position: Int) {
        val movieItem = getItem(position)
        if (movieItem != null) {
            holder.bind(movieItem)
        }

        holder.itemView.setOnClickListener {
            if (movieItem != null) {
                onClickListener?.onClick(position, movieItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieListViewHolder {
        return PopularMovieListViewHolder.create(parent)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, movieItem: MovieItem)
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