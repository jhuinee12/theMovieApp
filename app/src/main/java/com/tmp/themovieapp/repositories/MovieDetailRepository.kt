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
    suspend fun getAll() {
        CoroutineScope(Dispatchers.Main).launch {   // Main이 아닌 다른 스레드에서 비동기 처리
            Thread {
                app.database.movieDao().getAll()
                var movies = app.database.movieDao().getAll()
                Log.e("TAG", "getAll: $movies")
            }.start()
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCountId(id: Int): Int {
                return app.database.movieDao().countId(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(movie: MovieInfo) {
        CoroutineScope(Dispatchers.Main).launch {
                app.database.movieDao().insert(movie)
            Toast.makeText(app.applicationContext, "insert 완료", Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(movie: MovieInfo) {
        CoroutineScope(Dispatchers.Main).launch {
                app.database.movieDao().delete(movie)
            Toast.makeText(app.applicationContext, "delete 완료", Toast.LENGTH_SHORT).show()
        }
    }
}