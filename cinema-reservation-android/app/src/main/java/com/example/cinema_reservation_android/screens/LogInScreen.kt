package com.example.cinema_reservation_android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.ButtonComponent
import com.example.cinema_reservation_android.components.ClickableLoginTextComponent
import com.example.cinema_reservation_android.components.HeadingTextComponent
import com.example.cinema_reservation_android.components.MyTextFieldComponent
import com.example.cinema_reservation_android.components.PasswordTextFieldComponent
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){
            HeadingTextComponent(value = stringResource(id = R.string.log_in_title))
            Spacer(modifier = Modifier.height(80.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource(id = R.drawable.profile)
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock)
            )
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = stringResource(id = R.string.log_in),
                onButtonClicked = { /*TODO*/ })
            ClickableLoginTextComponent(tryingToLogin = false ,onTextSelected = {
                CinemaReservationAppRouter.navigateTo(
                    Screen.SignUpScreen)})
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfLogInScreen(){
    LogInScreen()
}
