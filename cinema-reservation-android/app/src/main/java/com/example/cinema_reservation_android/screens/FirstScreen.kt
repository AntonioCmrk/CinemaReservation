package com.example.cinema_reservation_android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.ButtonComponent
import com.example.cinema_reservation_android.components.HeadingTextComponent
import com.example.cinema_reservation_android.components.MyTextFieldComponent
import com.example.cinema_reservation_android.components.NormalTextComponent
import com.example.cinema_reservation_android.components.PasswordTextFieldComponent
import com.example.cinema_reservation_android.components.SmallTextComponent
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen

@Composable
fun FirstScren(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cinema))
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){
            HeadingTextComponent(value = stringResource(id = R.string.app_name1))
            HeadingTextComponent(value = stringResource(id = R.string.app_name2))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever)
            SmallTextComponent(value = stringResource(id = R.string.please_log_in_or_register))
            ButtonComponent(
                value = stringResource(id = R.string.log_in),
                onButtonClicked = { CinemaReservationAppRouter.navigateTo(Screen.LogInScreen) },isEnabled = true)
            Spacer(modifier = Modifier.height(28.dp))
            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = { CinemaReservationAppRouter.navigateTo(Screen.SignUpScreen) },isEnabled = true)
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfFirstScreen(){
    FirstScren()
}