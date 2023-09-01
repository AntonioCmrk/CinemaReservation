package com.example.cinema_reservation_android.data.signup

sealed class SignupUIEvent {
    data class UsernameChanged(val username:String) : SignupUIEvent()
    data class EmailChanged(val email:String) : SignupUIEvent()
    data class PasswordChanged(val password:String) : SignupUIEvent()

    object RegisterButtonClicked  : SignupUIEvent()
}