package com.zahi.themovieapp.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import com.zahi.themovieapp.App
import com.zahi.themovieapp.entity.MovieInfo

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