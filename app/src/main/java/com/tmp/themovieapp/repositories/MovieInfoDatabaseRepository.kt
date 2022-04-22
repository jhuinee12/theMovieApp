package com.tmp.themovieapp.repositories

import androidx.annotation.WorkerThread
import com.tmp.themovieapp.dao.MovieDao
import com.tmp.themovieapp.entity.MovieInfo

class MovieInfoDatabaseRepository(private val movieDao: MovieDao) {
    val allWords = movieDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: MovieInfo) {
        movieDao.insert(movie)
    }
}
