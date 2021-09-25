package com.example.playground.data.response

import com.example.playground.data.datasource.local.entity.MovieEntity
import com.example.playground.data.datasource.local.entity.PeopleEntity
import com.example.playground.data.datasource.local.entity.TvShowEntity
import com.example.playground.domain.model.Movie
import com.example.playground.domain.model.People
import com.example.playground.domain.model.TvShow

fun MovieResult.mapToDomain() = Movie(
    id = id, posterPath = posterPath ?: "N/A", backdropPath = backdropPath ?: "N/A", overview = overview, title = title
)

fun MovieResult.mapToEntity() = MovieEntity(
    id = id,
    posterPath = posterPath ?: "N/A",
    adult = adult,
    overview = overview,
    releaseDate = releaseDate,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath ?: "N/A",
    popularity = popularity,
    voteCount = voteCount,
    video = video,
    voteAverage = voteAverage
)

fun MovieEntity.mapToDomain(): Movie = Movie(
    id = id, posterPath = posterPath, backdropPath = backdropPath, overview = overview, title = title
)

fun TVShowResult.mapToDomain() = TvShow(
    id = id, name = name, posterPath = posterPath ?: "N/A", backdropPath = backdropPath ?: "N/A", overview = overview
)

fun TVShowResult.mapToEntity() = TvShowEntity(
    id = id,
    posterPath = posterPath ?: "N/A",
    popularity = popularity,
    backdropPath = backdropPath ?: "N/A",
    voteAverage = voteAverage,
    overview = overview,
    firstAirDate = firstAirDate,
    originalLanguage = originalLanguage,
    voteCount = voteCount,
    name = name,
    originalName = originalName
)

fun TvShowEntity.mapToDomain() = TvShow(
    id = id, name = name, posterPath = posterPath, backdropPath = backdropPath, overview = overview
)

fun PeopleResult.mapToDomain() = People(id = id, name = name, profilePath = profilePath ?: "N/A")

fun PeopleResult.mapToEntity() = PeopleEntity(
    id = id, profilePath = profilePath ?: "N/A", adult = adult, name = name, popularity = popularity
)

fun PeopleEntity.mapToDomain() = People(id = id, name = name, profilePath = profilePath)
