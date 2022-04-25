package com.tmp.themovieapp.repositories

import android.content.Context
import androidx.annotation.WorkerThread
import com.tmp.themovieapp.App
import com.tmp.themovieapp.entity.ActorInfo

class ActorDetailRepository(context: Context) {
    var app: App = context.applicationContext as App

    private val tmdbApi = TmdbService.api

    fun getPopularActors(page: Int) = tmdbApi.getPopularActors(page = page)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCountId(id: Int): Int {
        return app.database.actorDao().countId(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(actor: ActorInfo) {
        app.database.actorDao().insert(actor)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(actor: ActorInfo) {
        app.database.actorDao().delete(actor)
    }
}