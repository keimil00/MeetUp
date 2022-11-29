package com.example.meetup.authorization

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String
)
