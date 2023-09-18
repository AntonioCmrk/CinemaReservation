package com.example.cinema_reservation_android.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.ButtonComponent
import com.example.cinema_reservation_android.components.CustomCheckbox
import com.example.cinema_reservation_android.components.HeadingTextComponent
import com.example.cinema_reservation_android.components.TextComponent
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
    val context = LocalContext.current
    val checkboxStateList = remember {
        mutableStateListOf<Boolean>().apply {
            // Row 1
            add(movie?.r1s1?.isNotEmpty() == true)
            add(movie?.r1s2?.isNotEmpty() == true)
            add(movie?.r1s3?.isNotEmpty() == true)
            add(movie?.r1s4?.isNotEmpty() == true)
            add(movie?.r1s5?.isNotEmpty() == true)
            add(movie?.r1s6?.isNotEmpty() == true)

            // Row 2
            add(movie?.r2s1?.isNotEmpty() == true)
            add(movie?.r2s2?.isNotEmpty() == true)
            add(movie?.r2s3?.isNotEmpty() == true)
            add(movie?.r2s4?.isNotEmpty() == true)
            add(movie?.r2s5?.isNotEmpty() == true)
            add(movie?.r2s6?.isNotEmpty() == true)

            // Row 3
            add(movie?.r3s1?.isNotEmpty() == true)
            add(movie?.r3s2?.isNotEmpty() == true)
            add(movie?.r3s3?.isNotEmpty() == true)
            add(movie?.r3s4?.isNotEmpty() == true)
            add(movie?.r3s5?.isNotEmpty() == true)
            add(movie?.r3s6?.isNotEmpty() == true)

            // Row 4
            add(movie?.r4s1?.isNotEmpty() == true)
            add(movie?.r4s2?.isNotEmpty() == true)
            add(movie?.r4s3?.isNotEmpty() == true)
            add(movie?.r4s4?.isNotEmpty() == true)
            add(movie?.r4s5?.isNotEmpty() == true)
            add(movie?.r4s6?.isNotEmpty() == true)

            // Row 5
            add(movie?.r5s1?.isNotEmpty() == true)
            add(movie?.r5s2?.isNotEmpty() == true)
            add(movie?.r5s3?.isNotEmpty() == true)
            add(movie?.r5s4?.isNotEmpty() == true)
            add(movie?.r5s5?.isNotEmpty() == true)
            add(movie?.r5s6?.isNotEmpty() == true)
        }
    }
    val checkboxEnabledList = remember {
        mutableStateListOf<Boolean>().apply {
            // Row 1
            add(movie?.r1s1?.isEmpty() == true || movie?.r1s1 == email)
            add(movie?.r1s2?.isEmpty() == true || movie?.r1s2 == email)
            add(movie?.r1s3?.isEmpty() == true || movie?.r1s3 == email)
            add(movie?.r1s4?.isEmpty() == true || movie?.r1s4 == email)
            add(movie?.r1s5?.isEmpty() == true || movie?.r1s5 == email)
            add(movie?.r1s6?.isEmpty() == true || movie?.r1s6 == email)

            // Row 2
            add(movie?.r2s1?.isEmpty() == true || movie?.r2s1 == email)
            add(movie?.r2s2?.isEmpty() == true || movie?.r2s2 == email)
            add(movie?.r2s3?.isEmpty() == true || movie?.r2s3 == email)
            add(movie?.r2s4?.isEmpty() == true || movie?.r2s4 == email)
            add(movie?.r2s5?.isEmpty() == true || movie?.r2s5 == email)
            add(movie?.r2s6?.isEmpty() == true || movie?.r2s6 == email)

            // Row 3
            add(movie?.r3s1?.isEmpty() == true || movie?.r3s1 == email)
            add(movie?.r3s2?.isEmpty() == true || movie?.r3s2 == email)
            add(movie?.r3s3?.isEmpty() == true || movie?.r3s3 == email)
            add(movie?.r3s4?.isEmpty() == true || movie?.r3s4 == email)
            add(movie?.r3s5?.isEmpty() == true || movie?.r3s5 == email)
            add(movie?.r3s6?.isEmpty() == true || movie?.r3s6 == email)

            // Row 4
            add(movie?.r4s1?.isEmpty() == true || movie?.r4s1 == email)
            add(movie?.r4s2?.isEmpty() == true || movie?.r4s2 == email)
            add(movie?.r4s3?.isEmpty() == true || movie?.r4s3 == email)
            add(movie?.r4s4?.isEmpty() == true || movie?.r4s4 == email)
            add(movie?.r4s5?.isEmpty() == true || movie?.r4s5 == email)
            add(movie?.r4s6?.isEmpty() == true || movie?.r4s6 == email)

            // Row 5
            add(movie?.r5s1?.isEmpty() == true || movie?.r5s1 == email)
            add(movie?.r5s2?.isEmpty() == true || movie?.r5s2 == email)
            add(movie?.r5s3?.isEmpty() == true || movie?.r5s3 == email)
            add(movie?.r5s4?.isEmpty() == true || movie?.r5s4 == email)
            add(movie?.r5s5?.isEmpty() == true || movie?.r5s5 == email)
            add(movie?.r5s6?.isEmpty() == true || movie?.r5s6 == email)

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
                .padding(16.dp)
        ) {
            TextComponent(movie.name?: "")
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
                        .height(225.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(7) { columnIndex ->
                            if (columnIndex == 0)
                            {
                                Text(
                                    text = "            ",
                                    style = MaterialTheme.typography.labelSmall,
                                    modifier = Modifier
                                        .weight(1f, fill = false)
                                        .padding(0.dp, 0.dp),
                                    textAlign = TextAlign.Center
                                )
                            }else{
                            Text(
                                text = "Seat ${columnIndex}",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier
                                    .weight(1f, fill = false)
                                    .padding(0.dp, 0.dp),
                                textAlign = TextAlign.Center
                            )}
                        }
                    }
                }
                items(5) { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Row ${rowIndex + 1}", // Label for the row
                            style = MaterialTheme.typography.labelSmall // Use labelSmall style
                            //modifier = Modifier.width(80.dp) // Adjust the width as needed
                        )
                        Spacer(modifier = Modifier.width(16.dp)) // Add spacing between label and checkboxes
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            repeat(6) { columnIndex ->
                                val checkboxIndex = rowIndex * 6 + columnIndex
                                CustomCheckbox(
                                checked = checkboxStateList[checkboxIndex],
                                enabled = checkboxEnabledList[checkboxIndex],
                                onCheckedChange = { isChecked ->
                                    checkboxStateList[checkboxIndex] = isChecked
                                    // Row 1
                                    if (checkboxEnabledList[0] && checkboxStateList[0]) movie?.r1s1 = email
                                    if (checkboxEnabledList[0] && !checkboxStateList[0]) movie?.r1s1 = ""
                                    if (checkboxEnabledList[1] && checkboxStateList[1]) movie?.r1s2 = email
                                    if (checkboxEnabledList[1] && !checkboxStateList[1]) movie?.r1s2 = ""
                                    if (checkboxEnabledList[2] && checkboxStateList[2]) movie?.r1s3 = email
                                    if (checkboxEnabledList[2] && !checkboxStateList[2]) movie?.r1s3 = ""
                                    if (checkboxEnabledList[3] && checkboxStateList[3]) movie?.r1s4 = email
                                    if (checkboxEnabledList[3] && !checkboxStateList[3]) movie?.r1s4 = ""
                                    if (checkboxEnabledList[4] && checkboxStateList[4]) movie?.r1s5 = email
                                    if (checkboxEnabledList[4] && !checkboxStateList[4]) movie?.r1s5 = ""
                                    if (checkboxEnabledList[5] && checkboxStateList[5]) movie?.r1s6 = email
                                    if (checkboxEnabledList[5] && !checkboxStateList[5]) movie?.r1s6 = ""

                                    // Row 2
                                    if (checkboxEnabledList[6] && checkboxStateList[6]) movie?.r2s1 = email
                                    if (checkboxEnabledList[6] && !checkboxStateList[6]) movie?.r2s1 = ""
                                    if (checkboxEnabledList[7] && checkboxStateList[7]) movie?.r2s2 = email
                                    if (checkboxEnabledList[7] && !checkboxStateList[7]) movie?.r2s2 = ""
                                    if (checkboxEnabledList[8] && checkboxStateList[8]) movie?.r2s3 = email
                                    if (checkboxEnabledList[8] && !checkboxStateList[8]) movie?.r2s3 = ""
                                    if (checkboxEnabledList[9] && checkboxStateList[9]) movie?.r2s4 = email
                                    if (checkboxEnabledList[9] && !checkboxStateList[9]) movie?.r2s4 = ""
                                    if (checkboxEnabledList[10] && checkboxStateList[10]) movie?.r2s5 = email
                                    if (checkboxEnabledList[10] && !checkboxStateList[10]) movie?.r2s5 = ""
                                    if (checkboxEnabledList[11] && checkboxStateList[11]) movie?.r2s6 = email
                                    if (checkboxEnabledList[11] && !checkboxStateList[11]) movie?.r2s6 = ""

                                    // Row 3
                                    if (checkboxEnabledList[12] && checkboxStateList[12]) movie?.r3s1 = email
                                    if (checkboxEnabledList[12] && !checkboxStateList[12]) movie?.r3s1 = ""
                                    if (checkboxEnabledList[13] && checkboxStateList[13]) movie?.r3s2 = email
                                    if (checkboxEnabledList[13] && !checkboxStateList[13]) movie?.r3s2 = ""
                                    if (checkboxEnabledList[14] && checkboxStateList[14]) movie?.r3s3 = email
                                    if (checkboxEnabledList[14] && !checkboxStateList[14]) movie?.r3s3 = ""
                                    if (checkboxEnabledList[15] && checkboxStateList[15]) movie?.r3s4 = email
                                    if (checkboxEnabledList[15] && !checkboxStateList[15]) movie?.r3s4 = ""
                                    if (checkboxEnabledList[16] && checkboxStateList[16]) movie?.r3s5 = email
                                    if (checkboxEnabledList[16] && !checkboxStateList[16]) movie?.r3s5 = ""
                                    if (checkboxEnabledList[17] && checkboxStateList[17]) movie?.r3s6 = email
                                    if (checkboxEnabledList[17] && !checkboxStateList[17]) movie?.r3s6 = ""

                                    // Row 4
                                    if (checkboxEnabledList[18] && checkboxStateList[18]) movie?.r4s1 = email
                                    if (checkboxEnabledList[18] && !checkboxStateList[18]) movie?.r4s1 = ""
                                    if (checkboxEnabledList[19] && checkboxStateList[19]) movie?.r4s2 = email
                                    if (checkboxEnabledList[19] && !checkboxStateList[19]) movie?.r4s2 = ""
                                    if (checkboxEnabledList[20] && checkboxStateList[20]) movie?.r4s3 = email
                                    if (checkboxEnabledList[20] && !checkboxStateList[20]) movie?.r4s3 = ""
                                    if (checkboxEnabledList[21] && checkboxStateList[21]) movie?.r4s4 = email
                                    if (checkboxEnabledList[21] && !checkboxStateList[21]) movie?.r4s4 = ""
                                    if (checkboxEnabledList[22] && checkboxStateList[22]) movie?.r4s5 = email
                                    if (checkboxEnabledList[22] && !checkboxStateList[22]) movie?.r4s5 = ""
                                    if (checkboxEnabledList[23] && checkboxStateList[23]) movie?.r4s6 = email
                                    if (checkboxEnabledList[23] && !checkboxStateList[23]) movie?.r4s6 = ""

                                    // Row 5
                                    if (checkboxEnabledList[24] && checkboxStateList[24]) movie?.r5s1 = email
                                    if (checkboxEnabledList[24] && !checkboxStateList[24]) movie?.r5s1 = ""
                                    if (checkboxEnabledList[25] && checkboxStateList[25]) movie?.r5s2 = email
                                    if (checkboxEnabledList[25] && !checkboxStateList[25]) movie?.r5s2 = ""
                                    if (checkboxEnabledList[26] && checkboxStateList[26]) movie?.r5s3 = email
                                    if (checkboxEnabledList[26] && !checkboxStateList[26]) movie?.r5s3 = ""
                                    if (checkboxEnabledList[27] && checkboxStateList[27]) movie?.r5s4 = email
                                    if (checkboxEnabledList[27] && !checkboxStateList[27]) movie?.r5s4 = ""
                                    if (checkboxEnabledList[28] && checkboxStateList[28]) movie?.r5s5 = email
                                    if (checkboxEnabledList[28] && !checkboxStateList[28]) movie?.r5s5 = ""
                                    if (checkboxEnabledList[29] && checkboxStateList[29]) movie?.r5s6 = email
                                    if (checkboxEnabledList[29] && !checkboxStateList[29]) movie?.r5s6 = ""


                                }
                            )
                        }
                    }
                }}
            }
            val GradientColors = listOf(
                colorResource(id = R.color.purple),
                colorResource(id = R.color.primary))
            Box(modifier = Modifier
                .background(
                    brush = Brush.linearGradient(colors = GradientColors),
                    shape = RoundedCornerShape(16.dp),
                    alpha = 0.2f
                )
                .padding(16.dp))
                {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomCheckbox(
                        checked = true,
                        enabled = true,
                        onCheckedChange = { })
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = "Chosen"
                    )
                    CustomCheckbox(
                        checked = false,
                        enabled = true,
                        onCheckedChange = { })
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = "Free"
                    )
                    CustomCheckbox(
                        checked = true,
                        enabled = false,
                        onCheckedChange = { })
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = "Taken"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ButtonComponent(value = "Confirm",
                onButtonClicked = {
                    moviesViewModel.updateMovieInDatabase(movie.id!!,
                        movie.r1s1, movie.r1s2, movie.r1s3, movie.r1s4, movie.r1s5, movie.r1s6,
                        movie.r2s1, movie.r2s2, movie.r2s3, movie.r2s4, movie.r2s5, movie.r2s6,
                        movie.r3s1, movie.r3s2, movie.r3s3, movie.r3s4, movie.r3s5, movie.r3s6,
                        movie.r4s1, movie.r4s2, movie.r4s3, movie.r4s4, movie.r4s5, movie.r4s6,
                        movie.r5s1, movie.r5s2, movie.r5s3, movie.r5s4, movie.r5s5, movie.r5s6)
                    Toast.makeText(context, "Reservation updated successfully", Toast.LENGTH_LONG).show()
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
