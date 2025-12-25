package com.example.tvprojectjc

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{index}") {
        fun createRoute(index: Int) = "details/$index"
    }
    object VideoPlayer : Screen("video_player")
}
