package com.tmp.themovieapp.api

import com.tmp.themovieapp.entity.DetailMovieInfo
import com.tmp.themovieapp.entity.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "4912bd0c310773514471f12e8035c5f4",
        @Query("page") page: Int,
        @Query("language") language: String = "ko"
    ): Call<MovieList>

    fun getDetailMovieInfo(
        @Query("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String = "4912bd0c310773514471f12e8035c5f4",
        @Query("language") language: String = "ko"
    ): Call<DetailMovieInfo>
}