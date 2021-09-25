package com.example.playground.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "TvShows")
data class TvShowEntity(
    @PrimaryKey @NotNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "first_air_date") val firstAirDate: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "original_name") val originalName: String
)
