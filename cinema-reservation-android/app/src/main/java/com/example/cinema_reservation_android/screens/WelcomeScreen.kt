package com.example.cinema_reservation_android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinema_reservation_android.R
import com.example.cinema_reservation_android.components.HeadingTextComponent

@Composable
fun WelcomeScreen(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
        Column(modifier = Modifier.fillMaxSize()){
            HeadingTextComponent(value = stringResource(id = R.string.app_name1))
            HeadingTextComponent(value = stringResource(id = R.string.app_name2))

        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfWelcomeScreen(){
    WelcomeScreen()
}
