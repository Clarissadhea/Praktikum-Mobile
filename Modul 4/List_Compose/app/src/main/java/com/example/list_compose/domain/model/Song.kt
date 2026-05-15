package com.example.list_compose.domain.model

data class Song(
    val id: Int,
    val title: String,
    val artist: String,
    val album: String,
    val year: String,
    val imageResId: Int,
    val streamUrl: String
)