package com.example.cinema_reservation_android.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.cinema_reservation_android.models.Movie

sealed class Screen {
    object SignUpScreen : Screen()
    object LogInScreen : Screen()
    object MovieDetailScreen : Screen()
    object FirstScreen : Screen()
    object MovieScreen : Screen()
}

object CinemaReservationAppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.FirstScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }


}