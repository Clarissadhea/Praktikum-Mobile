package com.example.list_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    SongListScreen(navController = navController)
                }
                composable("detail/{songId}") { backStackEntry ->
                    val songId = backStackEntry.arguments?.getString("songId")?.toIntOrNull()
                    SongDetailScreen(songId = songId)
                }
            }
        }
    }
}