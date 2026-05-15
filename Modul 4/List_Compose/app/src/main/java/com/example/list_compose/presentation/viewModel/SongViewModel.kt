package com.example.list_compose.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.list_compose.data.source.SongDataDummy
import com.example.list_compose.domain.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class SongViewModel(private val listCategory: String) : ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val data = SongDataDummy.dummySongs
        _songs.value = data
        Timber.d("Log: [$listCategory] Data berhasil dimuat. Total item: ${data.size}")
    }

    fun getSongById(id: Int?): Song? {
        return _songs.value.find { it.id == id }
    }

    fun onDetailClicked(song: Song) {
        Timber.d("Log: Tombol Detail ditekan.")
        Timber.d("Log: Berpindah ke Detail - Judul: ${song.title}, Artis: ${song.artist}")
    }

    fun onListenClicked(url: String) {
        Timber.d("Log: Tombol Explicit Intent (Listen) ditekan. URL: $url")
    }
}