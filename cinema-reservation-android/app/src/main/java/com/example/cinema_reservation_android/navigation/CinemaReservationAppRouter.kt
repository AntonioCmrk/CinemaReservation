package com.example.cinema_reservation_android.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object LogInScreen : Screen()
    object WelcomeScreen : Screen()
    object FirstScreen : Screen()
}

object CinemaReservationAppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }


}