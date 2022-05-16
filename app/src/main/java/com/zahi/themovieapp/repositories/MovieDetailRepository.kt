package com.zahi.themovieapp.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import com.zahi.themovieapp.App
import com.zahi.themovieapp.entity.MovieInfo


class MovieDetailRepository(context: Context) {
    var app: App = context.applicationContext as App

    private val tmdbApi = TmdbService.api

    fun getDetailMovieCredit(movie_id: Int) = tmdbApi.getDetailMovieCredit(movie_id = movie_id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCountId(id: Int): Int {
        return app.database.movieDao().countId(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: MovieInfo) {
        app.database.movieDao().insert(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(movie: MovieInfo) {
        app.database.movieDao().delete(movie)
    }
}