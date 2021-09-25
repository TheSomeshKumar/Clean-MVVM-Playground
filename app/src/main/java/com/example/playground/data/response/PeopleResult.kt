package com.example.playground.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PeopleResult(
    @SerializedName("id") val id: Int,
    @SerializedName("profile_path") val profilePath: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double
)
