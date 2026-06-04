package com.example.modul5compose.feature.movie.domain.usecase

import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieByIdUseCase(private val repository: MovieRepository) {
    operator fun invoke(id: Int): Flow<Movie?> {
        return repository.getMovieById(id)
    }
}