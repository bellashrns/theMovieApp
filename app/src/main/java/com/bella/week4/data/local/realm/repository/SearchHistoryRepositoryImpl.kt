package com.bella.week4.data.local.realm.repository

import com.bella.week4.data.local.realm.model.SearchQuery
import com.bella.week4.domain.repository.SearchHistoryRepository
import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    val realm: Realm
) : SearchHistoryRepository {

    override fun getSearchHistory(): RealmResults<SearchQuery>? {
        return realm.where(SearchQuery::class.java).findAll()
    }

    override fun insertQuery(query: String) {
        try {
            realm.executeTransactionAsync { realm ->
                val searchQuery =
                    realm.createObject(SearchQuery::class.java, UUID.randomUUID().toString())
                searchQuery.query = query
                realm.insert(searchQuery)
            }
        } catch (e: Exception) {
            Timber.tag("SearchHistoryRepositoryImpl").e("insertQuery: %s", e.message)
        }
    }
}