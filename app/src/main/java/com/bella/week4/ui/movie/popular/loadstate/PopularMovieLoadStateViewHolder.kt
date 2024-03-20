package com.bella.week4.ui.movie.popular.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.databinding.LoadStateFooterItemBinding
import java.io.IOException

class PopularMovieLoadStateViewHolder(private val binding: LoadStateFooterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(error: Throwable?) {
        binding.errorMsg.isVisible =
            error is IOException
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PopularMovieLoadStateViewHolder {
            val binding = LoadStateFooterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PopularMovieLoadStateViewHolder(binding)
        }
    }
}