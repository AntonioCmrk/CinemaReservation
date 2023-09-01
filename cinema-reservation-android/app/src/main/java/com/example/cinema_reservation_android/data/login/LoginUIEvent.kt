package com.example.cinema_reservation_android.data.login

import com.example.cinema_reservation_android.data.signup.SignupUIEvent

sealed class LoginUIEvent {
    data class EmailChanged(val email:String) : LoginUIEvent()
    data class PasswordChanged(val password:String) : LoginUIEvent()

    object LoginButtonClicked  : LoginUIEvent()
}