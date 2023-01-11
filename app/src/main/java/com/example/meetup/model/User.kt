package com.example.meetup.model

data class User(
    //val image: Bitmap?,
    //val birthday: Date?,

    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: String,
    val locked: Boolean,
    val enabled: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean,
    val accountNonExpired: Boolean,
    val username: String
    //val
    //val contentDescription: String
)
