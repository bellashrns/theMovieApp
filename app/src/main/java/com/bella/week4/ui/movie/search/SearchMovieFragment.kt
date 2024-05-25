package com.bella.week4.ui.movie.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bella.week4.databinding.FragmentSearchMovieBinding
import com.bella.week4.extension.gone
import com.bella.week4.extension.visible
import com.bella.week4.ui.core.BaseFragment
import com.bella.week4.ui.movie.detail.MovieDetailActivity
import com.bella.week4.ui.movie.search.adapter.SearchMovieAdapter
import kotlinx.coroutines.launch

class SearchMovieFragment : BaseFragment() {
    override val viewModel: SearchMovieViewModel by viewModels()
    private lateinit var binding: FragmentSearchMovieBinding
    private val adapter by lazy { SearchMovieAdapter(isQuery) }

    private val _isQuery: MutableLiveData<Boolean> = MutableLiveData()
    private val isQuery: LiveData<Boolean> = _isQuery

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        val tvEmpty = binding.tvEmpty

        lifecycleScope.launch {
            viewModel.movies.observe(viewLifecycleOwner) {
                updateIsQuery(false)
                adapter.submitMovieList(it)
                updateUI(it.isEmpty(), recyclerView, tvEmpty)
            }
        }

        val searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    updateIsQuery(false)
                    viewModel.searchMovies(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.clearMovies()
                    searchView.clearFocus()
                    viewModel.getAllQuery()
                }
                return true
            }
        })

        viewModel.queries.observe(viewLifecycleOwner) { searchQueryList ->
            updateIsQuery(true)
            if (searchQueryList != null) {
                adapter.submitQueryList(searchQueryList)
                updateUI(searchQueryList.isEmpty(), recyclerView, tvEmpty)
            }
        }
    }

    private fun updateUI(isListEmpty: Boolean, recyclerView: RecyclerView, tvEmpty: TextView) {
        if (isListEmpty) {
            emptyList(tvEmpty, recyclerView)
            return
        }

        if (isQuery.value == true) {
            showQueryList(tvEmpty, recyclerView)
            return
        }

        showMovieList(tvEmpty, recyclerView)
    }

    private fun showMovieList(tvEmpty: TextView, recyclerView: RecyclerView) {
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

    private fun showQueryList(tvEmpty: TextView, recyclerView: RecyclerView) {
        tvEmpty.gone()
        recyclerView.visible()

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun emptyList(tvEmpty: TextView, recyclerView: RecyclerView) {
        tvEmpty.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    fun updateIsQuery(newValue: Boolean) {
        _isQuery.value = newValue
    }

    companion object {
        const val TAG = "Search"
    }
}