package com.example.playground.domain.repository

import com.example.playground.data.common.Result
import com.example.playground.domain.model.Movie
import com.example.playground.domain.model.People
import com.example.playground.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface PlaygroundRepository {
    suspend fun getMovies(): Flow<Result<List<Movie>>>
    suspend fun getTvShow(): Flow<Result<List<TvShow>>>
    suspend fun getPeople(): Flow<Result<List<People>>>
}
