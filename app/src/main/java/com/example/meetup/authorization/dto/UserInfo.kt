package com.example.meetup.authorization.dto

data class UserInfo(
    val username: String,
    val password: String,
    val phoneNumber: String,
    val email: String,
    val firstName: String,
    val lastName: String
)
