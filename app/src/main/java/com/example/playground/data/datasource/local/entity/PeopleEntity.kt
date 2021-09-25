package com.example.playground.data.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "People")
data class PeopleEntity(
    @PrimaryKey @NotNull @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "profile_path") val profilePath: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "popularity") val popularity: Double
)
