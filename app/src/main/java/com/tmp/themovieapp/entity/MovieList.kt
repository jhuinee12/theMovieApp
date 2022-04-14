package com.tmp.themovieapp.entity

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieInfo>,
    @SerializedName("total_pages") val pages: Int,
    @SerializedName("total_results") val results: Int
)
