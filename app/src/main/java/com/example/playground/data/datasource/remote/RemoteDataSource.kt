package com.example.playground.data.datasource.remote

import com.example.playground.data.common.Result
import com.example.playground.data.response.MovieResult
import com.example.playground.data.response.PeopleResult
import com.example.playground.data.response.TVShowResult

interface RemoteDataSource {
    suspend fun getMovies(): Result<List<MovieResult>>
    suspend fun getTvShow(): Result<List<TVShowResult>>
    suspend fun getPeople(): Result<List<PeopleResult>>
}
