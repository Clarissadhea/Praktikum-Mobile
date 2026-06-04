package com.example.modul5compose.feature.movie.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5compose.feature.movie.domain.usecase.*

class MovieViewModelFactory(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val saveLastOpenedMovieUseCase: SaveLastOpenedMovieUseCase,
    private val getLastOpenedMovieTitleUseCase: GetLastOpenedMovieTitleUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(
                getPopularMoviesUseCase,
                getMovieByIdUseCase,
                saveLastOpenedMovieUseCase,
                getLastOpenedMovieTitleUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}