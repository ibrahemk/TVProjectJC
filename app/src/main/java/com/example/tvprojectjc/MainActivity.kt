package com.example.tvprojectjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay




import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface

import com.example.tvprojectjc.ui.theme.TVProjectJCTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVProjectJCTheme {
                // simple stack management for Navigation 3
                val backStack = remember { mutableStateListOf<Any>(Home) }
                
                // Handle system back press
                androidx.activity.compose.BackHandler(enabled = backStack.size > 1) {
                    backStack.removeAt(backStack.lastIndex)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    NavDisplay(
                        backStack = backStack,
                    ) { key ->
                        NavEntry(key) {
                            when (key) {
                                Home -> HomeScreen(
                                    onItemSelected = { index -> backStack.add(Details(index)) }
                                )

                                is Details -> DetailsScreen(
                                    index = key.index,
                                    onPlay = { backStack.add(VideoPlayer) }
                                )

                                VideoPlayer -> VideoPlayerScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TVProjectJCTheme {
        HomeScreen(onItemSelected = {})
    }
}
