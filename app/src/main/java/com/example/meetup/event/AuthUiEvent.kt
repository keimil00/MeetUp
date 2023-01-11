package com.example.meetup.event

sealed class AuthUiEvent {
    data class FirstNameChanged(val value: String) : AuthUiEvent()
    data class LastNameChanged(val value: String) : AuthUiEvent()
    data class EmailChanged(val value: String) : AuthUiEvent()
    data class PasswordChanged(val value: String) : AuthUiEvent()

    object SignUp : AuthUiEvent()
    object SignIn : AuthUiEvent()
}