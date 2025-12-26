package com.example.tvprojectjc

import com.example.tvprojectjc.ui.theme.TVProjectJCTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeScreen(onItemSelected: (Int) -> Unit) {
    var selectedDrawerItem by remember { mutableStateOf(0) }

    NavigationDrawer(
        drawerContent = {
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DrawerItem(
                    selected = selectedDrawerItem == 0,
                    onClick = { selectedDrawerItem = 0 },
                    icon = Icons.Default.Home,
                    label = "Home"
                )
                DrawerItem(
                    selected = selectedDrawerItem == 1,
                    onClick = { selectedDrawerItem = 1 },
                    icon = Icons.Default.Search,
                    label = "Search"
                )
                DrawerItem(
                    selected = selectedDrawerItem == 2,
                    onClick = { selectedDrawerItem = 2 },
                    icon = Icons.Default.Movie,
                    label = "Movies"
                )
                DrawerItem(
                    selected = selectedDrawerItem == 3,
                    onClick = { selectedDrawerItem = 3 },
                    icon = Icons.Default.Tv,
                    label = "TV Shows"
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            when (selectedDrawerItem) {
                0 -> HomeContent(onItemSelected = onItemSelected)
                1 -> SearchContent()
                2 -> CategoryContent(title = "Movies")
                3 -> CategoryContent(title = "TV Shows")
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun HomeContent(onItemSelected: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar Area
        SearchBar(modifier = Modifier.padding(16.dp))

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
}

@Composable
fun SearchContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Search Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun CategoryContent(title: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$title Screen", style = MaterialTheme.typography.headlineMedium)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NavigationDrawerScope.DrawerItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String
) {
    NavigationDrawerItem(
        selected = selected,
        onClick = onClick,
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier.size(NavigationDrawerItemDefaults.IconSize)
            )
        }
    ) {
        Text(text = label, modifier = Modifier.padding(horizontal = 16.dp))
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Card(
        onClick = { /* Handle Search Click */ },
        modifier = modifier.fillMaxWidth(),
        scale = CardDefaults.scale(focusedScale = 1.05f),
        colors = CardDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "Search movies, shows...",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
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
            contentPadding = PaddingValues(horizontal = 8.dp),
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
