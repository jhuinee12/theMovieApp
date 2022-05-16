package com.zahi.themovieapp

import android.app.Application
import android.content.Context
import com.zahi.themovieapp.room.FavoriteRoomDatabase

class App : Application() {
    val database by lazy { FavoriteRoomDatabase.getDatabase(applicationContext) }

    init{
        instance = this
    }

    companion object {
        lateinit var instance: App
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }
}