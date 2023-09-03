package com.example.cinema_reservation_android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.AppToolbar
import com.example.cinema_reservation_android.data.movies.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(moviesViewModel: MoviesViewModel = viewModel()) {
    Scaffold(
        topBar = {
            AppToolbar(
                toolbarTitle = stringResource(id = R.string.app_name),
                logoutButtonClicked = {
                    moviesViewModel.logout()
                })
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.primary))
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {


            }

        }
    }
}
@Preview
@Composable
fun DefaultPreviewOfMovieScreen(){
   MovieScreen()
}
