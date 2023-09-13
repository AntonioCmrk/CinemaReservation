package com.example.cinema_reservation_android.sealed

import com.example.cinema_reservation_android.models.Movie

sealed class DataState {

    class Success(val data: MutableList<Movie>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}