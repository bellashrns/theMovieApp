package com.bella.week4.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bella.week4.data.local.room.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavouriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM favourite_movies")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM favourite_movies WHERE id = :id)")
    suspend fun getMovieById(id: Int): Boolean

    @Delete
    suspend fun deleteFavouriteMovie(movie: MovieEntity)
}