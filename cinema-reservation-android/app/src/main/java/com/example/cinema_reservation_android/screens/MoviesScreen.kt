package com.example.cinema_reservation_android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.ButtonComponent
import com.example.cinema_reservation_android.data.signup.SignupViewModel

@Composable
fun MovieScreen(signupViewModel: SignupViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){
            Text(text = "aaaaaaaa")
            ButtonComponent(value = stringResource(id = R.string.log_out),
                onButtonClicked = { signupViewModel.logout() },
            isEnabled = true)
        }
    }
}
@Preview
@Composable
fun DefaultPreviewOfMovieScreen(){
   MovieScreen()
}