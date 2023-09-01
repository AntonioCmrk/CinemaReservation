package com.example.cinema_reservation_android

import android.app.Application
import com.google.firebase.FirebaseApp

class CinemaReservationApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}