package com.example.list_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.list_compose.data.SongDataDummy

@Composable
fun SongDetailScreen(songId: Int?) {
    val song = SongDataDummy.dummySongs.find { it.id == songId }

    if (song != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = song.imageResId),
                contentDescription = song.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = song.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = song.artist, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.label_album, song.album),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = stringResource(id = R.string.label_release_year),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = song.year, fontSize = 16.sp)
            }
        }
    } else {
        Text(stringResource(id = R.string.error_song_not_found), modifier = Modifier.padding(16.dp))
    }
}