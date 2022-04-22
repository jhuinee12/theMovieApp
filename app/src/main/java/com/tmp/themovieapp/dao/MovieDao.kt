package com.tmp.themovieapp.dao

import androidx.room.*
import com.tmp.themovieapp.entity.MovieInfo

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieInfo")
    fun getAll(): MutableList<MovieInfo>

    @Query("SELECT * FROM MovieInfo WHERE id IN (:id)")
    fun loadAllById(id: Int): List<MovieInfo>

    @Query("SELECT count(*) FROM MovieInfo WHERE id = :sId")
    fun countId(sId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복값 입력 시, 덮어쓰기
    fun insert(movie: MovieInfo)

    @Delete
    fun delete(movie: MovieInfo)
}
