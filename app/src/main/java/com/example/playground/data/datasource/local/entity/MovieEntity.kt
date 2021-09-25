package com.example.playground.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Movies")
data class MovieEntity(
    @PrimaryKey @NotNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
)
