package com.tmp.themovieapp.repositories

class MovieDetailRepository {
    private val tmdbApi = TmdbService.api

    fun getDetailMovieCredit(movie_id: Int) = tmdbApi.getDetailMovieCredit(movie_id = movie_id)
}