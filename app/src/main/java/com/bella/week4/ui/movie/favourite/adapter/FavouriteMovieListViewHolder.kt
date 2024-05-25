package com.bella.week4.ui.movie.favourite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.BuildConfig
import com.bella.week4.data.local.room.entity.MovieEntity
import com.bella.week4.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class FavouriteMovieListViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieEntity?) {
        Glide.with(binding.root)
            .load(BuildConfig.IMAGE_URL + item?.posterPath)
            .into(binding.moviePosterIv)

        binding.movieTitleTv.text = item?.title
    }

    companion object {
        fun create(view: ViewGroup): FavouriteMovieListViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemMovieBinding.inflate(inflater, view, false)
            return FavouriteMovieListViewHolder(binding)
        }
    }
}