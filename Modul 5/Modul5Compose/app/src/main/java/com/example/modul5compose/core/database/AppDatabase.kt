package com.example.modul5compose.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.modul5compose.feature.movie.data.local.MovieDao
import com.example.modul5compose.feature.movie.data.local.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}