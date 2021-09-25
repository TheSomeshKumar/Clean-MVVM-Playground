package com.example.playground.data.datasource.remote

import com.example.playground.Constants
import com.example.playground.R
import com.example.playground.data.common.DataSourceException
import com.example.playground.data.common.RequestErrorHandler
import com.example.playground.data.common.Result
import com.example.playground.data.response.MovieResult
import com.example.playground.data.response.PeopleResult
import com.example.playground.data.response.TVShowResult

class RemoteDataSourceImpl(private val apiServices: ApiServices) : RemoteDataSource {

    override suspend fun getMovies(): Result<List<MovieResult>> {
        return try {
            val result = apiServices.getMovies(Constants.TMDB_KEY)
            if (result.isSuccessful) {
                result.body()?.let {
                    if (it.results.isNotEmpty()) {
                        Result.Success(it.results)
                    } else {
                        Result.Error(DataSourceException.Server(R.string.no_results_found))
                    }
                } ?: run {
                    Result.Error(
                        DataSourceException.Unexpected(R.string.error_unexpected_message)
                    )
                }
            } else {
                Result.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
            }
        } catch (e: Exception) {
            Result.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getTvShow(): Result<List<TVShowResult>> {
        return try {
            val result = apiServices.getTVShows(Constants.TMDB_KEY)
            if (result.isSuccessful) {
                result.body()?.let {
                    if (it.results.isNotEmpty()) {
                        Result.Success(it.results)
                    } else {
                        Result.Error(DataSourceException.Server(R.string.no_results_found))
                    }
                } ?: run {
                    Result.Error(
                        DataSourceException.Unexpected(R.string.error_unexpected_message)
                    )
                }
            } else {
                Result.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
            }
        } catch (e: Exception) {
            Result.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getPeople(): Result<List<PeopleResult>> {
        return try {
            val result = apiServices.getPeople(Constants.TMDB_KEY)
            if (result.isSuccessful) {
                result.body()?.let {
                    if (it.results.isNotEmpty()) {
                        Result.Success(it.results)
                    } else {
                        Result.Error(DataSourceException.Server(R.string.no_results_found))
                    }
                } ?: run {
                    Result.Error(
                        DataSourceException.Unexpected(R.string.error_unexpected_message)
                    )
                }
            } else {
                Result.Error(DataSourceException.Server(R.string.error_server_unexpected_message))
            }
        } catch (e: Exception) {
            Result.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}
