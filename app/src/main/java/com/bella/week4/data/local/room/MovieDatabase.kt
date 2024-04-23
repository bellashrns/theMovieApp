package com.bella.week4.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bella.week4.data.local.room.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}