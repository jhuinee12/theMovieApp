package com.zahi.themovieapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("cast") val cast: MutableList<ActorInfo>,
): Parcelable