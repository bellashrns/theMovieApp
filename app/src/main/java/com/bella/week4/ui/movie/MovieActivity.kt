package com.bella.week4.ui.movie

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bella.week4.databinding.ActivityMovieBinding
import com.bella.week4.ui.core.BaseActivity
import com.bella.week4.ui.core.BaseViewModel
import com.bella.week4.ui.movie.favourite.FavouriteMovieListFragment
import com.bella.week4.ui.movie.popular.PopularMovieListFragment
import com.bella.week4.ui.movie.search.SearchMovieFragment
import com.google.android.material.tabs.TabLayoutMediator

class MovieActivity : BaseActivity() {
    override val viewModel: BaseViewModel = BaseViewModel()
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager: ViewPager2 = binding.viewPager

        val fragments = listOf(
            PopularMovieListFragment(),
            FavouriteMovieListFragment(),
            SearchMovieFragment()
        )

        val adapter = ViewPagerAdapter(fragments, supportFragmentManager, this.lifecycle)
        viewPager.adapter = adapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = TabTitle.entries[position].title
        }.attach()
    }
}

enum class TabTitle(val title: String) {
    POPULAR(PopularMovieListFragment.TAG),
    FAVORITE(FavouriteMovieListFragment.TAG),
    SEARCH(SearchMovieFragment.TAG)
}