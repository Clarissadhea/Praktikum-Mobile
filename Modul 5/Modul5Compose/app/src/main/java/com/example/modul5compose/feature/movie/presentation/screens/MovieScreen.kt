package com.example.modul5compose.feature.movie.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modul5compose.core.common.UiState
import com.example.modul5compose.feature.movie.presentation.components.MovieFeaturedItem
import com.example.modul5compose.feature.movie.presentation.components.MovieListItem
import com.example.modul5compose.feature.movie.presentation.viewModel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val movieState by viewModel.movieState.collectAsState()
    val lastOpenedTitle by viewModel.lastOpenedTitle.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TMDB Popular Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

            if (!lastOpenedTitle.isNullOrEmpty()) {
                Text(
                    text = "Terakhir dilihat: $lastOpenedTitle",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                )
            }

            when (movieState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    val movies = (movieState as UiState.Success).data

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            Text(
                                text = "Top 10 Popular",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }

                        item {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(bottom = 24.dp)
                            ) {
                                items(movies.take(10)) { movie ->
                                    MovieFeaturedItem(
                                        movie = movie,
                                        viewModel = viewModel,
                                        onClickDetail = { onNavigateToDetail(it) }
                                    )
                                }
                            }
                        }

                        item {
                            Text(
                                text = "Semua Film",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                        }

                        items(movies) { movie ->
                            MovieListItem(
                                movie = movie,
                                viewModel = viewModel,
                                onClickDetail = { onNavigateToDetail(it) }
                            )
                        }
                    }
                }
                is UiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = (movieState as UiState.Error).message,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}