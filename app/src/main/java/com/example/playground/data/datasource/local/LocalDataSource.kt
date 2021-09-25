package com.example.playground.data.datasource.local

import com.example.playground.data.datasource.local.dao.PlaygroundDao
import com.example.playground.data.datasource.local.entity.MovieEntity
import com.example.playground.data.datasource.local.entity.PeopleEntity
import com.example.playground.data.datasource.local.entity.TvShowEntity

class LocalDataSource constructor(private val bookingPlaygroundDao: PlaygroundDao) {

    fun getAllMovies() = bookingPlaygroundDao.getAllMovies()
    suspend fun insertMovies(movies: List<MovieEntity>) = bookingPlaygroundDao.insertAllMovies(movies)

    fun getAllTvShows(): List<TvShowEntity> = bookingPlaygroundDao.getAllTvShow()
    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = bookingPlaygroundDao.insertAllTvShow(tvShows)

    fun getAllPeople(): List<PeopleEntity> = bookingPlaygroundDao.getAllPeople()
    suspend fun insertPeople(people: List<PeopleEntity>) = bookingPlaygroundDao.insertAllPeople(people)
}
