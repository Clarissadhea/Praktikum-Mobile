package com.example.list_compose.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.list_compose.R
import com.example.list_compose.presentation.components.FeaturedSongCard
import com.example.list_compose.presentation.components.SongItem
import com.example.list_compose.presentation.viewModel.SongViewModel

@Composable
fun SongListScreen(navController: NavController, viewModel: SongViewModel) {
    val songs by viewModel.songs.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.title_top_5),
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
                items(songs.take(5)) { song ->
                    FeaturedSongCard(song, navController, viewModel)
                }
            }
        }
        item {
            Text(
                text = stringResource(id = R.string.title_all_songs),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        items(songs) { song ->
            SongItem(song, navController, viewModel)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}