package com.example.modul5compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.modul5compose.app.navigation.AppNavigation
import com.example.modul5compose.feature.movie.presentation.viewModel.MovieViewModel
import com.example.modul5compose.ui.theme.Modul5ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Modul5ComposeTheme {
                val app = application as MyApplication
                val viewModel: MovieViewModel = viewModel(factory = app.viewModelFactory)

                AppNavigation(viewModel = viewModel)
            }
        }
    }
}