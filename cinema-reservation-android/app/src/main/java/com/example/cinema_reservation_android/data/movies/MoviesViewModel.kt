package com.example.cinema_reservation_android.data.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class MoviesViewModel: ViewModel() {
    private val TAG = MoviesViewModel::class.simpleName

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign outsuccess")
                CinemaReservationAppRouter.navigateTo(Screen.LogInScreen)
            } else {
                Log.d(TAG, "Inside sign out in not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }
    val emailId: MutableLiveData<String> = MutableLiveData()

    fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }
}