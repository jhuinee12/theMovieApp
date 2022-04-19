package com.tmp.themovieapp.repositories

class ActorListRepository {
    private val tmdbApi = TmdbService.api

    fun getPopularActors(page: Int) = tmdbApi.getPopularActors(page = page)
}