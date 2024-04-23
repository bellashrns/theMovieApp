package com.bella.week4.ui.movie.favourite.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bella.week4.data.local.room.entity.MovieEntity

class FavouriteMovieListAdapter(
    var onClick: ((Int, MovieEntity) -> Unit)? = null
) : ListAdapter<MovieEntity, FavouriteMovieListViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteMovieListViewHolder {
        return FavouriteMovieListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavouriteMovieListViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)

        holder.itemView.setOnClickListener {
            onClick?.invoke(position, movieItem)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}