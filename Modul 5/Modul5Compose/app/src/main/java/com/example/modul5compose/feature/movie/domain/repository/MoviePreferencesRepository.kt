package com.example.modul5compose.feature.movie.domain.repository

interface MoviePreferencesRepository {
    fun saveLastOpenedMovieTitle(title: String)
    fun getLastOpenedMovieTitle(): String?
}