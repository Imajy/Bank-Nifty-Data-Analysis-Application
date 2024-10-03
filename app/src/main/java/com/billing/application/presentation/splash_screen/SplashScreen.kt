package com.billing.application.presentation.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.billing.application.R
import com.billing.application.common.Screen
import kotlinx.coroutines.delay
import java.time.LocalDate

@SuppressLint("NewApi")
@Composable
fun SplashScreen(navController: NavHostController) {
    val currentDate = LocalDate.now()
    LaunchedEffect (Unit) {
        delay(1000)
            navController.navigate(Screen.HomeScreen.route) { popUpTo(0) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image(
           painter = painterResource(R.drawable.img),
           contentDescription = "logo",
           modifier = Modifier
               .background(Color.White)
               .fillMaxWidth(.7f)
               .clip(RoundedCornerShape(15.dp)),
           contentScale = ContentScale.FillWidth,
       )
    }
}
