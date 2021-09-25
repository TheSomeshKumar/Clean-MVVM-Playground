package com.example.playground.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.playground.data.datasource.local.entity.MovieEntity
import com.example.playground.data.datasource.local.entity.PeopleEntity
import com.example.playground.data.datasource.local.entity.TvShowEntity

@Dao
interface PlaygroundDao {

    @Query("SELECT * FROM Movies")
    fun getAllMovies(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM TvShows")
    fun getAllTvShow(): List<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTvShow(tvShows: List<TvShowEntity>)

    @Query("SELECT * FROM People")
    fun getAllPeople(): List<PeopleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPeople(people: List<PeopleEntity>)
}
