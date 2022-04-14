package com.tmp.themovieapp.entity

import com.google.gson.annotations.SerializedName

data class MovieInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("status") val status: String,
    @SerializedName("vote_average") val vote_average: Float,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("poster_path") val poster_path: String
)
