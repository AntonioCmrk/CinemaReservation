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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.ButtonComponent
import com.example.cinema_reservation_android.components.ClickableLoginTextComponent
import com.example.cinema_reservation_android.components.HeadingTextComponent
import com.example.cinema_reservation_android.components.MyTextFieldComponent
import com.example.cinema_reservation_android.components.PasswordTextFieldComponent
import com.example.cinema_reservation_android.data.LoginViewModel
import com.example.cinema_reservation_android.data.UIEvent
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.example.cinema_reservation_android.navigation.SystemBackButtonHandler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(loginViewModel: LoginViewModel = viewModel()){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){
            HeadingTextComponent(value = stringResource(id = R.string.sign_up_title))
            Spacer(modifier = Modifier.height(80.dp))
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.username),
                painterResource(id = R.drawable.profile),
                onTextChanged = {
                    loginViewModel.onEvent(UIEvent.UsernameChanged(it))
                },
                loginViewModel.registrationUIState.value.usernameError
            )
            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextChanged = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                },
                loginViewModel.registrationUIState.value.emailError
            )
            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                },
                loginViewModel.registrationUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(80.dp))
            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
                })
            ClickableLoginTextComponent(onTextSelected = {
                CinemaReservationAppRouter.navigateTo(
                    Screen.LogInScreen)})
        }
    }
    SystemBackButtonHandler {
        CinemaReservationAppRouter.navigateTo(Screen.FirstScreen)
    }
}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}
