package com.example.playground.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class TVShowResult(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("overview") val overview: String,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("name") val name: String,
    @SerializedName("original_name") val originalName: String
)
