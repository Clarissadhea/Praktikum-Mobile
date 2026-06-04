package com.example.modul5compose.feature.movie.data.repository

import com.example.modul5compose.core.network.ApiResult
import com.example.modul5compose.core.network.safeApiCall
import com.example.modul5compose.feature.movie.data.local.MovieDao
import com.example.modul5compose.feature.movie.data.mapper.toDomain
import com.example.modul5compose.feature.movie.data.mapper.toEntity
import com.example.modul5compose.feature.movie.data.remote.MovieApiService
import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Flow<ApiResult<List<Movie>>> = flow {
        emit(ApiResult.Loading)

        val localData = movieDao.getAllMovies()

        if (localData.isNotEmpty()) {
            emit(ApiResult.Success(localData.toDomain()))
        }

        val apiResult = safeApiCall { apiService.getPopularMovies() }

        when (apiResult) {
            is ApiResult.Success -> {
                val moviesFromApi = apiResult.data.results
                movieDao.clearMovies()
                movieDao.insertAll(moviesFromApi.map { it.toEntity() })

                val newLocalData = movieDao.getAllMovies()
                emit(ApiResult.Success(newLocalData.toDomain()))
            }
            is ApiResult.Error -> {
                if (localData.isEmpty()) {
                    emit(ApiResult.Error(apiResult.message))
                }
            }
            else -> {}
        }
    }

    override fun getMovieById(id: Int): Flow<Movie?> = flow {
        val movie = movieDao.getAllMovies().find { it.id == id }?.toDomain()
        emit(movie)
    }
}