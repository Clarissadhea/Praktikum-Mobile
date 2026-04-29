package com.example.list_compose

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.list_compose.data.Song
import com.example.list_compose.data.SongDataDummy

@Composable
fun SongListScreen(navController: androidx.navigation.NavController) {
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
                items(SongDataDummy.dummySongs.take(5)) { song ->
                    FeaturedSongCard(song = song, navController = navController)
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

        items(SongDataDummy.dummySongs) { song ->
            SongItem(song = song, navController = navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
@Composable
fun FeaturedSongCard(song: Song, navController: androidx.navigation.NavController) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.width(140.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = song.imageResId),
                contentDescription = "Album Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = song.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = song.artist,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.navigate("detail/${song.id}") },
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("Detail", fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun SongItem(song: Song, navController: androidx.navigation.NavController) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = song.imageResId),
                contentDescription = "Album Cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = song.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = song.artist, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = stringResource(id = R.string.label_album, song.album),
                    fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                Text(
                    text = song.year,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, song.streamUrl.toUri())
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(stringResource(id = R.string.btn_listen))
                    }
                    Button(onClick = { navController.navigate("detail/${song.id}") }) {
                        Text(stringResource(id = R.string.btn_detail))
                    }
                }
            }
        }
    }
}