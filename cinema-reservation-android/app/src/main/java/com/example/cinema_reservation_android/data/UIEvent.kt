package com.example.cinema_reservation_android.data

sealed class UIEvent {
    data class UsernameChanged(val username:String) : UIEvent()
    data class EmailChanged(val email:String) : UIEvent()
    data class PasswordChanged(val password:String) : UIEvent()

    object RegisterButtonClicked  : UIEvent()
}