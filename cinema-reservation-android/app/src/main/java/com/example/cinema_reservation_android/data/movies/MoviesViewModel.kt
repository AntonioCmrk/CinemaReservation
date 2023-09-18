package com.example.cinema_reservation_android.data.movies

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinema_reservation_android.models.Movie
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.example.cinema_reservation_android.sealed.DataState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MoviesViewModel: ViewModel() {
    private val TAG = MoviesViewModel::class.simpleName
    private lateinit var database: DatabaseReference

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

    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchDataFromFirebase()
        database = Firebase.database.reference
    }

    fun fetchDataFromFirebase() {
        val tempList = mutableListOf<Movie>()
        response.value = DataState.Loading
        FirebaseDatabase.getInstance().getReference("Movies")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (DataSnap in snapshot.children) {
                        val movieItem = DataSnap.getValue(Movie::class.java)
                        if (movieItem != null)
                            tempList.add(movieItem)
                    }
                    response.value = DataState.Success(tempList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failure(error.message)
                }

            })
    }
    fun updateMovieInDatabase(id: String,
                              r1s1: String?, r1s2: String?, r1s3: String?, r1s4: String?, r1s5: String?, r1s6: String?,
                              r2s1: String?, r2s2: String?, r2s3: String?, r2s4: String?, r2s5: String?, r2s6: String?,
                              r3s1: String?, r3s2: String?, r3s3: String?, r3s4: String?, r3s5: String?, r3s6: String?,
                              r4s1: String?, r4s2: String?, r4s3: String?, r4s4: String?, r4s5: String?, r4s6: String?,
                              r5s1: String?, r5s2: String?, r5s3: String?, r5s4: String?, r5s5: String?, r5s6: String?)
    {
        val movieRef = database.child("Movies").child(id)

        movieRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val movieItem = snapshot.getValue(Movie::class.java)

                if (movieItem != null) {
                    val updatedMovie = movieItem.copy(
                        r1s1 = r1s1 ?: movieItem.r1s1,
                        r1s2 = r1s2 ?: movieItem.r1s2,
                        r1s3 = r1s3 ?: movieItem.r1s3,
                        r1s4 = r1s4 ?: movieItem.r1s4,
                        r1s5 = r1s5 ?: movieItem.r1s5,
                        r1s6 = r1s6 ?: movieItem.r1s6,

                        r2s1 = r2s1 ?: movieItem.r2s1,
                        r2s2 = r2s2 ?: movieItem.r2s2,
                        r2s3 = r2s3 ?: movieItem.r2s3,
                        r2s4 = r2s4 ?: movieItem.r2s4,
                        r2s5 = r2s5 ?: movieItem.r2s5,
                        r2s6 = r2s6 ?: movieItem.r2s6,

                        r3s1 = r3s1 ?: movieItem.r3s1,
                        r3s2 = r3s2 ?: movieItem.r3s2,
                        r3s3 = r3s3 ?: movieItem.r3s3,
                        r3s4 = r3s4 ?: movieItem.r3s4,
                        r3s5 = r3s5 ?: movieItem.r3s5,
                        r3s6 = r3s6 ?: movieItem.r3s6,

                        r4s1 = r4s1 ?: movieItem.r4s1,
                        r4s2 = r4s2 ?: movieItem.r4s2,
                        r4s3 = r4s3 ?: movieItem.r4s3,
                        r4s4 = r4s4 ?: movieItem.r4s4,
                        r4s5 = r4s5 ?: movieItem.r4s5,
                        r4s6 = r4s6 ?: movieItem.r4s6,

                        r5s1 = r5s1 ?: movieItem.r5s1,
                        r5s2 = r5s2 ?: movieItem.r5s2,
                        r5s3 = r5s3 ?: movieItem.r5s3,
                        r5s4 = r5s4 ?: movieItem.r5s4,
                        r5s5 = r5s5 ?: movieItem.r5s5,
                        r5s6 = r5s6 ?: movieItem.r5s6

                    )

                    movieRef.setValue(updatedMovie)
                        .addOnSuccessListener {
                            Log.d(TAG, "Movie updated successfully")
                            // You can handle the success case here
                        }
                        .addOnFailureListener { e ->
                            Log.e(TAG, "Error updating movie: ${e.message}")
                            // Handle the error case here
                        }
                } else {
                    Log.e(TAG, "Movie not found with ID: $id")
                    // Handle the case where the movie with the given ID is not found
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Database error: ${error.message}")
                // Handle the database error here
            }
        })
    }
    var movie by mutableStateOf<Movie?>(null)
    fun addMovie(newMovie: Movie){
        movie = newMovie
    }
}