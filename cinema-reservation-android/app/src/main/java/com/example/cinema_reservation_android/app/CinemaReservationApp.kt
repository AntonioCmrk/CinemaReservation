package com.example.cinema_reservation_android.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.data.movies.MoviesViewModel
import com.example.cinema_reservation_android.models.Movie
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.example.cinema_reservation_android.screens.FirstScren
import com.example.cinema_reservation_android.screens.LogInScreen
import com.example.cinema_reservation_android.screens.MovieDetailScreen
import com.example.cinema_reservation_android.screens.MovieScreen
import com.example.cinema_reservation_android.screens.SignUpScreen

@Composable
fun CinemaReservationApp(moviesViewModel: MoviesViewModel = viewModel()){

    moviesViewModel.checkForActiveSession()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.primary)
    ){
        if (moviesViewModel.isUserLoggedIn.value == true) {
            CinemaReservationAppRouter.navigateTo(Screen.MovieScreen)
        }
        Crossfade(targetState = CinemaReservationAppRouter.currentScreen) { currentState ->
            when (currentState.value) {

                is Screen.FirstScreen -> {
                    FirstScren()
                }

                is Screen.MovieDetailScreen -> {
                    MovieDetailScreen()
                }

                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }

                is Screen.LogInScreen -> {
                    LogInScreen()
                }
                is Screen.MovieScreen -> {
                    MovieScreen()
                }

                else -> { FirstScren()}
            }
        }

    }
}