package com.zahi.themovieapp.dao

import androidx.room.*
import com.zahi.themovieapp.entity.ActorInfo

@Dao
interface ActorDao {
    @Query("SELECT * FROM ActorInfo")
    fun getAll(): MutableList<ActorInfo>

    @Query("SELECT * FROM ActorInfo WHERE id IN (:id)")
    fun loadAllById(id: Int): List<ActorInfo>

    @Query("SELECT count(*) FROM ActorInfo WHERE id = :sId")
    fun countId(sId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE) // 중복값 입력 시, 덮어쓰기
    fun insert(movie: ActorInfo)

    @Delete
    fun delete(movie: ActorInfo)
}
