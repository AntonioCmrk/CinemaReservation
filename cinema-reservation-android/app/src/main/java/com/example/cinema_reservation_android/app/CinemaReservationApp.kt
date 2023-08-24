package com.example.cinema_reservation_android.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.example.cinema_reservation_android.screens.FirstScren
import com.example.cinema_reservation_android.screens.LogInScreen
import com.example.cinema_reservation_android.screens.SignUpScreen
import com.example.cinema_reservation_android.screens.WelcomeScreen

@Composable
fun CinemaReservationApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.primary)
    ){
        Crossfade(targetState = CinemaReservationAppRouter.currentScreen) { currentState ->
            when (currentState.value) {

                is Screen.FirstScreen -> {
                    FirstScren()
                }

                is Screen.WelcomeScreen -> {
                    WelcomeScreen()
                }

                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.LogInScreen -> {
                    LogInScreen()
                }

                else -> { FirstScren()}
            }
        }

    }
}