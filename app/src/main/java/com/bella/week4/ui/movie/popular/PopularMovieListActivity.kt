package com.bella.week4.ui.movie.popular

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.ui.core.BaseActivity
import com.bella.week4.databinding.ActivityHomeBinding
import com.bella.week4.ui.movie.model.MovieItem
import com.bella.week4.ui.movie.popular.adapter.PopularMovieListAdapter
import com.bella.week4.ui.movie.popular.loadstate.PopularMovieLoadStateAdapter
import com.bella.week4.ui.movie.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PopularMovieListActivity : BaseActivity() {
    override val viewModel: PopularMovieListViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PopularMovieListAdapter()
        recyclerView = binding.recyclerView

        recyclerView.adapter = adapter.withLoadStateFooter(
            footer = PopularMovieLoadStateAdapter { adapter.retry() }
        )

        recyclerView.layoutManager = GridLayoutManager(this, 2).also {
            it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == adapter.itemCount) 2 else 1
                }
            }
        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

        val searchView = binding.searchView
        searchMovie(searchView)

        adapter.setOnClickListener(object : PopularMovieListAdapter.OnClickListener {
            override fun onClick(position: Int, movieItem: MovieItem) {
                val bundle = Bundle().apply {
                    putString(MovieDetailActivity.MOVIE_BACKDROP_PATH, movieItem.backdropPath)
                    putString(MovieDetailActivity.MOVIE_TITLE, movieItem.title)
                    putString(MovieDetailActivity.MOVIE_RELEASE_DATE, movieItem.releaseDate)
                    putString(MovieDetailActivity.MOVIE_OVERVIEW, movieItem.overview)
                }
                val intent = Intent(this@PopularMovieListActivity, MovieDetailActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }

    private fun searchMovie(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    recyclerView.scrollToPosition(0)
                    viewModel.searchMovies(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}