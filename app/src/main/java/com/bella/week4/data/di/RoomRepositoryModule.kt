package com.bella.week4.data.di

import com.bella.week4.data.local.room.repository.FavouriteMovieRepositoryImpl
import com.bella.week4.domain.repository.FavouriteMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RoomModule::class])
@InstallIn(SingletonComponent::class)
abstract class RoomRepositoryModule {

    @Binds
    abstract fun provideFavouriteMovieRepository(
        roomRepositoryImpl: FavouriteMovieRepositoryImpl
    ): FavouriteMovieRepository
}