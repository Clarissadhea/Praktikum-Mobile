package com.example.modul5compose.feature.movie.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.core.network.ApiResult
import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.domain.usecase.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val saveLastOpenedMovieUseCase: SaveLastOpenedMovieUseCase,
    private val getLastOpenedMovieTitleUseCase: GetLastOpenedMovieTitleUseCase
) : ViewModel() {

    private val _movieState = MutableStateFlow<UiState<List<Movie>>>(UiState.Loading)
    val movieState: StateFlow<UiState<List<Movie>>> get() = _movieState

    private val _detailState = MutableStateFlow<UiState<Movie>>(UiState.Loading)
    val detailState: StateFlow<UiState<Movie>> get() = _detailState

    private val _lastOpenedTitle = MutableStateFlow<String?>(null)
    val lastOpenedTitle: StateFlow<String?> get() = _lastOpenedTitle

    init {
        fetchMovies()
        loadLastOpenedTitle()
    }

    fun fetchMovies() {
        viewModelScope.launch {
            Timber.d("Log: Memulai pengambilan data film dari Repository...")
            getPopularMoviesUseCase().collect { result ->
                when (result) {
                    is ApiResult.Loading -> _movieState.value = UiState.Loading
                    is ApiResult.Success -> {
                        _movieState.value = UiState.Success(result.data)
                        Timber.d("Log: Data berhasil dimuat. Total item: ${result.data.size}")
                    }
                    is ApiResult.Error -> {
                        _movieState.value = UiState.Error(result.message)
                        Timber.e("Log Error: ${result.message}")
                    }
                }
            }
        }
    }

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            _detailState.value = UiState.Loading
            getMovieByIdUseCase(id).collect { movie ->
                if (movie != null) {
                    _detailState.value = UiState.Success(movie)
                    saveLastOpenedMovieUseCase(movie.title)
                    loadLastOpenedTitle()
                } else {
                    _detailState.value = UiState.Error("Data film tidak ditemukan di database")
                }
            }
        }
    }

    private fun loadLastOpenedTitle() {
        _lastOpenedTitle.value = getLastOpenedMovieTitleUseCase()
    }

    fun onDetailClicked(movie: Movie) {
        Timber.d("Log: Tombol Detail ditekan.")
        Timber.d("Log: Berpindah ke Detail -> ID: ${movie.id}, Judul: ${movie.title}")
    }

    fun onExplicitIntentClicked(url: String) {
        Timber.d("Log: Tombol Explicit Intent ditekan. URL: $url")
    }
}