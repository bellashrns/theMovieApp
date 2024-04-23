package com.bella.week4.ui.di

import com.bella.week4.domain.usecase.favouriteMovie.FavouriteMovieUseCase
import com.bella.week4.domain.usecase.favouriteMovie.FavouriteMovieUseCaseImpl
import com.bella.week4.domain.usecase.searchHistory.SearchHistoryUseCase
import com.bella.week4.domain.usecase.searchHistory.SearchHistoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideSearchHistoryUseCase(
        useCaseImplementation: SearchHistoryUseCaseImpl
    ): SearchHistoryUseCase

    @Binds
    @ViewModelScoped
    abstract fun provideFavouriteMovieUseCase(
        useCaseImplementation: FavouriteMovieUseCaseImpl
    ): FavouriteMovieUseCase
}