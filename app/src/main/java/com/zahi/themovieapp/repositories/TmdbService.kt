package com.zahi.themovieapp.repositories

import com.zahi.themovieapp.api.TmdbApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbService {
    var api: TmdbApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TmdbApi::class.java)
    }
}