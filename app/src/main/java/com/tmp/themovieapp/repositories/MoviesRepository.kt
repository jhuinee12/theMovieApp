package com.tmp.themovieapp.repositories

import android.util.Log
import com.tmp.themovieapp.data.api.TmdbApi
import com.tmp.themovieapp.entity.MovieList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    var api: TmdbApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TmdbApi::class.java)
    }

    fun getPopularMovies(page: Int = 1) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<MovieList> {
                override fun onResponse(
                    call: Call<MovieList>,
                    response: Response<MovieList>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            Log.d("Repository", "Movies: ${responseBody.movies}")
                        } else {
                            Log.d("Repository", "Failed to get response")
                        }
                    }
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    Log.e("Repository", "onFailure", t)
                }
            })
    }
}