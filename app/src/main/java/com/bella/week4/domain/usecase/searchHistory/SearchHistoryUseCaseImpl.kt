package com.bella.week4.domain.usecase.searchHistory

import com.bella.week4.data.local.realm.model.SearchQuery
import com.bella.week4.domain.repository.SearchHistoryRepository
import io.realm.RealmResults
import timber.log.Timber
import javax.inject.Inject

class SearchHistoryUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : SearchHistoryUseCase {
    override fun getSearchHistory(): RealmResults<SearchQuery>? {
        var queryList: RealmResults<SearchQuery>? = null
        try {
            queryList = searchHistoryRepository.getSearchHistory()
        } catch (e: Exception) {
            Timber.tag("SearchMovieViewModel").e("getAllQuery: %s", e.message)
        }
        return queryList
    }

    override fun insertQuery(query: String) {
        try {
            searchHistoryRepository.insertQuery(query)
        } catch (e: Exception) {
            Timber.tag("SearchMovieViewModel").e("insertQuery: %s", e.message)
        }
    }
}