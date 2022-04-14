package com.tmp.themovieapp.repositories

class MovieDetailRepository {
    private val tmdbApi = TmdbService.api

    fun getRepositories(movie_id: Int) = tmdbApi.getDetailMovieInfo(movie_id = movie_id)
}