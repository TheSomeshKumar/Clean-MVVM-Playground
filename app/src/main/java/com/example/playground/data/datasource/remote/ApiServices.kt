package com.example.playground.data.datasource.remote

import com.example.playground.Constants
import com.example.playground.data.response.BaseResponse
import com.example.playground.data.response.MovieResult
import com.example.playground.data.response.PeopleResult
import com.example.playground.data.response.TVShowResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET(Constants.POPULAR_MOVIE)
    suspend fun getMovies(@Query("api_key") apiKey: String): Response<BaseResponse<MovieResult>>

    @GET(Constants.POPULAR_TV_SHOW)
    suspend fun getTVShows(@Query("api_key") apiKey: String): Response<BaseResponse<TVShowResult>>

    @GET(Constants.POPULAR_PEOPLE)
    suspend fun getPeople(@Query("api_key") apiKey: String): Response<BaseResponse<PeopleResult>>
}
