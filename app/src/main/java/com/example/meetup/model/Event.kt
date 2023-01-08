package com.example.meetup.model

data class Event(
    val name: String,
    val date: String,
    val durationInSeconds: Int,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val color: String
)
