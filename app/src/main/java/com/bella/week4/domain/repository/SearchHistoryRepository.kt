package com.bella.week4.domain.repository

import com.bella.week4.data.local.realm.model.SearchQuery
import io.realm.RealmResults

interface SearchHistoryRepository {
    fun getSearchHistory(): RealmResults<SearchQuery>?
    fun insertQuery(query: String)
}