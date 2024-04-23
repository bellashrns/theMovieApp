package com.bella.week4.domain.usecase.searchHistory

import com.bella.week4.data.local.realm.model.SearchQuery
import io.realm.RealmResults

interface SearchHistoryUseCase {
    fun getSearchHistory(): RealmResults<SearchQuery>?
    fun insertQuery(query: String)
}