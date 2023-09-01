package com.example.cinema_reservation_android.data.login

data class LoginUIState (

    var email  :String = "",
    var password  :String = "",

    var emailError :Boolean = false,
    var passwordError : Boolean = false
)