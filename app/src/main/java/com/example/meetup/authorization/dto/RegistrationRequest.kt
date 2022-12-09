package com.example.meetup.authorization.dto

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String
)
