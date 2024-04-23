package com.bella.week4.data.local.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.UUID

open class SearchQuery : RealmObject() {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var query: String = ""
}