package com.billing.application.common

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    object EditScreen : Screen("edit_screen")

}