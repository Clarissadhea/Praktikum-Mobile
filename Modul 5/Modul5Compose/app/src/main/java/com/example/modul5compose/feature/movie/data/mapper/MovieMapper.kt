package com.example.modul5compose.feature.movie.data.mapper

import com.example.modul5compose.feature.movie.data.local.MovieEntity
import com.example.modul5compose.feature.movie.data.remote.dto.MovieDto
import com.example.modul5compose.feature.movie.domain.model.Movie

fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterUrl = this.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        releaseDate = this.releaseDate ?: "Unknown"
    )
}

fun List<MovieEntity>.toDomain(): List<Movie> {
    return this.map { it.toDomain() }
}