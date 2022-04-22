package com.tmp.themovieapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tmp.themovieapp.entity.MovieInfoRoom

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieInfoRoom")
    fun getAll(): List<MovieInfoRoom>

    @Query("SELECT * FROM MovieInfoRoom WHERE id IN (:id)")
    fun loadAllById(id: Int): List<MovieInfoRoom>

    @Insert
    fun insert(movie: MovieInfoRoom)

    @Delete
    fun delete(movie: MovieInfoRoom)
}
