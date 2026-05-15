package com.example.list_compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.list_compose.domain.model.Song
import com.example.list_compose.presentation.viewModel.SongViewModel
import com.example.list_compose.R

@Composable
fun FeaturedSongCard(song: Song, navController: NavController, viewModel: SongViewModel) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.width(140.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = song.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = song.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = song.artist, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Button(
                    onClick = {
                        viewModel.onDetailClicked(song)
                        navController.navigate("detail/${song.id}")
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text(stringResource(id = R.string.btn_detail), fontSize = 12.sp)
                }
            }
        }
    }
}