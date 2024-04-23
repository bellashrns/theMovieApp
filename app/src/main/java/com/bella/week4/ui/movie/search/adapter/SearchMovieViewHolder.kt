package com.bella.week4.ui.movie.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bella.week4.BuildConfig
import com.bella.week4.data.local.realm.model.SearchQuery
import com.bella.week4.databinding.ItemMovieBinding
import com.bella.week4.databinding.ItemQueryBinding
import com.bella.week4.domain.model.MovieItem
import com.bumptech.glide.Glide

class SearchMovieViewHolder(
    private var binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindMovie(item: MovieItem?) {
        Glide.with(binding.root)
            .load(BuildConfig.IMAGE_URL + item?.posterPath)
            .into((binding as ItemMovieBinding).moviePoster)

        (binding as ItemMovieBinding).movieTitle.text = item?.title
    }

    fun bindQuery(query: SearchQuery?) {
        (binding as ItemQueryBinding).tvQuery.text = query?.query
    }

    companion object {
        fun create(view: ViewGroup, isQuery: LiveData<Boolean>): SearchMovieViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = if (isQuery.value == true) {
                ItemQueryBinding.inflate(inflater, view, false)
            } else {
                ItemMovieBinding.inflate(inflater, view, false)
            }
            return SearchMovieViewHolder(binding)
        }
    }
}