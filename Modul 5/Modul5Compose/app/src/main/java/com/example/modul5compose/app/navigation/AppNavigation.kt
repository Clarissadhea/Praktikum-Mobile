package com.example.modul5compose.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul5compose.feature.movie.presentation.screens.MovieDetailScreen
import com.example.modul5compose.feature.movie.presentation.screens.MovieScreen
import com.example.modul5compose.feature.movie.presentation.viewModel.MovieViewModel

@Composable
fun AppNavigation(viewModel: MovieViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MovieScreen(
                viewModel = viewModel,
                onNavigateToDetail = { movieId ->
                    navController.navigate("detail/$movieId")
                }
            )
        }

        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable
            MovieDetailScreen(
                movieId = movieId,
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}