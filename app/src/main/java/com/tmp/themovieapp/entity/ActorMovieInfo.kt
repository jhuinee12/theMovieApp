package com.tmp.themovieapp.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActorInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("known_for_department") val known_for_department: String,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String,
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("cast_id") val cast_id: Int,
    @SerializedName("credit_id") val credit_id: String
): Parcelable