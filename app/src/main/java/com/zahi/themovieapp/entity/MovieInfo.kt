package com.zahi.themovieapp.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "MovieInfo")
data class MovieInfo(
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("release_date") val release_date: String?,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("status") val status: String?,
    @SerializedName("vote_average") val vote_average: Float?,
    @SerializedName("vote_count") val vote_count: Int?,
    @SerializedName("poster_path") val poster_path: String?
): Parcelable

