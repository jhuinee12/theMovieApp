package com.tmp.themovieapp.repositories

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import com.tmp.themovieapp.App
import com.tmp.themovieapp.entity.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListRepository(context: Context) {
    var app: App = context.applicationContext as App

    private val tmdbApi = TmdbService.api

    fun getRepositories(page: Int) = tmdbApi.getPopularMovies(page = page)


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAll(): MutableList<MovieInfo> {
        return app.database.movieDao().getAll()
    }
}