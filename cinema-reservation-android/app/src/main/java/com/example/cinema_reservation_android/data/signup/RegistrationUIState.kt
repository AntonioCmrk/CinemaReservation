package com.example.cinema_reservation_android.data.signup

data class RegistrationUIState (

    var username  :String = "",
    var email  :String = "",
    var password  :String = "",

    var usernameError : Boolean = false,
    var emailError :Boolean = false,
    var passwordError : Boolean = false
)