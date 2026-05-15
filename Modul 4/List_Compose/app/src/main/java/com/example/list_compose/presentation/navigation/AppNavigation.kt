package com.example.list_compose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.list_compose.presentation.screens.SongDetailScreen
import com.example.list_compose.presentation.screens.SongListScreen
import com.example.list_compose.presentation.viewModel.SongViewModel
import com.example.list_compose.presentation.viewModel.SongViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val songViewModel: SongViewModel = viewModel(
        factory = SongViewModelFactory("Kategori: Dhea's Top Songs")
    )

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            SongListScreen(navController = navController, viewModel = songViewModel)
        }
        composable("detail/{songId}") { backStackEntry ->
            val songId = backStackEntry.arguments?.getString("songId")?.toIntOrNull()
            SongDetailScreen(songId = songId, viewModel = songViewModel)
        }
    }
}