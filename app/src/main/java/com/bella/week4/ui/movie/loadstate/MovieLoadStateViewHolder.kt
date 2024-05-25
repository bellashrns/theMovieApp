package com.bella.week4.ui.movie.loadstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.databinding.LoadStateFooterItemBinding
import java.io.IOException

class MovieLoadStateViewHolder(private val binding: LoadStateFooterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(error: Throwable?) {
        binding.errorMsgTv.isVisible =
            error is IOException
    }

    companion object {
        fun create(parent: ViewGroup): MovieLoadStateViewHolder {
            val binding = LoadStateFooterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return MovieLoadStateViewHolder(binding)
        }
    }
}