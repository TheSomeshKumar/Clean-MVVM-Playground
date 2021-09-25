package com.example.playground.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.playground.data.datasource.local.dao.PlaygroundDao
import com.example.playground.data.datasource.local.entity.MovieEntity
import com.example.playground.data.datasource.local.entity.PeopleEntity
import com.example.playground.data.datasource.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class, PeopleEntity::class], version = 1)
abstract class PlaygroundDatabase : RoomDatabase() {
    abstract fun dao(): PlaygroundDao

    companion object {
        @Volatile
        private var instance: PlaygroundDatabase? = null

        fun getDatabase(context: Context): PlaygroundDatabase = instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) = Room.databaseBuilder(
            appContext, PlaygroundDatabase::class.java, "PlaygroundDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }
}
