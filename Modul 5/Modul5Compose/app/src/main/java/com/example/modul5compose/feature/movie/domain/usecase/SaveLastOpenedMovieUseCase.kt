package com.example.modul5compose.feature.movie.domain.usecase

import com.example.modul5compose.feature.movie.domain.repository.MoviePreferencesRepository

class SaveLastOpenedMovieUseCase(private val repository: MoviePreferencesRepository) {
    operator fun invoke(title: String) {
        repository.saveLastOpenedMovieTitle(title)
    }
}