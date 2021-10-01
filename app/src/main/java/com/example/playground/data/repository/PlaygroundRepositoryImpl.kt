package com.example.playground.data.repository

import com.example.playground.data.common.Result
import com.example.playground.data.common.onFlowStarts
import com.example.playground.data.datasource.local.LocalDataSource
import com.example.playground.data.datasource.remote.RemoteDataSource
import com.example.playground.data.response.mapToDomain
import com.example.playground.data.response.mapToEntity
import com.example.playground.domain.model.Movie
import com.example.playground.domain.model.People
import com.example.playground.domain.model.TvShow
import com.example.playground.domain.repository.PlaygroundRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlaygroundRepositoryImpl(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) : PlaygroundRepository {
    override suspend fun getMovies(): Flow<Result<List<Movie>>> {
        return flow {
            localDataSource.getAllMovies().run {
                if (this.isNotEmpty()) {
                    emit(Result.Success(map { it.mapToDomain() }))
                } else {
                    remoteDataSource.getMovies().run {
                        when (this) {
                            is Result.Success -> {
                                localDataSource.insertMovies(response.map { it.mapToEntity() })
                                emit(Result.Success(response.map { it.mapToDomain() }))
                            }
                            is Result.Error -> {
                                emit(Result.Error(exception))
                            }
                        }
                    }
                }
            }
        }.flowOn(Dispatchers.IO).onFlowStarts()
    }

    override suspend fun getTvShow(): Flow<Result<List<TvShow>>> {
        return flow {
            if (localDataSource.getAllTvShows().isNotEmpty()) {
                emit(Result.Success(localDataSource.getAllTvShows().map { it.mapToDomain() }))
            } else {
                remoteDataSource.getTvShow().run {
                    when (this) {
                        is Result.Success -> {
                            localDataSource.insertTvShows(response.map { it.mapToEntity() })
                            emit(Result.Success(response.map { it.mapToDomain() }))
                        }
                        is Result.Error -> {
                            emit(Result.Error(exception))
                        }
                    }
                }
            }
        }.flowOn(Dispatchers.IO).onFlowStarts()
    }

    override suspend fun getPeople(): Flow<Result<List<People>>> {
        return flow {
            if (localDataSource.getAllPeople().isNotEmpty()) {
                emit(Result.Success(localDataSource.getAllPeople().map { it.mapToDomain() }))
            } else {
                remoteDataSource.getPeople().run {
                    when (this) {
                        is Result.Success -> {
                            localDataSource.insertPeople(response.map { it.mapToEntity() })
                            emit(Result.Success(response.map { it.mapToDomain() }))
                        }
                        is Result.Error -> {
                            emit(Result.Error(exception))
                        }
                    }
                }
            }
        }.flowOn(Dispatchers.IO).onFlowStarts()
    }
}
