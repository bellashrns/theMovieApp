package com.bella.week4.ui.movie.popular

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bella.week4.databinding.FragmentPopularMovieListBinding
import com.bella.week4.ui.core.BaseFragment
import com.bella.week4.ui.movie.detail.MovieDetailActivity
import com.bella.week4.ui.movie.popular.adapter.PopularMovieListAdapter
import com.bella.week4.ui.movie.loadstate.MovieLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PopularMovieListFragment : BaseFragment() {

    private lateinit var binding: FragmentPopularMovieListBinding
    override val viewModel: PopularMovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PopularMovieListAdapter()
        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter()
        )

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

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }
    }

    companion object {
        const val TAG = "Popular"
    }

}