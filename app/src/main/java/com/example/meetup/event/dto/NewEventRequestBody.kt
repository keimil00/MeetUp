package com.example.meetup.event.dto

data class NewEventRequestBody(
    val name: String,
    val date: String,
    val durationInSeconds: Int,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val color: String
)
