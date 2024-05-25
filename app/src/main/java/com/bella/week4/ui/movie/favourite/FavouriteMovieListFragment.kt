package com.bella.week4.ui.movie.favourite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.databinding.FragmentFavoriteMovieListBinding
import com.bella.week4.extension.gone
import com.bella.week4.extension.visible
import com.bella.week4.ui.core.BaseFragment
import com.bella.week4.ui.movie.detail.MovieDetailActivity
import com.bella.week4.ui.movie.favourite.adapter.FavouriteMovieListAdapter
import kotlinx.coroutines.launch

class FavouriteMovieListFragment : BaseFragment() {
    override val viewModel: FavouriteMovieListViewModel by viewModels()
    private lateinit var binding: FragmentFavoriteMovieListBinding
    private val adapter = FavouriteMovieListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val tvEmpty = binding.tvEmpty

        lifecycleScope.launch {
            viewModel.getFavouriteMovies().observe(viewLifecycleOwner) { movies ->
                val isListEmpty = movies.isEmpty()
                updateUI(isListEmpty, tvEmpty, recyclerView)

                adapter.submitList(movies)
            }
        }
    }

    private fun updateUI(
        isListEmpty: Boolean,
        tvEmpty: TextView,
        recyclerView: RecyclerView
    ) {
        if (isListEmpty) {
            emptyList(tvEmpty, recyclerView)
        } else {
            showList(tvEmpty, recyclerView)
        }
    }

    private fun emptyList(tvEmpty: TextView, recyclerView: RecyclerView) {
        tvEmpty.visible()
        recyclerView.gone()
    }

    private fun showList(tvEmpty: TextView, recyclerView: RecyclerView) {
        tvEmpty.gone()
        recyclerView.visible()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 2).also {
            it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == adapter.itemCount) 2 else 1
                }
            }
        }

        adapter.onClick = { _, movie ->
            val bundle = Bundle().apply {
                putString(MovieDetailActivity.MOVIE_ID, movie.id.toString())
                putString(MovieDetailActivity.MOVIE_BACKDROP_PATH, movie.backdropPath)
                putString(MovieDetailActivity.MOVIE_TITLE, movie.title)
                putString(MovieDetailActivity.MOVIE_RELEASE_DATE, movie.releaseDate)
                putString(MovieDetailActivity.MOVIE_OVERVIEW, movie.overview)
                putString(MovieDetailActivity.MOVIE_POSTER_PATH, movie.posterPath)
            }

            val intent = Intent(requireContext(), MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.MOVIE_BUNDLE, bundle)
            startActivity(intent)
        }
    }

    companion object {
        const val TAG = "Favourite"
    }
}