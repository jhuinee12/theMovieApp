package com.zahi.themovieapp.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import com.zahi.themovieapp.App
import com.zahi.themovieapp.entity.ActorInfo

class ActorListRepository(context: Context) {
    var app: App = context.applicationContext as App

    private val tmdbApi = TmdbService.api

    fun getPopularActors(page: Int) = tmdbApi.getPopularActors(page = page)


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getAll(): MutableList<ActorInfo> {
        return app.database.actorDao().getAll()
    }
}