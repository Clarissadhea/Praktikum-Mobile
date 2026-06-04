package com.example.modul5compose.feature.movie.domain.usecase

import com.example.modul5compose.feature.movie.domain.repository.MoviePreferencesRepository

class GetLastOpenedMovieTitleUseCase(private val repository: MoviePreferencesRepository) {
    operator fun invoke(): String? {
        return repository.getLastOpenedMovieTitle()
    }
}