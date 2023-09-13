package com.example.cinema_reservation_android.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.ButtonComponent
import com.example.cinema_reservation_android.components.HeadingTextComponent
import com.example.cinema_reservation_android.data.movies.MoviesViewModel
import com.example.cinema_reservation_android.models.Movie
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.example.cinema_reservation_android.navigation.SystemBackButtonHandler

@Composable
fun MovieDetailScreen(moviesViewModel: MoviesViewModel = viewModel()) {
    val TAG = MoviesViewModel::class.simpleName
    moviesViewModel.getUserData()
    val movie = moviesViewModel.movie
    val email = moviesViewModel.emailId.value
    if (movie != null) {
        Log.d(TAG, "MovieDetailScreen: ${movie.r1s2}")
    }
    val checkboxStateList = remember {
        mutableStateListOf<Boolean>().apply {
            add(movie?.r1s1?.isNotEmpty() == true)
            add(movie?.r1s2?.isNotEmpty() == true)
            add(movie?.r1s3?.isNotEmpty() == true)
            add(movie?.r2s1?.isNotEmpty() == true)
            add(movie?.r2s2?.isNotEmpty() == true)
            add(movie?.r2s3?.isNotEmpty() == true)
        }
    }
    val checkboxEnabledList = remember {
        mutableStateListOf<Boolean>().apply {
            add(movie?.r1s1?.isEmpty() == true || movie?.r1s1 == email)
            add(movie?.r1s2?.isEmpty() == true || movie?.r1s2 == email)
            add(movie?.r1s3?.isEmpty() == true || movie?.r1s3 == email)
            add(movie?.r2s1?.isEmpty() == true || movie?.r2s1 == email)
            add(movie?.r2s2?.isEmpty() == true || movie?.r2s2 == email)
            add(movie?.r2s3?.isEmpty() == true || movie?.r2s3 == email)
        }
    }

    if (movie?.name == null) {
        // Handle the case where movie is null, e.g., display an error message.
        Text(
            text = "Movie not found",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.white))
        ) {
            Text(
                text = movie.name ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
            )
            movie.image?.let { url ->
                val painter = rememberImagePainter(
                    data = url,
                    builder = {
                        placeholder(R.drawable.empty)
                        error(R.drawable.empty)
                    }
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp),
                    contentScale = ContentScale.Crop,
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                items(2) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(3) { columnIndex ->
                            val checkboxIndex = rowIndex * 3 + columnIndex
                            Checkbox(
                                checked = checkboxStateList[checkboxIndex],
                                enabled = checkboxEnabledList[checkboxIndex],
                                onCheckedChange = { isChecked ->
                                    checkboxStateList[checkboxIndex] = isChecked
                                    if(checkboxEnabledList[0] && checkboxStateList[0]) movie.r1s1 = email
                                    if(checkboxEnabledList[0] && !checkboxStateList[0]) movie.r1s1 = ""
                                    if(checkboxEnabledList[1] && checkboxStateList[1]) movie.r1s2 = email
                                    if(checkboxEnabledList[1] && !checkboxStateList[1]) movie.r1s2 = ""
                                    if(checkboxEnabledList[2] && checkboxStateList[2]) movie.r1s3 = email
                                    if(checkboxEnabledList[2] && !checkboxStateList[2]) movie.r1s3 = ""
                                    if(checkboxEnabledList[3] && checkboxStateList[3]) movie.r2s1 = email
                                    if(checkboxEnabledList[3] && !checkboxStateList[3]) movie.r2s1 = ""
                                    if(checkboxEnabledList[4] && checkboxStateList[4]) movie.r2s2 = email
                                    if(checkboxEnabledList[4] && !checkboxStateList[4]) movie.r2s2 = ""
                                    if(checkboxEnabledList[5] && checkboxStateList[5]) movie.r2s3 = email
                                    if(checkboxEnabledList[5] && !checkboxStateList[5]) movie.r2s3 = ""

                                }
                            )
                        }
                    }
                }
            }
            ButtonComponent(value = "Confirm",
                onButtonClicked = {
                    moviesViewModel.updateMovieInDatabase(movie.id!!, movie.r1s1,movie.r1s2,movie.r1s3,movie.r2s1,movie.r2s2,movie.r2s3)
                    CinemaReservationAppRouter.navigateTo(
                        Screen.MovieScreen)
                },
                isEnabled = (movie.id != null)
            )
        }
    }
    SystemBackButtonHandler {
        CinemaReservationAppRouter.navigateTo(Screen.MovieScreen)
    }
}
@Preview
@Composable
fun DefaultPreviewOfMovieDetailScreen(){

}
