package com.example.meetup.authorization

data class UserState(
    val isLoading: Boolean = false,
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val email: String = ""
)
