package com.example.cinema_reservation_android.data.rules

object Validator {

    fun validateUsername(uname:String) : ValidationResult{
        return ValidationResult(
            (!uname.isNullOrEmpty() && uname.length>=4)
        )
    }
    fun validateEmail(email:String): ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty())
        )
    }
    fun validatePassword(pass:String): ValidationResult{
        return ValidationResult(
            (!pass.isNullOrEmpty() && pass.length>=4)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)