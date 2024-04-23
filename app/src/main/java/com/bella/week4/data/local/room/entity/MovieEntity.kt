package com.bella.week4.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val posterPath: String,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val backdropPath: String
)