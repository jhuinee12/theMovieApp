package com.tmp.themovieapp.dao

import androidx.room.*
import com.tmp.themovieapp.entity.MovieInfo

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieInfo")
    fun getAll(): List<MovieInfo>

    @Query("SELECT * FROM MovieInfo WHERE id IN (:id)")
    fun loadAllById(id: Int): List<MovieInfo>

    @Query("SELECT count(*) FROM MovieInfo WHERE id IN (:id)")
    fun countId(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE) // 중복값 입력 시, 무시! (insert 수행 안함)
    fun insert(movie: MovieInfo)

    @Delete
    fun delete(movie: MovieInfo)
}
