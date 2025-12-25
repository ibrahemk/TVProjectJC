package com.example.tvprojectjc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.tv.material3.*
import com.example.tvprojectjc.ui.theme.TVProjectJCTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun DetailsScreen(index: Int, onPlay: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                        Color.Black
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(48.dp)
                .align(Alignment.CenterStart)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Details for Item $index",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "This is a detailed description for card number $index. " +
                        "In a real app, this would show movie details, TV show synopses, " +
                        "or any other relevant information for the selected item.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray,
                modifier = Modifier.width(500.dp)
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = onPlay) {
                    Text("Play Now")
                }
                OutlinedButton(onClick = { /* Add to list logic */ }) {
                    Text("Add to Watchlist")
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreviewDetailsScreen() {
    TVProjectJCTheme {
        DetailsScreen(index = 1, onPlay = {})
    }
}
