package com.example.cinema_reservation_android.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cinema_reservation_android.data.rules.Validator
import com.example.cinema_reservation_android.navigation.CinemaReservationAppRouter
import com.example.cinema_reservation_android.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignupViewModel : ViewModel() {

    private val TAG = SignupViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)
    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.UsernameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    username = event.username
                )
                validateDataWithRules()
                printState()
            }
            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                validateDataWithRules()
                printState()
            }
            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                validateDataWithRules()
                printState()
            }
            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()

            }
        }
    }

    private fun signUp() {
        Log.d(TAG, "inside_SignUp")
        printState()
       createUserInFirebase(
           email = registrationUIState.value.email,
           password = registrationUIState.value.password
       )
    }

    private fun validateDataWithRules() {
        val unameResult = Validator.validateUsername(
            uname = registrationUIState.value.username
        )
        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            pass = registrationUIState.value.password
        )
        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "usernameResult = $unameResult")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")

        registrationUIState.value = registrationUIState.value.copy(
            usernameError = unameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        allValidationsPassed.value = unameResult.status && emailResult.status && passwordResult.status
    }

    private fun printState(){
        Log.d(TAG, "inside_printState")
        Log.d(TAG, registrationUIState.toString())
    }

    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false
                if (it.isSuccessful) {
                    CinemaReservationAppRouter.navigateTo(Screen.MovieScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }

    fun logout(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null){
                Log.d(TAG, "Inside sign outsuccess")
                CinemaReservationAppRouter.navigateTo(Screen.LogInScreen)
            }else{
                Log.d(TAG, "Inside sign out in not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }


}