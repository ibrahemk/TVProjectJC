package com.example.tvprojectjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface

import com.example.tvprojectjc.ui.theme.TVProjectJCTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVProjectJCTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(onItemSelected = { index ->
                                navController.navigate(Screen.Details.createRoute(index))
                            })
                        }
                        composable(
                            route = Screen.Details.route,
                            arguments = listOf(navArgument("index") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val index = backStackEntry.arguments?.getInt("index") ?: 0
                            DetailsScreen(
                                index = index,
                                onPlay = {
                                    navController.navigate(Screen.VideoPlayer.route)
                                }
                            )
                        }
                        composable(Screen.VideoPlayer.route) {
                            VideoPlayerScreen()
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
