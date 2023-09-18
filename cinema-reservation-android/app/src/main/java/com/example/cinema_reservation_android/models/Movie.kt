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
    var r1s4: String? = null,
    var r1s5: String? = null,
    var r1s6: String? = null,
    var r2s1: String? = null,
    var r2s2: String? = null,
    var r2s3: String? = null,
    var r2s4: String? = null,
    var r2s5: String? = null,
    var r2s6: String? = null,
    var r3s1: String? = null,
    var r3s2: String? = null,
    var r3s3: String? = null,
    var r3s4: String? = null,
    var r3s5: String? = null,
    var r3s6: String? = null,
    var r4s1: String? = null,
    var r4s2: String? = null,
    var r4s3: String? = null,
    var r4s4: String? = null,
    var r4s5: String? = null,
    var r4s6: String? = null,
    var r5s1: String? = null,
    var r5s2: String? = null,
    var r5s3: String? = null,
    var r5s4: String? = null,
    var r5s5: String? = null,
    var r5s6: String? = null

){
}