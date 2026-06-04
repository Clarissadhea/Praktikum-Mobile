package com.example.modul5compose

import android.app.Application
import androidx.room.Room
import com.example.modul5compose.core.preferences.AppPreferences
import com.example.modul5compose.core.database.AppDatabase
import com.example.modul5compose.core.network.ApiClient
import com.example.modul5compose.feature.movie.data.remote.MovieApiService
import com.example.modul5compose.feature.movie.data.repository.MoviePreferencesRepositoryImpl
import com.example.modul5compose.feature.movie.data.repository.MovieRepositoryImpl
import com.example.modul5compose.feature.movie.domain.usecase.*
import com.example.modul5compose.feature.movie.presentation.viewModel.MovieViewModelFactory
import timber.log.Timber

class MyApplication : Application() {
    lateinit var viewModelFactory: MovieViewModelFactory

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "movie_database"
        ).build()
        val preferences = AppPreferences(applicationContext)

        val apiService = ApiClient.createService<MovieApiService>()

        val movieRepository = MovieRepositoryImpl(apiService, database.movieDao())
        val prefsRepository = MoviePreferencesRepositoryImpl(preferences)

        val getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
        val getMovieByIdUseCase = GetMovieByIdUseCase(movieRepository)
        val saveLastOpenedUseCase = SaveLastOpenedMovieUseCase(prefsRepository)
        val getLastOpenedUseCase = GetLastOpenedMovieTitleUseCase(prefsRepository)

        viewModelFactory = MovieViewModelFactory(
            getPopularMoviesUseCase,
            getMovieByIdUseCase,
            saveLastOpenedUseCase,
            getLastOpenedUseCase
        )
    }
}