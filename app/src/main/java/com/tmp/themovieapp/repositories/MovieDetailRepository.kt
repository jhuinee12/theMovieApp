package com.tmp.themovieapp.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import com.tmp.themovieapp.App
import com.tmp.themovieapp.entity.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
            val sMovie = movie
            app.database.movieDao().insert(movie)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(movie: MovieInfo) {
        app.database.movieDao().delete(movie)
    }
}