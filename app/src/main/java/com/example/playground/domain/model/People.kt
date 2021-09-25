package com.example.playground.domain.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("id") val id: Int, @SerializedName("name") val name: String, @SerializedName("profile_path") val profilePath: String
)
