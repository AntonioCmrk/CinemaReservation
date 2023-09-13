package com.example.cinema_reservation_android.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Movie (
    var id: String? = null,
    var name: String? = null,
    var day: String? = null,
    var image: String? = null,
    var price: Int? = null,
    var r1s1: String? = null,
    var r1s2: String? = null,
    var r1s3: String? = null,
    var r2s1: String? = null,
    var r2s2: String? = null,
    var r2s3: String? = null
){
}