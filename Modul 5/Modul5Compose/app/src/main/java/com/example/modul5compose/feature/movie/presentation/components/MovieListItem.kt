package com.example.modul5compose.feature.movie.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.modul5compose.feature.movie.domain.model.Movie
import com.example.modul5compose.feature.movie.presentation.viewModel.MovieViewModel

@Composable
fun MovieListItem(
    movie: Movie,
    viewModel: MovieViewModel,
    onClickDetail: (Int) -> Unit
) {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "Poster ${movie.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Release: ${movie.releaseDate}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            val tmdbUrl = "https://www.themoviedb.org/movie/${movie.id}"
                            viewModel.onExplicitIntentClicked(tmdbUrl)
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tmdbUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Web", fontSize = 12.sp)
                    }

                    Button(
                        onClick = {
                            viewModel.onDetailClicked(movie)
                            onClickDetail(movie.id)
                        }
                    ) {
                        Text("Detail", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}