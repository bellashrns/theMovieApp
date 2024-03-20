package com.bella.week4.ui.movie.popular.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.BuildConfig
import com.bella.week4.databinding.ItemMovieBinding
import com.bella.week4.ui.movie.model.MovieItem
import com.bumptech.glide.Glide

class PopularMovieListViewHolder(
    private val binding: ItemMovieBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieItem?) {
        Glide.with(binding.root)
            .load(BuildConfig.IMAGE_URL + item?.posterPath)
            .into(binding.moviePoster)

        binding.movieTitle.text = item?.title
    }

    companion object {
        fun create(view: ViewGroup): PopularMovieListViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = ItemMovieBinding.inflate(inflater, view, false)
            return PopularMovieListViewHolder(binding)
        }
    }
}