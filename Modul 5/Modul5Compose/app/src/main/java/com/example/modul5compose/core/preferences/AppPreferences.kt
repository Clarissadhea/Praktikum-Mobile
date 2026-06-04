package com.example.modul5compose.core.preferences

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "movie_prefs",
        Context.MODE_PRIVATE)

    fun saveLastOpenedMovieTitle(title: String) {
        prefs.edit().putString("LAST_OPENED_TITLE", title).apply()
    }

    fun getLastOpenedMovieTitle(): String? {
        return prefs.getString("LAST_OPENED_TITLE", null)
    }
}