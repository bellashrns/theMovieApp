package com.bella.week4.ui.movie.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.bella.week4.BuildConfig
import com.bella.week4.R
import com.bella.week4.data.local.room.entity.MovieEntity
import com.bella.week4.databinding.ActivityMovieDetailBinding
import com.bella.week4.ui.core.BaseActivity
import com.bumptech.glide.Glide

class MovieDetailActivity : BaseActivity() {
    override val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieBundle = intent.getBundleExtra(MOVIE_BUNDLE)

        val movieBackdropPath = movieBundle?.getString(MOVIE_BACKDROP_PATH) ?: ""
        val movieTitle = movieBundle?.getString(MOVIE_TITLE) ?: ""
        val movieReleaseDate = movieBundle?.getString(MOVIE_RELEASE_DATE) ?: ""
        val movieOverview = movieBundle?.getString(MOVIE_OVERVIEW) ?: ""
        val moviePosterPath = movieBundle?.getString(MOVIE_POSTER_PATH) ?: ""
        val movieId = movieBundle?.getString(MOVIE_ID)
        val currentId = movieId?.toIntOrNull() ?: 0

        val currentMovie = MovieEntity(
            currentId,
            moviePosterPath,
            movieTitle,
            movieReleaseDate,
            movieOverview,
            movieBackdropPath
        )

        viewModel.checkIsFavourite(currentId)

        binding.favouriteButton.setOnClickListener {
            viewModel.toggleFavourite(currentMovie)
        }

        binding.apply {
            Glide.with(root)
                .load(BuildConfig.IMAGE_URL + movieBackdropPath)
                .into(binding.movieBackdropIv)
            titleTv.text = movieTitle
            releaseDateTv.text = movieReleaseDate
            overviewTv.text = movieOverview
        }

        binding.toolbar.setOnClickListener {
            finish()
        }

        viewModel.isFavourite.observe(this) {
            viewModel.checkIsFavourite(currentId)
            updateUI(it)
        }
    }

    private fun updateUI(isFavourite: Boolean) {
        binding.favouriteButton.setImageResource(
            if (isFavourite) R.drawable.ic_favorite else R.drawable.ic_unfavorite
        )
    }

    companion object {
        const val MOVIE_ID = "id"
        const val MOVIE_BACKDROP_PATH = "backdropPath"
        const val MOVIE_POSTER_PATH = "posterPath"
        const val MOVIE_TITLE = "title"
        const val MOVIE_RELEASE_DATE = "releaseDate"
        const val MOVIE_OVERVIEW = "overview"
        const val MOVIE_BUNDLE = "movieBundle"
    }
}
