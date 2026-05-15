package com.example.list_xml.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.list_xml.data.source.SongDataDummy
import com.example.list_xml.domain.model.Song
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class SongViewModel(private val categoryName: String) : ViewModel() {

    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        val data = SongDataDummy.dummySongs
        _songs.value = data
        Timber.d("[$categoryName] Log: Data berhasil dimuat. Total item: ${data.size}")
    }

    fun getSongById(id: Int): Song? {
        return _songs.value.find { it.id == id }
    }

    fun onDetailClick(song: Song) {
        Timber.d("Log: Tombol Detail ditekan.")
        Timber.d("Log: Berpindah ke Detail -> ID: ${song.id}, Judul: ${song.title}")
    }

    fun onListenClick(streamUrl: String) {
        Timber.d("Log: Tombol Explicit Intent (Listen) ditekan. URL: $streamUrl")
    }
}

class SongViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SongViewModel(category) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}