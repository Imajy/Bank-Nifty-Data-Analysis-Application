package com.billing.application.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.billing.application.MainActivity
import com.billing.application.presentation.home_screen.HomeScreen
import com.billing.application.presentation.home_screen.edit.InsertUserScreen
import com.billing.application.presentation.splash_screen.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    context: MainActivity,
    innerPadding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(innerPadding, navController)
        }
        composable(Screen.EditScreen.route) {
            InsertUserScreen( navController, innerPadding)
        }
    }
}
