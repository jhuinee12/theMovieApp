package com.zahi.themovieapp.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


// 기본키 자동 할당 : autoGenerate 속성!! (여기선 사용x)
@Parcelize
@Entity(tableName = "ActorInfo")
data class ActorInfo(
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("known_for_department") val known_for_department: String?,
    @SerializedName("name") val name: String,
    @SerializedName("character") val character: String?,
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("cast_id") val cast_id: Int?,
    @SerializedName("credit_id") val credit_id: String?
): Parcelable
