package com.bella.week4.ui.movie.popular.loadstate

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PopularMovieLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PopularMovieLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PopularMovieLoadStateViewHolder, loadState: LoadState) {
        if (loadState is LoadState.Error) {
            holder.bind(loadState.error)
        } else {
            holder.bind(null)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PopularMovieLoadStateViewHolder {
        return PopularMovieLoadStateViewHolder.create(parent, retry)
    }
}