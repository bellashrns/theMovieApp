package com.bella.week4.data.di

import com.bella.week4.data.local.realm.repository.SearchHistoryRepositoryImpl
import com.bella.week4.domain.repository.SearchHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [RealmModule::class])
@InstallIn(SingletonComponent::class)
abstract class RealmRepositoryModule {

    @Binds
    abstract fun provideSearchHistoryRepository(
        realmRepositoryImpl: SearchHistoryRepositoryImpl
    ): SearchHistoryRepository
}