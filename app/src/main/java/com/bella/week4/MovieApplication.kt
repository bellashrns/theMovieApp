package com.bella.week4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm

@HiltAndroidApp
class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}