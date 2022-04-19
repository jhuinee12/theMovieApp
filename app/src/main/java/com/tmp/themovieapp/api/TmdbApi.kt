package com.tmp.themovieapp.api

import com.tmp.themovieapp.entity.MovieDetail
import com.tmp.themovieapp.entity.PopularActorList
import com.tmp.themovieapp.entity.PopularMovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


val APIKEY: String = "4912bd0c310773514471f12e8035c5f4"
val LANG: String = "ko"

interface TmdbApi {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = APIKEY,
        @Query("page") page: Int,
        @Query("language") language: String = LANG
    ): Call<PopularMovieList>

    @GET("movie/{movie_id}/credits")
    fun getDetailMovieCredit(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") apiKey: String = APIKEY,
        @Query("language") language: String = LANG
    ): Call<MovieDetail>

    @GET("person/popular")
    fun getPopularActors(
        @Query("api_key") apiKey: String = APIKEY,
        @Query("page") page: Int,
        @Query("language") language: String = LANG
    ): Call<PopularActorList>
}