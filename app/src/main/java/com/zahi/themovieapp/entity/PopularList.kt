package com.zahi.themovieapp.entity

import com.google.gson.annotations.SerializedName

data class PopularMovieList(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieInfo>,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("total_results") val results: Int
)

data class PopularActorList(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val actors: List<ActorInfo>,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("total_results") val results: Int
)
