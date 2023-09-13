package com.example.cinema_reservation_android.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.AppToolbar
import com.example.cinema_reservation_android.components.MovieCard
import com.example.cinema_reservation_android.data.movies.MoviesViewModel
import com.example.cinema_reservation_android.models.Movie
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.example.cinema_reservation_android.sealed.DataState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(moviesViewModel: MoviesViewModel = viewModel()) {
    val moviesState = remember { moviesViewModel.response }
    val TAG = MoviesViewModel::class.simpleName

    DisposableEffect(Unit) {
        moviesViewModel.fetchDataFromFirebase()
        onDispose { }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary))
    ) {
        Scaffold(
            topBar = {
                AppToolbar(
                    toolbarTitle = stringResource(id = R.string.app_name),
                    logoutButtonClicked = {
                        moviesViewModel.logout()
                    }
                )
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (val dataState = moviesState.value) {
                    is DataState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }
                    is DataState.Success -> {
                        val movies = dataState.data as List<Movie>
                        Log.d(TAG, "Movies: $movies")
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            items(movies.size) { index ->
                                val movie = movies[index]
                                MovieCard(
                                    movie = movie,
                                    onClick = {
                                        moviesViewModel.addMovie(movie)
                                        CinemaReservationAppRouter.navigateTo(
                                            Screen.MovieDetailScreen
                                        )
                                    }
                                )
                            }
                        }

                    }
                    is DataState.Failure -> {
                        // Handle the failure state here, you can display an error message
                        // or take other appropriate actions.
                        Log.e(TAG, "Failed to fetch movies: ${dataState.message}")
                        Text(
                            text = "Failed to fetch movies",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    else -> {
                        Text(
                            text = "Failed to fetch movies",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfMovieScreen(){
    MovieScreen()
}
