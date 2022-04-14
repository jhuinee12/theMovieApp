package com.tmp.themovieapp.repositories

class MovieListRepository {
    private val tmdbApi = TmdbService.api

    fun getRepositories(page: Int) = tmdbApi.getPopularMovies(page = page)
}