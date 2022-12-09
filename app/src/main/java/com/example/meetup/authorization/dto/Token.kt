package com.example.meetup.authorization.dto

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String
)
