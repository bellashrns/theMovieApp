package com.bella.week4.ui.movie.detail

import android.os.Bundle
import com.bella.week4.BuildConfig
import com.bella.week4.ui.core.BaseActivity
import com.bella.week4.ui.core.BaseViewModel
import com.bella.week4.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide

class MovieDetailActivity() : BaseActivity() {

    override val viewModel: BaseViewModel = BaseViewModel()

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieBackdropPath = intent.getStringExtra(MOVIE_BACKDROP_PATH)
        val movieTitle = intent.getStringExtra(MOVIE_TITLE)
        val movieReleaseDate = intent.getStringExtra(MOVIE_RELEASE_DATE)
        val movieOverview = intent.getStringExtra(MOVIE_OVERVIEW)

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
    }

    companion object {
        const val MOVIE_BACKDROP_PATH = "backdropPath"
        const val MOVIE_TITLE = "title"
        const val MOVIE_RELEASE_DATE = "releaseDate"
        const val MOVIE_OVERVIEW = "overview"
    }
}