package com.example.playground.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<T>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)
