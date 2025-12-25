package com.example.tvprojectjc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.example.tvprojectjc.ui.theme.TVProjectJCTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(onItemSelected: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            MovieRow(
                title = "Favorite Movies",
                onItemSelected = onItemSelected
            )
        }
        item {
            MovieRow(
                title = "Movies",
                onItemSelected = onItemSelected
            )
        }

    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieRow(
    title: String,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp, start = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(20) { index ->
                Card(
                    onClick = { onItemSelected(index) },
                    scale = CardDefaults.scale(focusedScale = 1.1f)
                ) {
                    Text(
                        text = "$title $index",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreviewHomeScreen() {
    TVProjectJCTheme {


        HomeScreen(onItemSelected = {})
    }
}
